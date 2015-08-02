package dlh.fpt.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dlh.fpt.R;

/**
 * Created by LOIBV on 8/2/2015.
 */
public class ListAdapter extends
        ArrayAdapter<String>
{
    Activity context=null;
    ArrayList<String> myArray=null;
    int layoutId;

    public ListAdapter(Activity context,
                          int layoutId,
                          ArrayList<String> arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArray=arr;
    }
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        if(myArray.size()>0 && position>=0)
        {
            final TextView tv =(TextView)
                    convertView.findViewById(R.id.listTextViewItem);
            tv.setText(myArray.get(position));
        }
        return convertView;
    }
}