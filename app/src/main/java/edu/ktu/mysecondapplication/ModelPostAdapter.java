package edu.ktu.mysecondapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ModelPostAdapter extends ArrayAdapter<ModelPost>{
    public ModelPostAdapter (Context context,List<ModelPost> objects){
        super(context, R.layout.listitemdesign,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v= convertView;

        if(v==null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.listitemdesign,null);
        }

        TextView title = (TextView) v.findViewById(R.id.title);
        TextView description = (TextView) v.findViewById(R.id.body_text);

        ModelPost item = getItem(position);

        title.setText(item.getTitle());
        description.setText(item.getBodyText());

        return v;
    }
}
