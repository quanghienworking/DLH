package dlh.fpt.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import dlh.fpt.R;
import dlh.fpt.adapter.ViewPagerAdapter;

public class PlayActivity extends FragmentActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        sharedPreferences = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
    }
}
