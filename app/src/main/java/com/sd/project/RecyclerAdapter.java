package com.sd.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<HistoryDoc> list;
    private Context context;


    public RecyclerAdapter(Context context,List<HistoryDoc> list ){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        holder.ip.setText(list.get(position).getIp());
        holder.city.setText(list.get(position).getCity());
        holder.region.setText(list.get(position).getRegion());
        holder.timezone.setText(list.get(position).getTimezone());
        holder.country.setText(list.get(position).getCountry());
        holder.loc.setText(list.get(position).getLoc());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(Long.parseLong(list.get(position).getTimestamp())));
        holder.date.setText(dateString);

        SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a");

        holder.time.setText(f.format(new Date(Long.parseLong(list.get(position).getTimestamp()))));




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView ip,city,region,timezone,loc,country,time,date;
        public Button button;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            ip = itemView.findViewById(R.id.recyclerViewIp);
            city = itemView.findViewById(R.id.recyclerViewCity);
            region = itemView.findViewById(R.id.recyclerViewRegion);
            timezone = itemView.findViewById(R.id.recyclerViewTimezone);
            loc = itemView.findViewById(R.id.recyclerViewLocation);
            country = itemView.findViewById(R.id.recyclerViewCountry);
            button = itemView.findViewById(R.id.recyclerViewMap);
            time = itemView.findViewById(R.id.recyclerViewTime);
            date = itemView.findViewById(R.id.recyclerViewDate);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent i  = new Intent(context,MapActivity.class);
                   i.putExtra("location",list.get(getAdapterPosition()).getLoc());
                   context.startActivity(i);


                }
            });


        }
    }
}
