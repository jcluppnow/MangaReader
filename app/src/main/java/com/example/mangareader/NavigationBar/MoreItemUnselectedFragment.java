package com.example.mangareader.NavigationBar;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangareader.MorePage.MoreActivity;
import com.example.mangareader.R;

public class MoreItemUnselectedFragment extends Fragment {

    private ImageView moreImageView;
    private TextView moreTextView;
    private View selectedDivider;

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
        View view = inflater.inflate(R.layout.fragment_more_item, container, false);

        //Find UI elements.
        moreImageView = view.findViewById(R.id.moreSelectedItemIcon);
        moreTextView = view.findViewById(R.id.moreSelectedLabel);
        selectedDivider = view.findViewById(R.id.selectedDivider);

        //As this item is unselected, hide the selected divider.
        view.findViewById(R.id.selectedDivider).setVisibility(View.INVISIBLE);

        //Add Event Listeners.
        setupListeners(view);

        return view;
    }

    private void setupListeners(View view) {
        //Click Listener.
        view.setOnClickListener(v -> selectMoreEventHandler());

        //Touch Listener.
        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                view.setBackgroundColor(getResources().getColor(R.color.lighter_background_header));
                moreImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_more_selected_hover));
                moreTextView.setTextColor(getResources().getColor(R.color.darkHover));
                selectedDivider.setBackgroundColor(getResources().getColor(R.color.darkHover));
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                //Reset colour.
                view.setBackgroundColor(getResources().getColor(R.color.header_blue));
                moreImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_more));
                moreTextView.setTextColor(getResources().getColor(R.color.white));
                selectedDivider.setBackgroundColor(getResources().getColor(R.color.white));

                //Start Activity.
                //Check if we can use transition animation.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    //Apply activity transition.
                    startActivity(MoreActivity.getIntent(getActivity()));

                    //Do a transition. Enter/Exit animation required as follows.
                    getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
                else
                {
                   //Swap without transition.
                    startActivity(MoreActivity.getIntent(getActivity()));
                }
            }

            return false;
        });
    }

    private void selectMoreEventHandler()
    {
        startActivity(MoreActivity.getIntent(getActivity()));
    }
}