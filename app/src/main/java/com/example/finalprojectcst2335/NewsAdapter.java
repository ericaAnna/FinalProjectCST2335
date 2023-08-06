package com.example.finalprojectcst2335;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<String> newsHeadlines;

    public NewsAdapter(List<String> newsHeadlines) {
        this.newsHeadlines = newsHeadlines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String headline = newsHeadlines.get(position);
        holder.headlineTextView.setText(headline);
    }

    @Override
    public int getItemCount() {
        return newsHeadlines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView headlineTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headlineTextView = itemView.findViewById(R.id.headlineTextView);
        }
    }
}
