package com.example.mangareader.NavigationBar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangareader.R;

public class MoreItemSelectedFragment extends Fragment {
    private ImageView moreImageView;
    private TextView moreTextView;
    private View selectedDivider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_item, container, false);

        //Find UI elements.
        moreImageView = view.findViewById(R.id.moreSelectedItemIcon);
        moreTextView = view.findViewById(R.id.moreSelectedLabel);
        selectedDivider = view.findViewById(R.id.selectedDivider);

        //Set Colors for selected.
        moreImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_more_selected));
        moreTextView.setTextColor(getResources().getColor(R.color.selected_blue));
        selectedDivider = view.findViewById(R.id.selectedDivider);

        return view;
    }
}