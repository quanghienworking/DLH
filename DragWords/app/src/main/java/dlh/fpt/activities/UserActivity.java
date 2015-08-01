package dlh.fpt.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dlh.fpt.R;

import dlh.fpt.entities.User;
import dlh.fpt.database.DatabaseUserHandler;

/**
 * Created by Daniel on 8/1/2015.
 */
public class UserActivity extends Activity implements View.OnClickListener{
    EditText edtUsername;
    Button btnStart;
    DatabaseUserHandler dbUser;
    SharedPreferences sharedPreferences;

    @Override
    public void onClick(View v) {
        User user = new User();
        String name = edtUsername.getText().toString().trim();
        if (name.length() == 0) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
        }else {
            user.setName(name);
            user.setUserID(user.hashCode());
            boolean check = dbUser.addUser(user);
            if (check = true) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name" , edtUsername.getText().toString().trim());
                editor.commit();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/robotolight.ttf");
        TextView tv = (TextView) findViewById(R.id.tvEnterUsername);
        tv.setTypeface(custom_font);
        dbUser = new DatabaseUserHandler(this);
        btnStart = (Button) findViewById(R.id.btnStart);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        sharedPreferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        btnStart.setOnClickListener(this);


    }
}
