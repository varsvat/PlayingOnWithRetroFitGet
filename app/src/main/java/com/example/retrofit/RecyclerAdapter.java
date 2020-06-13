package com.example.retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Model> modelList;

    public RecyclerAdapter(List<Model> list){
        this.modelList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText("ID: "+Integer.toString(modelList.get(position).getId()));
        holder.userid.setText("UserID: "+Integer.toString(modelList.get(position).getUserId()));
        holder.title.setText("Title: "+modelList.get(position).getTitle());
        holder.body.setText("Body: "+ modelList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id , userid , title , body;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textid);
            userid = itemView.findViewById(R.id.textuserid);
            title = itemView.findViewById(R.id.texttitle);
            body = itemView.findViewById(R.id.textbody);
        }
    }
}
