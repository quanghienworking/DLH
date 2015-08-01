package dlh.fpt.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dlh.fpt.R;
import dlh.fpt.database.DatabaseUserHandler;
import dlh.fpt.database.DatabaseWordHandler;
import dlh.fpt.entities.Word;

public class InputWordActivity extends Activity  {

    private List<String> wordList;
    private EditText edtWord;
    private Button btnAdd;

    private Button btnOK;
    private ListView lv;
    private View footerView;
    DatabaseUserHandler db;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_word);

        sharedPreferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        lv = (ListView) findViewById(R.id.listWords);
        edtWord = (EditText) findViewById(R.id.edtWord);
        btnOK = (Button) findViewById(R.id.btnOK);
//        footerView = getLayoutInflater().inflate(R.layout.listview_footer, null);

        wordList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (InputWordActivity.this, android.R.layout.simple_list_item_1, wordList);
//        lv.addFooterView(footerView);
        lv.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = edtWord.getText().toString().trim();
                if (word.length() > 0) {
                    if (!wordList.contains(word)) {
                        wordList.add(wordList.size(), word);
                        adapter.notifyDataSetChanged();
                        edtWord.setText("");
                    } else {
                        Toast.makeText(InputWordActivity.this, "Duplicated word.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(InputWordActivity.this, "Please enter the word again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        edtWord.setFilters(new InputFilter[]{filter});
        db = new DatabaseUserHandler(this);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < wordList.size(); i++) {
                    Log.d("DAT", wordList.size() + "");
                    if (!db.checkExistWork(wordList.get(i))) {
                        Word w = new Word();
                        w.setWord(wordList.get(i));
                        w.setWordID(w.hashCode());
                        w.setUserID(Integer.parseInt(sharedPreferences.getString("userid", "")));
                        db.addWord(w);
                    }
                }
            }
        });
    }


}
