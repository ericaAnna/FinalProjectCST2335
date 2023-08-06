package com.example.finalprojectcst2335;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private List<String> newsHeadlines;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the list of news headlines
        newsHeadlines = new ArrayList<>();
        newsHeadlines.add("Ukraine war: Russia hits blood transfusion centre, says Zelensky");
        newsHeadlines.add("Imran Khan: Pakistan ex-PM given three-year jail sentence");
        newsHeadlines.add("Donald Trump social media post flagged by prosecutors in court filing");
        newsHeadlines.add("Ukraine war: The Russians hunting for cheap flats in occupied Mariupol");
        newsHeadlines.add("Kai Cenat: Police charge Twitch streamer after PS5 giveaway mayhem");
        newsHeadlines.add("China floods: Torrential rains in Hebei province leave 10 dead");
        newsHeadlines.add("Italian fugitive caught in Corfu thanks to photo celebrating football win");
        newsHeadlines.add("Chandrayaan-3: Historic India mission enters Moon orbit, aiming for south pole");
        newsHeadlines.add("Venezuela Red Cross President Mario Enrique Villarroel fired by court");
        newsHeadlines.add("Hun Sen: Cambodia election result confirms expected win for PM");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_fragment, container, false);

        // Find the RecyclerView in the fragment layout
        recyclerView = rootView.findViewById(R.id.recyclerViewNews);

        // Create and set the adapter for the RecyclerView
        adapter = new NewsAdapter(newsHeadlines);
        recyclerView.setAdapter(adapter);

        // Set the layout manager for the RecyclerView (LinearLayoutManager in this case)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }
}
