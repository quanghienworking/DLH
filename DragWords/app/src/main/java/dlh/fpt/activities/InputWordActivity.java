package dlh.fpt.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
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

public class InputWordActivity extends Activity{
    private List<String> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_word);
        final EditText edtWord = (EditText) findViewById(R.id.edtWord);
        wordList = new ArrayList<String>();
        ListView lv = (ListView) findViewById(R.id.listWords);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (InputWordActivity.this, android.R.layout.simple_list_item_1, wordList);
        lv.setAdapter(adapter);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = edtWord.getText().toString().trim();
                if (word.length() > 0) {
                    if (!wordList.contains(word)) {
                        wordList.add(wordList.size(), word);
                        adapter.notifyDataSetChanged();
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
        edtWord.setFilters(new InputFilter[] { filter });
    }


}
