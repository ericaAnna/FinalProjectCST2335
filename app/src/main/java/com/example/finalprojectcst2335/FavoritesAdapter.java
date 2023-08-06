package com.example.finalprojectcst2335;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FavoritesAdapter extends BaseAdapter {

    private Context context;
    private List<Article> favoritesList;

    public FavoritesAdapter(Context context, List<Article> favoritesList) {
        this.context = context;
        this.favoritesList = favoritesList;
    }

    @Override
    public int getCount() {
        return favoritesList.size();
    }

    @Override
    public Object getItem(int position) {
        return favoritesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // Return the unique ID for the item at the specified position
        // For example, if your Article class has an ID field, you can use it here.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Article article = favoritesList.get(position);

        viewHolder.favoriteTitleTextView.setText(article.getHeadline());

        // Add other data you want to display for each favorite item
        // viewHolder.favoriteDescriptionTextView.setText(article.getDescription());
        // viewHolder.favoriteDateTextView.setText(article.getDate());

        return convertView;
    }

    private static class ViewHolder {
        TextView favoriteTitleTextView;

        ViewHolder(View view) {
            favoriteTitleTextView = view.findViewById(R.id.favoriteTitleTextView);
            // Initialize other TextViews or UI elements here if needed
            // favoriteDescriptionTextView = view.findViewById(R.id.favoriteDescriptionTextView);
            // favoriteDateTextView = view.findViewById(R.id.favoriteDateTextView);
        }
    }
}
