package com.example.trupti.myvolleyhasmap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pc41 on 01-06-2017.
 */



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    ArrayList<HashMap<String , String>> iteamlist;
    Context context;

    public MyAdapter(ArrayList<HashMap<String, String>> iteamlist, Context applicationContext) {
        this.context=applicationContext;
        this.iteamlist=iteamlist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_datalist,parent,false);
        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HashMap<String,String>  map = new HashMap<>();

        holder.tvTitle.setText(iteamlist.get(position).get("atitle"));
        holder.tvDescription.setText(iteamlist.get(position).get("des"));
        Picasso.with(context).load("https://s.w.org/about/images/wordpress-logo-notext-bg.png"+iteamlist.get(position).get("image")).into(holder.imageView);
        Toast.makeText(context,""+iteamlist.get(position).get("image"),Toast.LENGTH_SHORT).show();
        //Toast.makeText(context,""+iteamlist.get(position).get("atitle"),Toast.LENGTH_SHORT).show();


    }



    @Override
    public int getItemCount() {
        return iteamlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDescription;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_header);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_content);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
