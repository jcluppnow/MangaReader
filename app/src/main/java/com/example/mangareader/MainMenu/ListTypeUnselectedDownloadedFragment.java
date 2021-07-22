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

public class ListTypeUnselectedDownloadedFragment extends Fragment {

    private TextView downloadedLabel;

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
        View view = inflater.inflate(R.layout.fragment_list_type_downloaded, container, false);

        //Find UI Elements.
        downloadedLabel = view.findViewById(R.id.listTypeUnselectedDownloadedLabel);

        //As this item is unselected, hide the selected divider.
        view.findViewById(R.id.selectedDivider).setVisibility(View.INVISIBLE);

        //Add Event Listeners.
        view.setOnClickListener(v -> selectDownloadedEventHandler());

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