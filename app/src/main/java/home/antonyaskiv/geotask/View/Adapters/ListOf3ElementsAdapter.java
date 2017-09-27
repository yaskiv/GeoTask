package home.antonyaskiv.geotask.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import home.antonyaskiv.geotask.R;

/**
 * Created by AntonYaskiv on 27.09.2017.
 */

public class ListOf3ElementsAdapter extends BaseAdapter {
    List<String> list;
    Context context;
    LayoutInflater layoutInflater;
    public  ListOf3ElementsAdapter(Context context,List<String> list)
    {
        this.list=list;
        this.context=context;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView=view;
        if (convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.layout_for_one_item_of_list_result,viewGroup,false);
        }
        TextView textView=convertView.findViewById(R.id.ItemOfList);
        textView.setText( getItem(i));
        return convertView;
    }
}
