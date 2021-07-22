package com.example.mangareader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class HeaderFragment extends Fragment {

    private TextView labelTextView;
    private ImageView searchButton, filterButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_header, container, false);

        //Find UI elements.
        labelTextView = view.findViewById(R.id.headerLabel);
        searchButton = view.findViewById(R.id.searchButton);
        filterButton = view.findViewById(R.id.filterButton);

        //Add Event Listeners.
        searchButton.setOnClickListener(v -> searchEventHandler());

        filterButton.setOnClickListener(v -> filterEventHandler());

        //Set any initial values.
        labelTextView.setText(getString(R.string.libraryLabel));

        return view;
    }

    private void searchEventHandler() {

    }

    private void filterEventHandler() {

    }
}