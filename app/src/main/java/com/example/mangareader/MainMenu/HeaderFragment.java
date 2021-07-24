package com.example.mangareader.MainMenu;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangareader.NavigationBar.HistoryItemSelectedFragment;
import com.example.mangareader.NavigationBar.LibraryItemUnselectedFragment;
import com.example.mangareader.NavigationBar.MoreItemUnselectedFragment;
import com.example.mangareader.R;
import com.example.mangareader.SearchFunctionality.SearchFragment;


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
        setupListeners();

        //Set any initial values.
        labelTextView.setText(getString(R.string.libraryLabel));

        return view;
    }

    private void setupListeners()
    {
        searchButton.setOnClickListener(v -> searchEventHandler());
        filterButton.setOnClickListener(v -> filterEventHandler());
    }

    private void searchEventHandler() {
        //Get Fragment Manager from Parent Activity.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Switch Header Fragment will Search Fragment.
        fragmentManager.beginTransaction().replace(R.id.headerFragmentFrameLayout, new SearchFragment()).commit();
    }

    private void filterEventHandler() {
        //TBD
    }


}