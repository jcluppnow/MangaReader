package com.example.mangareader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListTypeUnselectedDownloadedFragment extends Fragment {

    private TextView downloadedLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_type_unselected_downloaded, container, false);

        //Initialise UI Elements.
        downloadedLabel = view.findViewById(R.id.listTypeUnselectedDownloadedLabel);

        downloadedLabel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectDownloadedEventHandler();
                    }
                }
        );

        return view;
    }

    private void selectDownloadedEventHandler()
    {
        //Fetch the Activity Fragment Manager.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Mark the Downloaded option as selected.
        fragmentManager.beginTransaction().replace(R.id.downloadedListTypeFragment, new ListTypeSelectedDownloadedFragment()).commit();

        //Mark the Default option as unselected.
        fragmentManager.beginTransaction().replace(R.id.defaultListTypeFragment, new ListTypeUnselectedDefaultFragment()).commit();
    }
}