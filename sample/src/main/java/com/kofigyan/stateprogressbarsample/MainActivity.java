package com.kofigyan.stateprogressbarsample;

import android.content.res.Resources;
import android.os.Bundle;

import com.kofigyan.stateprogressbarsample.not_stateprogressbar.adapter.ApiFeatureAdapter;
import com.kofigyan.stateprogressbarsample.not_stateprogressbar.pojo.ApiFeature;
import com.example.stateprogressbar.StateProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListBaseActivity {

    private StateProgressBar stateProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout that includes both RecyclerView and StateProgressBar
        setContentView(R.layout.activity_main);

        // Initialize StateProgressBar
        stateProgressBar = findViewById(R.id.stateProgressBar);
        initializeStateProgressBar();

        // Set up RecyclerView with adapter
        List<ApiFeature> features = getApiFeatures();
        ApiFeatureAdapter adapter = new ApiFeatureAdapter(features, this);
        recyclerView.setAdapter(adapter);
    }

    private void initializeStateProgressBar() {
        // Define the states for the progress bar
        List<String> states = new ArrayList<>();
        states.add("Start");
        states.add("In Progress");
        states.add("Review");
        states.add("Complete");

        stateProgressBar.setStates(states);

        // Example usage of progressing the StateProgressBar
        stateProgressBar.setOnStateChangeListener((oldStateIndex, newStateIndex) -> {
            // Handle state changes (e.g., logging, UI updates)
            System.out.println("State changed from " + oldStateIndex + " to " + newStateIndex);
        });

        // Simulate advancing the state for demo purposes
        stateProgressBar.nextState();
    }

    private List<ApiFeature> getApiFeatures() {

        List<ApiFeature> features = new ArrayList<>();

        Resources res = getResources();
        String[] titles = res.getStringArray(R.array.features_titles);
        String[] descriptions = res.getStringArray(R.array.features_descriptions);

        for (int i = 0; i < titles.length; i++) {
            features.add(new ApiFeature(titles[i], descriptions[i]));
        }

        return features;
    }
}
