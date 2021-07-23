package com.example.mangareader.MorePage.NotificationsPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mangareader.R;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //Get Fragment Manager.
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Add Header Fragment.
        fragmentManager.beginTransaction().add(R.id.notificationsHeaderFrameLayout, new NotificationsHeaderFragment()).commit();

        //Add Notifications Body Fragment.
        fragmentManager.beginTransaction().add(R.id.notificationsConsoleFrameLayout, new EmptyNotificationsFragment()).commit();
    }

    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent(context, NotificationsActivity.class);

        return intent;
    }
}