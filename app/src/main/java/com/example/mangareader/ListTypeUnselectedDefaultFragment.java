package com.example.mangareader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListTypeUnselectedDefaultFragment extends Fragment {

    private TextView defaultLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_type_unselected_default, container, false);

        defaultLabel = view.findViewById(R.id.listTypeUnselectedDefaultLabel);

        defaultLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDefaultEventHandler();
            }
        });

        return view;
    }

    private void selectDefaultEventHandler()
    {
        //Get Fragment Manager from Parent Activity.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Mark the Default option as selected.
        fragmentManager.beginTransaction().replace(R.id.defaultListTypeFragment, new ListTypeSelectedDefaultFragment()).commit();

        //Mark the Downloaded option as unselected.
        fragmentManager.beginTransaction().replace(R.id.downloadedListTypeFragment, new ListTypeSelectedDownloadedFragment()).commit();
    }
}