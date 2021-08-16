package com.example.internship_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {

    Context context;
    List<Post> postList;


    public Adapter(Context context, List<Post> postList)
    {
        this.postList=postList;
        this.context=context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.crew_card,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.holder holder, int position) {
        Post post=postList.get(position);
        holder.name.setText(post.getName());
        holder.wiki.setText(post.getWikipedia());
        holder.agen.setText(post.getAgency());
        if(!post.getStatus().equals("active")){
            holder.log.setImageResource(R.drawable.offline);
        }
        else{
            holder.log.setImageResource(R.drawable.online);
        }
        Picasso.with(context)
                .load(post.getImage())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class holder extends RecyclerView.ViewHolder{

        ImageView img,log;
        TextView name,agen,wiki;

        public holder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.card_image);
            name=(TextView)itemView.findViewById(R.id.card_name);
            agen=(TextView)itemView.findViewById(R.id.card_agency);
            wiki=(TextView)itemView.findViewById(R.id.card_wikipedia);
            log=(ImageView)itemView.findViewById(R.id.card_status);
        }
    }
}
