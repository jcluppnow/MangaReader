package com.example.mangareader.NavigationBar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangareader.R;

public class MoreItemSelectedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_item_selected, container, false);

        //Add Event Listeners.
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMoreEventHandler();
            }
        });

        return view;
    }

    private void selectMoreEventHandler()
    {
        //Get Fragment Manager from Parent Activity.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Mark the Library Item as unselected.
        fragmentManager.beginTransaction().replace(R.id.libraryNavigationItem, new LibraryItemUnselectedFragment()).commit();

        //Mark the History Item as unselected.
        fragmentManager.beginTransaction().replace(R.id.historyNavigationItem, new HistoryItemUnselectedFragment()).commit();

        //Mark the More Item as selected.
        fragmentManager.beginTransaction().replace(R.id.moreNavigationItem, new MoreItemSelectedFragment()).commit();
    }
}