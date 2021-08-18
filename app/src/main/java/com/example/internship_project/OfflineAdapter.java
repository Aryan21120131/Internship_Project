package com.example.internship_project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OfflineAdapter extends RecyclerView.Adapter<OfflineAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    List<UserModel> list;

    public OfflineAdapter(Context context, List<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).getName());
        holder.agen.setText(list.get(position).getAgency());
        holder.wiki.setText(list.get(position).getWikipedia());
        if(list.get(position).getStatus().equals("active")){
            holder.log.setImageResource(R.drawable.online);
        }else {
            holder.log.setImageResource(R.drawable.offline);
        }
        holder.img.setImageResource(R.drawable.space_x_icon);
        holder.menu.setVisibility(View.VISIBLE);
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view,position,list.get(position));
            }
        });
    }

    public void showPopUp(View view,int position,UserModel list){
        PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.inflate(R.menu.option_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id=menuItem.getItemId();
                switch(id){
                    case R.id.DELETE:
                        DatabaseClass.getDatabase(context).getDao().deleteCrew(list);
                        Toast.makeText(context, "Delete "+position+" item", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.UPDATE:
//                        AlertDialog update=new AlertDialog.Builder(context)
//                                .setTitle("UPDATE")
//                                .setMessage("Update the given crew member details")
//                                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        Toast.makeText(context, "SAVED", Toast.LENGTH_SHORT).show();
//                                    }
//                                }).setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        Toast.makeText(context, "CALCLED", Toast.LENGTH_SHORT).show();
//                                    }
//                                }).create();
//                        update.show(
                        Toast.makeText(context, "Currently Update function is not available", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img,log;
        TextView name,agen,wiki;
        Button menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.card_image);
            name=(TextView)itemView.findViewById(R.id.card_name);
            agen=(TextView)itemView.findViewById(R.id.card_agency);
            wiki=(TextView)itemView.findViewById(R.id.card_wikipedia);
            log=(ImageView)itemView.findViewById(R.id.card_status);
            menu=itemView.findViewById(R.id.menu);
        }
    }
}
