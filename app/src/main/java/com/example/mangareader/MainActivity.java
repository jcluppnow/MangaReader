package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.mangareader.NavigationBar.HistoryItemUnselectedFragment;
import com.example.mangareader.NavigationBar.LibraryItemSelectedFragment;
import com.example.mangareader.NavigationBar.MoreItemUnselectedFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize fragments.
        ListTypeSelectedDefaultFragment defaultListTypeFragment = new ListTypeSelectedDefaultFragment();
        ListTypeUnselectedDownloadedFragment downloadedListTypeFragment = new ListTypeUnselectedDownloadedFragment();

        //Fetch fragment manager.
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null)
        {
            //Add Header Fragment.
            fragmentManager.beginTransaction().add(R.id.defaultListTypeFragment, new HeaderFragment());

            //Add Top List Types.
            //Add Default List Type Fragment.
            fragmentManager.beginTransaction().add(R.id.defaultListTypeFragment, defaultListTypeFragment, "DefaultListTypeSelected").commit();

            //Add Downloaded List Type Fragment.
            fragmentManager.beginTransaction().add(R.id.downloadedListTypeFragment, downloadedListTypeFragment, "DownloadedListTypeUnselected").commit();

            //Add Recycler Fragment.

            //Add Bottom Navigation Item Fragments.
            //Add Library Navigation Item Fragment.
            fragmentManager.beginTransaction().add(R.id.libraryNavigationItem, new LibraryItemSelectedFragment(), "LibraryNavigationItemSelected").commit();

            //Add History Navigation Item Fragment.
            fragmentManager.beginTransaction().add(R.id.historyNavigationItem, new HistoryItemUnselectedFragment(), "HistoryNavigationItemSelected").commit();

            //Add More Navigation Item Fragment.
            fragmentManager.beginTransaction().add(R.id.moreNavigationItem, new MoreItemUnselectedFragment(), "MoreNavigationItemSelected").commit();
        }
    }
}