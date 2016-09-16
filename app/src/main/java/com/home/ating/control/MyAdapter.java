package com.home.ating.control;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ating on 16/9/16.
 */
public class MyAdapter extends ArrayAdapter<Computer> {

    private LayoutInflater mInflater = null;
    public MyAdapter(Context context, List<Computer> data){
        super(context, 0, data);
        mInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Computer data = getItem(position);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.computer_item, parent, false);
        }

        TextView tv = (TextView)convertView.findViewById(R.id.ip);
        ImageView iv = (ImageView)convertView.findViewById(R.id.pic);

        tv.setText(data.ip);
        iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.pc2));

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setClass(getContext().getApplicationContext(), DetailActivity.class);

                intent.putExtra("ip", data.ip);
                intent.putExtra("user", data.user);

                getContext().startActivity(intent);
            }
        });

        return convertView;
    }


}
