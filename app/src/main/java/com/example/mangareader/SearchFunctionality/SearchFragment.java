package com.example.mangareader.SearchFunctionality;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mangareader.MainMenu.HeaderFragment;
import com.example.mangareader.R;

public class SearchFragment extends Fragment {

    private ImageView searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //Find ui elements.
        searchButton = view.findViewById(R.id.searchButton);

        //Add event listeners.
        setupEventListeners();

        return view;
    }

    private void setupEventListeners()
    {
        searchButton.setOnClickListener(v -> searchEventHandler());
    }

    private void searchEventHandler() {
        //TBD - Have search affect Recycler Results.

        //For now, return to Main.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Replace the Search bar with the original header.
        fragmentManager.beginTransaction().replace(R.id.headerFragmentFrameLayout, new HeaderFragment()).commit();
    }
}