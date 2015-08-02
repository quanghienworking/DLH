package dlh.fpt.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dlh.fpt.R;
import dlh.fpt.database.DatabaseHandler;
import dlh.fpt.entities.User;
import dlh.fpt.utils.FontsOverride;

/**
 * Created by Daniel on 8/1/2015.
 */
public class UserActivity extends Activity implements View.OnClickListener {
    EditText edtUsername;
    Button btnStart;
    DatabaseHandler dbUser;
    SharedPreferences sharedPreferences;
    Dialog dialog;

    @Override
    public void onClick(View v) {
        User user = new User();
        String name = edtUsername.getText().toString().trim();
        if (name.length() == 0) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
        } else {
            user.setName(name);
            user.setUserID(user.hashCode());
            //search user
            boolean checkExisted = dbUser.checkUser(name);
            if (checkExisted) {
                dialog = new Dialog(this);
                //delete title of popup
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_check_user);
                TextView tvYes = (TextView) dialog.findViewById(R.id.btnYes);
                TextView tvNo = (TextView) dialog.findViewById(R.id.btnNo);
                tvNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                tvYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UserActivity.this, InputWordActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }else {
                boolean check = dbUser.addUser(user);
                if (check = true) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userid", String.valueOf(user.hashCode()));
                    editor.putString("username", edtUsername.getText().toString().trim());
                    editor.commit();
                }
                Intent intent = new Intent(this, InputWordActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/robotolight.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/robotolight.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/robotolight.ttf");
        setContentView(R.layout.activity_main);
        dbUser = new DatabaseHandler(this);
        btnStart = (Button) findViewById(R.id.btnStart);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        sharedPreferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        btnStart.setOnClickListener(this);
    }
}
