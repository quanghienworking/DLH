package dlh.fpt.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dlh.fpt.R;
import dlh.fpt.activities.PlayActivity;

public class DragAndDropFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag_and_drop, container, false);

        String[] array = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        List<String> list = new ArrayList<String>(Arrays.asList(array));
        GridView grid = (GridView) view.findViewById(R.id.grvResult);
        grid.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.grid_item, list));
        return view;
    }


}
