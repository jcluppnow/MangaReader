package com.example.mangareader.NavigationBar;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangareader.R;

public class HistoryItemSelectedFragment extends Fragment {

    private ImageView historyImageView;
    private TextView historyTextView;
    private View selectedDivider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_history_item, container, false);

        //Find UI elements.
        historyImageView = view.findViewById(R.id.historySelectedItemIcon);
        historyTextView = view.findViewById(R.id.historySelectedLabel);
        selectedDivider = view.findViewById(R.id.selectedDivider);

        //Set Colors for selected.
        historyImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_history_selected));
        historyTextView.setTextColor(getResources().getColor(R.color.selected_blue));
        selectedDivider.setBackgroundColor(getResources().getColor(R.color.selected_blue));

        return view;
    }
}