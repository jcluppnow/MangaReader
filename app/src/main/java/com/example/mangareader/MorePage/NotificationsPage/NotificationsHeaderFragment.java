package com.example.mangareader.MorePage.NotificationsPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mangareader.R;

public class NotificationsHeaderFragment extends Fragment {

    private ImageView backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications_header, container, false);

        //Find UI elements.
        backButton = view.findViewById(R.id.backButton);

        //Add Event Listeners.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBackButtonEventHandler();
            }
        });

        return view;
    }

    private void selectBackButtonEventHandler()
    {
        //Finish the activity.
        getActivity().finish();
    }
}