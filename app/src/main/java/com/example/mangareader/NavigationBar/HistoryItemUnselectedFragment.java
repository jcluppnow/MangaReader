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

import com.example.mangareader.DataControllers.NavigationSingleton;
import com.example.mangareader.HistoryPage.HistoryActivity;
import com.example.mangareader.MainMenu.ListTypeSelectedDefaultFragment;
import com.example.mangareader.MainMenu.ListTypeUnselectedDownloadedFragment;
import com.example.mangareader.MainMenu.MainActivity;
import com.example.mangareader.MorePage.MoreActivity;
import com.example.mangareader.R;

public class HistoryItemUnselectedFragment extends Fragment {

    private ImageView historyImageView;
    private TextView historyTextView;
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
        View view = inflater.inflate(R.layout.fragment_history_item, container, false);

        //Find UI elements.
        historyImageView = view.findViewById(R.id.historySelectedItemIcon);
        historyTextView = view.findViewById(R.id.historySelectedLabel);
        selectedDivider = view.findViewById(R.id.selectedDivider);

        //As this item is unselected, hide the selected divider.
        view.findViewById(R.id.selectedDivider).setVisibility(View.INVISIBLE);

        //Add Event Listeners.
        setupListeners(view);

        return view;
    }

    private void setupListeners(View view) {
        //Click Listener.
        view.setOnClickListener(v -> selectHistoryEventHandler());

        //Touch Listener.
        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                view.setBackgroundColor(getResources().getColor(R.color.highlightBox));

                historyImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_history_selected_hover));
                historyTextView.setTextColor(getResources().getColor(R.color.darkHover));
                selectedDivider.setBackgroundColor(getResources().getColor(R.color.darkHover));
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                //Reset colour.
                view.setBackgroundColor(getResources().getColor(R.color.header_blue));
                historyImageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_history));
                historyTextView.setTextColor(getResources().getColor(R.color.white));
                selectedDivider.setBackgroundColor(getResources().getColor(R.color.white));

                //Start Activity.
                //Check if we can use transition animation.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    //Apply activity transition.
                    startActivity(HistoryActivity.getIntent(getActivity()));

                    //Do a transition. Enter/Exit animation required as follows.
                    applyTransition();
                }
                else
                {
                    //Swap without transition.
                    startActivity(HistoryActivity.getIntent(getActivity()));
                }
            }

            return false;
        });
    }

    private void selectHistoryEventHandler()
    {
        //Get Fragment Manager from Parent Activity.
        FragmentManager fragmentManager = getParentFragmentManager();

        //Mark the Library Item as unselected.
        fragmentManager.beginTransaction().replace(R.id.libraryNavigationItem, new LibraryItemUnselectedFragment()).commit();

        //Mark the History Item as selected.
        fragmentManager.beginTransaction().replace(R.id.historyNavigationItem, new HistoryItemSelectedFragment()).commit();

        //Mark the More Item as unselected.
        fragmentManager.beginTransaction().replace(R.id.moreNavigationItem, new MoreItemUnselectedFragment()).commit();
    }

    private void applyTransition()
    {
        NavigationSingleton navigationSingleton = NavigationSingleton.getInstance();

        if (navigationSingleton.isCurrentItemLibrary())
        {
            //Update singleton to match that the item has changed to history.
            navigationSingleton.changeToHistory();

            //Library -> History = Rightwards direction.
            //Animation for the entering activity is first, followed by the animation for the exiting activity.
            //Exiting activity should animate leaving from the left.
            //The entering activity should be animated entering from the right.
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else
        {
            //Update singleton to match that the item has changed to history.
            navigationSingleton.changeToHistory();

            //More -> History = Leftwards direction.
            //Animation for the entering activity is first, followed by the animation for the exiting activity.
            //Exiting activity should animate leaving from the right.
            //The entering activity should be animated entering from the left.
            getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }
}