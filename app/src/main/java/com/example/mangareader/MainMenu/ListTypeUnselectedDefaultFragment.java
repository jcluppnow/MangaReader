package com.example.mangareader.MainMenu;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mangareader.R;

public class ListTypeUnselectedDefaultFragment extends Fragment {

    private TextView defaultLabel;

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
        View view = inflater.inflate(R.layout.fragment_list_type_default, container, false);

        //Find UI elements.
        //As this item is unselected, hide the selected divider.
        view.findViewById(R.id.selectedDivider).setVisibility(View.INVISIBLE);

        //Add Event Listeners.
        view.setOnClickListener(v -> selectDefaultEventHandler());

        return view;
    }

    private void selectDefaultEventHandler()
    {
        //Get Fragment Manager from Parent Activity.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Mark the Default option as selected.
        fragmentManager.beginTransaction().replace(R.id.defaultListTypeFragment, new ListTypeSelectedDefaultFragment()).commit();

        //Mark the Downloaded option as unselected.
        fragmentManager.beginTransaction().replace(R.id.downloadedListTypeFragment, new ListTypeUnselectedDownloadedFragment()).commit();
    }
}