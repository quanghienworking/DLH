package dlh.fpt.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dlh.fpt.R;
import dlh.fpt.database.DatabaseUserHandler;
import dlh.fpt.entities.User;
import dlh.fpt.utils.FontsOverride;

/**
 * Created by Daniel on 8/1/2015.
 */
public class UserActivity extends Activity implements View.OnClickListener{
    EditText edtUsername;
    Button btnStart;
    DatabaseUserHandler dbUser;

    @Override
    public void onClick(View v) {
        User user = new User();
        String name = edtUsername.getText().toString();
        user.setName(name);
        user.setUserID(user.hashCode());

        dbUser.addUser(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/robotolight.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/robotolight.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/robotolight.ttf");
        setContentView(R.layout.activity_main);
        dbUser = new DatabaseUserHandler(this);
        btnStart = (Button) findViewById(R.id.btnStart);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        btnStart.setOnClickListener(this);
    }
}
