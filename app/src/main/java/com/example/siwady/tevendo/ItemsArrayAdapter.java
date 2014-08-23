package com.example.siwady.tevendo;

/**
 * Created by Siwady on 22/08/2014.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

public class ItemsArrayAdapter extends ArrayAdapter<ParseObject>{
    private final Context context;
    private final ParseObject[] values;

    public ItemsArrayAdapter(Context context,ParseObject[] values) {
        super(context, R.layout.cell_item, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cell_item, parent, false);
        TextView Name = (TextView) rowView.findViewById(R.id.tv_Name);
        TextView Price = (TextView) rowView.findViewById(R.id.tv_Price);
        ImageView Image = (ImageView) rowView.findViewById(R.id.iv_Image);
        Name.setText(values[position].get("Name").toString());
        Price.setText(values[position].get("Price").toString());

        return rowView;
    }
}
