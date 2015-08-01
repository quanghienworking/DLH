package dlh.fpt.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import dlh.fpt.fragments.DragAndDropFragment;
import dlh.fpt.fragments.ListWordFragment;

/**
 * Created by LOIBV on 8/1/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    // Tab Titles
    private String tabtitles[] = new String[] { "Tab1", "Tab2"};
    Context context;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open DragAndDropFragment.java
            case 0:
                DragAndDropFragment fragmenttab1 = new DragAndDropFragment();
                return fragmenttab1;

            // Open ListWordFragment.java
            case 1:
                ListWordFragment fragmenttab2 = new ListWordFragment();
                return fragmenttab2;


        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}