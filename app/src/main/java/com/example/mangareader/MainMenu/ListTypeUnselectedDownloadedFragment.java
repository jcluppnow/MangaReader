package com.example.mangareader.MainMenu;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mangareader.BookRecycler.EmptyRecyclerFragment;
import com.example.mangareader.HistoryPage.HistoryActivity;
import com.example.mangareader.R;

public class ListTypeUnselectedDownloadedFragment extends Fragment {

    private TextView listTypeTextView;
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
        View view = inflater.inflate(R.layout.fragment_list_type_downloaded, container, false);

        //Find UI Elements.
        listTypeTextView = view.findViewById(R.id.listTypeSelectedLabel);
        selectedDivider = view.findViewById(R.id.selectedDivider);

        //As this item is unselected, hide the selected divider.
        view.findViewById(R.id.selectedDivider).setVisibility(View.INVISIBLE);

        //Add Event Listeners.
        setupEventHandlers(view);

        return view;
    }

    private void setupEventHandlers(View view)
    {
        view.setOnClickListener(v -> selectDownloadedEventHandler());

        //Touch Listener.
        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                view.setBackgroundColor(getResources().getColor(R.color.highlightBox));
                listTypeTextView.setTextColor(getResources().getColor(R.color.darkHover));
                selectedDivider.setBackgroundColor(getResources().getColor(R.color.darkHover));
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                //Reset colour.
                view.setBackgroundColor(getResources().getColor(R.color.header_blue));
                listTypeTextView.setTextColor(getResources().getColor(R.color.white));
                selectedDivider.setBackgroundColor(getResources().getColor(R.color.white));
                selectDownloadedEventHandler();
            }

            return false;
        });
    }

    private void selectDownloadedEventHandler()
    {
        //Fetch the Activity Fragment Manager.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Mark the Downloaded option as selected.
        fragmentManager.beginTransaction().replace(R.id.downloadedListTypeFragment, new ListTypeSelectedDownloadedFragment()).commit();

        //Mark the Default option as unselected.
        fragmentManager.beginTransaction().replace(R.id.defaultListTypeFragment, new ListTypeUnselectedDefaultFragment()).commit();

        //Add the downloaded recycler. TBD.
        fragmentManager.beginTransaction().replace(R.id.booksRecyclerFragmentFrameLayout, new EmptyRecyclerFragment()).commit();
    }
}