package com.example.mangareader.NavigationBar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangareader.R;

public class LibraryItemSelectedFragment extends Fragment {

    private ImageView libraryImageView;
    private TextView libraryTextView;
    private View selectedDivider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library_item, container, false);

        //Find UI elements.
        libraryImageView = view.findViewById(R.id.librarySelectedItemIcon);
        libraryTextView = view.findViewById(R.id.librarySelectedLabel);
        selectedDivider = view.findViewById(R.id.selectedDivider);

        //Set Colors for selected.
        libraryImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_library_selected));
        libraryTextView.setTextColor(getResources().getColor(R.color.selected_blue));
        selectedDivider.setBackgroundColor(getResources().getColor(R.color.selected_blue));

        return view;
    }
}