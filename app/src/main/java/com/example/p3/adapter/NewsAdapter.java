package com.example.p3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p3.R;
import com.example.p3.model.DataNews;
import com.example.p3.ui.NewsActivity;
import com.example.p3.util.Constans;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    public static ArrayList<DataNews> pdflist;
    public static ArrayList<DataNews> mFilteredList;
    public Context context;

    public NewsAdapter(ArrayList<DataNews> pdflist, Context context) {
        this.pdflist = pdflist;
        this.context = context;
        mFilteredList = pdflist;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final DataNews webList = mFilteredList.get(position);
        holder.txtTitle.setText(webList.getTitle());

        Picasso.get()
                .load(webList.getLink_image())
                .into(holder.imgPost);

        holder.layClick.setOnClickListener(v -> {
            Constans.TITLE = webList.getTitle();
            Constans.IMAGE = webList.getLink_image();
            Constans.DESCRIPTION = webList.getDescription();

            Intent intent = new Intent(context, NewsActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (mFilteredList == null) ? 0 : mFilteredList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public ImageView imgPost;
        public CardView layClick;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPost = itemView.findViewById(R.id.imgPost);
            layClick = itemView.findViewById(R.id.layClick);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
