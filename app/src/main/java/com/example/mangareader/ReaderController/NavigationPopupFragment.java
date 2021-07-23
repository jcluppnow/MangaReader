package com.example.mangareader.ReaderController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mangareader.R;

public class NavigationPopupFragment extends Fragment {

    private ImageView refreshButton, backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_popup, container, false);

        //Find UI elements.
        refreshButton = view.findViewById(R.id.refreshButton);
        backButton = view.findViewById(R.id.backButton);

        //Setup Event Listeners.
        setupEventListeners();

        return view;
    }

    private void setupEventListeners()
    {
        refreshButton.setOnClickListener(v -> selectRefreshEventHandler());

        backButton.setOnClickListener(v -> selectBackEventHandler());
    }

    private void selectRefreshEventHandler()
    {
        //TBD - Will be used to refresh download.
    }

    private void selectBackEventHandler()
    {
        //Destroy the Reading Activity.
        getActivity().finish();
    }
}