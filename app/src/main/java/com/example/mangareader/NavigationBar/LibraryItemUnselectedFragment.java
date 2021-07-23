package com.example.mangareader.NavigationBar;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangareader.MorePage.MoreActivity;
import com.example.mangareader.R;

public class LibraryItemUnselectedFragment extends Fragment {

    private ImageView libraryImageView;
    private TextView libraryTextView;
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
        View view =  inflater.inflate(R.layout.fragment_library_item, container, false);

        //Find UI elements.
        libraryImageView = view.findViewById(R.id.librarySelectedItemIcon);
        libraryTextView = view.findViewById(R.id.librarySelectedLabel);
        selectedDivider = view.findViewById(R.id.selectedDivider);

        //As this item is unselected, hide the selected divider.
        view.findViewById(R.id.selectedDivider).setVisibility(View.INVISIBLE);

        //Add Event Listeners.
        setupListeners(view);

        return view;
    }

    private void setupListeners(View view) {
        //Click Listener.
        view.setOnClickListener(v -> selectLibraryEventHandler());

        //Touch Listener.
        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                view.setBackgroundColor(getResources().getColor(R.color.lighter_background_header));
                libraryImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_library_selected_hover));
                libraryTextView.setTextColor(getResources().getColor(R.color.darkHover));
                selectedDivider.setBackgroundColor(getResources().getColor(R.color.darkHover));
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                //Reset colour.
                view.setBackgroundColor(getResources().getColor(R.color.header_blue));
                libraryImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_library));
                libraryTextView.setTextColor(getResources().getColor(R.color.white));
                selectedDivider.setBackgroundColor(getResources().getColor(R.color.white));
            }

            return false;
        });
    }

    private void selectLibraryEventHandler()
    {
        //Get Fragment Manager from Parent Activity.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Mark the Library Item as selected.
        fragmentManager.beginTransaction().replace(R.id.libraryNavigationItem, new LibraryItemSelectedFragment()).commit();

        //Mark the History Item as unselected.
        fragmentManager.beginTransaction().replace(R.id.historyNavigationItem, new HistoryItemUnselectedFragment()).commit();

        //Mark the More Item as unselected.
        fragmentManager.beginTransaction().replace(R.id.moreNavigationItem, new MoreItemUnselectedFragment()).commit();
    }
}