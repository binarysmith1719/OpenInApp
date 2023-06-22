package com.example.openinapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.openinapp.Model.link;

import java.util.ArrayList;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {
    ArrayList<link> list=new ArrayList<>();
    Context context;
    ClipboardManager clipboard;
    public LinkAdapter(Context context, ArrayList<link> list)
    {
        this.list=list;
        this.context=context;
        clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }
    @NonNull
    @Override
    public LinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linkcards,parent,false);
        return new LinkAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkAdapter.ViewHolder holder, int position) {
           link obj= list.get(position);

//           Log.d("TAG","in onBInd View Holder list->"+obj.getUrl_id());

           holder.clicks.setText(Integer.toString(obj.getTotal_clicks()));
           holder.link.setText(obj.getWeb_link());
           holder.title.setText(obj.getTitle());

           //SHOWING THE MAIN PART OF THE DATE
           String dateString=obj.getCreated_at();
           String str[]=dateString.split("T");
           holder.date.setText(str[0]);

           //IMAGE OF THE CONTENT
           if(obj.getOriginal_image()!=null && !obj.getOriginal_image().equals("")){
           Glide.with(context).load(obj.getOriginal_image()).into(holder.imgView);}
           else{
               holder.imgView.setImageDrawable(context.getDrawable(R.drawable.noimage));
           }

           //CLIPBOARD
           holder.copyToClipboard.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   ClipData clip = ClipData.newPlainText("Copied Text",obj.getWeb_link());
                   clipboard.setPrimaryClip(clip);
                   Toast.makeText(context,"Copied To Clipboard",Toast.LENGTH_SHORT).show();
               }
           });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,date,clicks,link;
        ImageView imgView,copyToClipboard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date  = itemView.findViewById(R.id.date);
            clicks = itemView.findViewById(R.id.noofclicks);
            link=itemView.findViewById(R.id.link);
            imgView= itemView.findViewById(R.id.thumbnail);
            copyToClipboard=itemView.findViewById(R.id.copytoclipboard);
        }
    }


}
