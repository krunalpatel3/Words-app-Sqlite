package krunal.com.example.acer.wordsapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import krunal.com.example.acer.wordsapp.Database.DbHelper;
import krunal.com.example.acer.wordsapp.Database.WordContract;

public class AddActivity extends AppCompatActivity {

    private static final String TAB = "add";
    private FloatingActionButton fab;
    public EditText mEditText;
    DatabaseOpration mdatabaseopration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        fab = findViewById(R.id.floatingActionButton2);
        mEditText = findViewById(R.id.editText);
        mdatabaseopration = new DatabaseOpration(getApplication());
        fab.setOnClickListener((v -> {
            String word = mEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(word)){
                mdatabaseopration.insert(word);
            }else {
                Toast.makeText(this,"Enter Word!",Toast.LENGTH_LONG).show();
            }

            finish();

        }));
    }



}
