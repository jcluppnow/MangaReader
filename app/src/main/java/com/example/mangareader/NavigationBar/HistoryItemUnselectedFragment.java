package com.example.mangareader.NavigationBar;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangareader.MainMenu.ListTypeSelectedDefaultFragment;
import com.example.mangareader.MainMenu.ListTypeUnselectedDownloadedFragment;
import com.example.mangareader.R;

public class HistoryItemUnselectedFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_item, container, false);

        //As this item is unselected, hide the selected divider.
        view.findViewById(R.id.selectedDivider).setVisibility(View.INVISIBLE);

        //Add Event Listeners.
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectHistoryEventHandler();
            }
        });

        return view;
    }

    private void selectHistoryEventHandler()
    {
        //Get Fragment Manager from Parent Activity.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Mark the Library Item as unselected.
        fragmentManager.beginTransaction().replace(R.id.libraryNavigationItem, new LibraryItemUnselectedFragment()).commit();

        //Mark the History Item as selected.
        fragmentManager.beginTransaction().replace(R.id.historyNavigationItem, new HistoryItemSelectedFragment()).commit();

        //Mark the More Item as unselected.
        fragmentManager.beginTransaction().replace(R.id.moreNavigationItem, new MoreItemUnselectedFragment()).commit();
    }
}