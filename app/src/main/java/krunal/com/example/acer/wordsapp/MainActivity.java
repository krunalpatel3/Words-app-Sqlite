package krunal.com.example.acer.wordsapp;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    private static final String TAB= "main";
    DatabaseOpration mdatabaseopration;
    List<Word> list = new ArrayList<>();
    RecycleAdapter mRecycleAdapter;
    RecyclerView recyclerView;
    public static final String id = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerView);
        mdatabaseopration = new DatabaseOpration(getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.i("id",String.valueOf(mRecycleAdapter.get(viewHolder.getAdapterPosition()).getId()));
                DatabaseOpration.delete(mRecycleAdapter.get(viewHolder.getAdapterPosition()).getId());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DisplayData();

        //mRecycleAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK){
            mRecycleAdapter.notifyDataSetChanged();
        }

    }

    private void DisplayData(){

        list = mdatabaseopration.getAllList();

        if (list.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            mRecycleAdapter = new RecycleAdapter(this,list);
            recyclerView.setAdapter(mRecycleAdapter);
            mRecycleAdapter.SetOnClickListener(word -> {

                int getid = word.getId();
                Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                // put id in intent object.
                intent.putExtra(id,getid);
                //start UpdateActivity on click.
                startActivity(intent);

            });

            mRecycleAdapter.notifyDataSetChanged();

        }
        else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(getBaseContext(),"there are no words",Toast.LENGTH_LONG).show();
        }


    }
}
