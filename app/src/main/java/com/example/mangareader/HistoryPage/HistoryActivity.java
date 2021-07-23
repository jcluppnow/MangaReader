package com.example.mangareader.HistoryPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mangareader.NavigationBar.HistoryItemSelectedFragment;
import com.example.mangareader.NavigationBar.HistoryItemUnselectedFragment;
import com.example.mangareader.NavigationBar.LibraryItemUnselectedFragment;
import com.example.mangareader.NavigationBar.MoreItemSelectedFragment;
import com.example.mangareader.NavigationBar.MoreItemUnselectedFragment;
import com.example.mangareader.R;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (savedInstanceState == null)
        {
            //Get Fragment Manager.
            FragmentManager fragmentManager = getSupportFragmentManager();

            //Attach Fragments.
            //Add the header fragment.
            fragmentManager.beginTransaction().add(R.id.historyHeaderFrameLayout, new HistoryHeaderFragment()).commit();

            //Add the history console fragment.
            fragmentManager.beginTransaction().add(R.id.historyInformationFrameLayout, new EmptyHistoryFragment()).commit();

            //Add More Navigation Bar Item.
            fragmentManager.beginTransaction().add(R.id.moreNavigationItem, new MoreItemUnselectedFragment(), "MoreNavigationItemSelected").commit();

            //Add Library Navigation Bar Item.
            fragmentManager.beginTransaction().add(R.id.libraryNavigationItem, new LibraryItemUnselectedFragment(), "LibraryNavigationItem").commit();

            //Add History Navigation Bar Item.
            fragmentManager.beginTransaction().add(R.id.historyNavigationItem, new HistoryItemSelectedFragment(), "HistoryNavigationItem").commit();
        }
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, HistoryActivity.class);

        return intent;
    }
}