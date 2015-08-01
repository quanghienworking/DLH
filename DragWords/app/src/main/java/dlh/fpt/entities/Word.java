package dlh.fpt.entities;

/**
 * Created by Daniel on 8/1/2015.
 */
public class Word {
    private int wordID;
    private String word;
    private int userID;

    public Word() {

    }

    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (word != null ? word.hashCode() : 0);
        return hash;
    }
}
