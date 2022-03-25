package com.muverity.xmlfromurl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private final ArrayList<DataModel> listdata;
    private final Context context;

    public ListAdapter(ArrayList<DataModel> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataModel myListData = listdata.get(position);

        holder.id.setText(myListData.id);
        holder.title.setText(myListData.title);

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView logo;
        public TextView id, title;

        public ViewHolder(View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);

        }
    }
}
