package com.example.mangareader.MorePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mangareader.BookRecycler.BookRecyclerFragment;
import com.example.mangareader.MainMenu.HeaderFragment;
import com.example.mangareader.MainMenu.ListTypeSelectedDefaultFragment;
import com.example.mangareader.MainMenu.ListTypeUnselectedDownloadedFragment;
import com.example.mangareader.NavigationBar.HistoryItemUnselectedFragment;
import com.example.mangareader.NavigationBar.LibraryItemSelectedFragment;
import com.example.mangareader.NavigationBar.LibraryItemUnselectedFragment;
import com.example.mangareader.NavigationBar.MoreItemSelectedFragment;
import com.example.mangareader.NavigationBar.MoreItemUnselectedFragment;
import com.example.mangareader.R;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        //Fetch fragment manager.
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            //Add More Header Fragment.
            fragmentManager.beginTransaction().add(R.id.moreHeaderFrameLayout, new MoreHeaderFragment()).commit();

            //Add Configuration Information Fragment.
            fragmentManager.beginTransaction().add(R.id.configurationFrameLayout, new SettingsFragment(), "HistoryNavigationItemSelected").commit();

            //Add more navigation bar items.
            //Add More Navigation Bar Item.
            fragmentManager.beginTransaction().add(R.id.moreNavigationItem, new MoreItemSelectedFragment(), "MoreNavigationItemSelected").commit();

            //Add Library Navigation Bar Item.
            fragmentManager.beginTransaction().add(R.id.libraryNavigationItem, new LibraryItemUnselectedFragment(), "LibraryNavigationItem").commit();

            //Add History Navigation Bar Item.
            fragmentManager.beginTransaction().add(R.id.historyNavigationItem, new HistoryItemUnselectedFragment(), "HistoryNavigationItem").commit();
        }
    }

    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent(context, MoreActivity.class);

        return intent;
    }
}