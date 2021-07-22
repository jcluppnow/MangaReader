package com.example.mangareader.MorePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mangareader.R;

public class MoreHeaderFragment extends Fragment {

    private ImageView notificationsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_header, container, false);

        //Find UI elements.
        notificationsButton = view.findViewById(R.id.notificationsButton);

        //Add Event Listeners.
        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNotificationsEventHandler();
            }
        });

        return view;
    }

    private void selectNotificationsEventHandler()
    {

    }
}