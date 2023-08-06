package com.example.finalprojectcst2335;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsHeadlineAdapter extends ArrayAdapter<String> {

    private List<String> newsHeadlines;

    public NewsHeadlineAdapter(Context context, int resource, List<String> headlines) {
        super(context, resource, headlines);
        newsHeadlines = headlines;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_news_headline, parent, false);
        }

        // Get the current news headline
        String currentHeadline = newsHeadlines.get(position);

        // Set the news headline text in the TextView
        TextView headlineTextView = listItemView.findViewById(R.id.textViewHeadline);
        headlineTextView.setText(currentHeadline);

        return listItemView;
    }
}
