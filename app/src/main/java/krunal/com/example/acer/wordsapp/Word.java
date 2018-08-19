package krunal.com.example.acer.wordsapp;

/**
 * Created by acer on 08-02-2018.
 */

public class Word {

    private int id;
    private String Word;

    Word(int id, String word){
        this.id=id;
        this.Word= word;
    }

    Word(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }
}
