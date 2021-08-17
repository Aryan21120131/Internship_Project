package com.example.internship_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

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
    public void onBindViewHolder(@NonNull Adapter.holder holder, @SuppressLint("RecyclerView") int position) {
        Post post=postList.get(position);
        holder.name.setText(post.getName());
        holder.wiki.setText(post.getWikipedia());
        holder.agen.setText(post.getAgency());
        if(post.getStatus().equals("active")){
            holder.log.setImageResource(R.drawable.online);
        }
        else{
            holder.log.setImageResource(R.drawable.offline);
        }
        Picasso.with(context)
                .load(post.getImage())
                .into(holder.img);
        holder.menu.setVisibility(View.INVISIBLE);
//        UserModel userModel=new UserModel();
//        userModel.setName(post.getName());
//        DatabaseClass.getDatabase(context.getApplicationContext()).getDao().deleteMember(userModel);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class holder extends RecyclerView.ViewHolder{

        ImageView img,log;
        TextView name,agen,wiki;
        Button menu;

        public holder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.card_image);
            name=itemView.findViewById(R.id.card_name);
            agen=itemView.findViewById(R.id.card_agency);
            wiki=itemView.findViewById(R.id.card_wikipedia);
            log=itemView.findViewById(R.id.card_status);
            menu=itemView.findViewById(R.id.menu);
        }
    }
}
