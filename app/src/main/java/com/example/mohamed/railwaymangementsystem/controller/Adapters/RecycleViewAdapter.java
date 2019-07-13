package com.example.mohamed.railwaymangementsystem.controller.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintData;
import com.example.mohamed.railwaymangementsystem.APILayer.APIModules.ComplaintOutput;
import com.example.mohamed.railwaymangementsystem.Fragments.AdminComplaintsFragment;
import com.example.mohamed.railwaymangementsystem.R;

import java.util.ArrayList;

/**
 * Created by mohamed on 7/4/2019.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    private Context context ;
    private ArrayList<ComplaintOutput> arrayList;
    private ItemCLickListener itemClickListener;

    public  RecycleViewAdapter(Context context , ArrayList<ComplaintOutput> arrayList, ItemCLickListener itemClickListener)
    {
        this.context=context;
        this.arrayList=arrayList;
        this.itemClickListener=itemClickListener;
    }
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
       View view = LayoutInflater.from(context).inflate(R.layout.recycle_view_item,parent,false);
        RecycleViewHolder viewHolder = new RecycleViewHolder(view);
       return  viewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position )  {
        holder.bindComplaint(arrayList.get(position),itemClickListener,position);
    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder
    {  private TextView ticketTextView ;
       private TextView complaintTextView ;
       private ComplaintOutput complaintOutput ;
       private View itemView;

        public RecycleViewHolder(View itemView ){
            super(itemView);
            ticketTextView = (TextView)itemView.findViewById(R.id.ticket_number_tv);
            complaintTextView=(TextView) itemView.findViewById(R.id.complaint_message_tv);
            this.itemView = itemView;

        }
        public void bindComplaint(ComplaintOutput complaintOutput , ItemCLickListener itemClickListener, int position)
        {   this.complaintOutput=complaintOutput;
            ticketTextView.setText(complaintOutput.getTicketId());
            complaintTextView.setText(complaintOutput.getMessage());
            itemClickListener.onItemClick(itemView,position);
        }



    }

}
