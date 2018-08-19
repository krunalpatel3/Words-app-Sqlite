package krunal.com.example.acer.wordsapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private FloatingActionButton fabDone;
    private EditText mEditText;
    private static int getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mEditText = findViewById(R.id.UpdateeditText);
        fabDone = findViewById(R.id.floatingActionButtonDoneUpdate);

        getId = getIntent().getIntExtra(MainActivity.id, -1);
        Word currentWord = DatabaseOpration.Query(getId);

        mEditText.setText(currentWord.getWord());

        fabDone.setOnClickListener(view -> {
            String word = mEditText.getText().toString().trim();

            if (!TextUtils.isEmpty(word)){
                 int numberOfRows = DatabaseOpration.update(getId,word);
                 Log.i("Update",String.valueOf(numberOfRows));
                 if (numberOfRows != -99){
                     Toast.makeText(this,"Updated Successfully",Toast.LENGTH_LONG).show();
                 }
                 finish();
            }else {
                Toast.makeText(this,"Enter properlly!",Toast.LENGTH_LONG).show();
            }

        });
    }
}
