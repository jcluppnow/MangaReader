package com.example.mangareader.ReaderController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mangareader.R;

import java.io.File;

public class ReadingActivity extends AppCompatActivity {

    private ImageView bookImage;
    private SwipeListener swipeListener;
    private ConstraintLayout constraintLayout;
    private int currentPage;
    private boolean popupEnabled;
    private NavigationPopupFragment popupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        //Set the default page.
        currentPage = 1;

        //Set the default status for popup which is disabled. Popup will be hidden initially.
        popupEnabled = false;

        //Find UI elements.
        bookImage = findViewById(R.id.bookImage);
        constraintLayout = findViewById(R.id.readingConstraintLayout);

        //Works based on the assumption images are stored in drawables.
        bookImage.setImageResource(R.drawable.o1);

        bookImage.setOnClickListener(v -> selectImageEventHandler());

        //Initialize swipe listener.
        //swipeListener = new SwipeListener(constraintLayout);
        swipeListener = new SwipeListener(bookImage);

        //Create Popup Fragment.
        popupFragment = new NavigationPopupFragment();

        //Add Popup Fragment then hide the fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.popupFrameLayout, popupFragment).hide(popupFragment).commit();
    }

    public static Intent getIntent(Context context) {
        Intent readingActivityIntent = new Intent(context, ReadingActivity.class);

        return readingActivityIntent;
    }

    private void selectImageEventHandler()
    {
        //Get the fragment manager.
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (popupEnabled)
        {
            //Disable the popup by hiding it.
            fragmentManager.beginTransaction().hide(popupFragment).commit();
            //fragmentManager.beginTransaction().remove(popupFragment).commit();

            //Mark the popup as hidden.
            popupEnabled = false;
        }
        else
        {
            //Enable the popup by showing it.
            fragmentManager.beginTransaction().show(popupFragment).commit();
            //fragmentManager.beginTransaction().add(R.id.popupFrameLayout, popupFragment).commit();

            //Mark the popup as shown.
            popupEnabled = true;
        }
    }

    private void setNextImage()
    {
        //Works based on the assumption images are stored in drawables.
        currentPage++;
        String resourceName = "o" + Integer.valueOf(currentPage);

        //Fetch the drawable identifier using the above string.
        int resourceID = getResources().getIdentifier(resourceName, "drawable", getPackageName());

        //Set the image to the next page.
        bookImage.setImageResource(resourceID);
    }

    private void startFlipAnimation(int nextImageID)
    {
        bookImage.setRotationY(0f);
        bookImage.animate().rotationY(90f).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                //Update with next image.
                bookImage.setImageResource(nextImageID);

                bookImage.setRotationY(270f);

                bookImage.animate().rotationY(360f).setListener(null);
            }
        });
    }

    private void loadPreviousImage()
    {
        if (currentPage > 1)
        {
            //Works based on the assumption images are stored in drawables.
            currentPage--;
            String resourceName = "o" + Integer.valueOf(currentPage);

            //Fetch the drawable identifer using the above string.
            int resourceID = getResources().getIdentifier(resourceName, "drawable", getPackageName());

            //Update with previous image.
            bookImage.setImageResource(resourceID);
        }
    }

    public void loadNextImage(Context c, int resourceID) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                setNextImage();
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                bookImage.startAnimation(anim_in);
            }
        });
        bookImage.startAnimation(anim_out);
    }

    private class SwipeListener implements View.OnTouchListener {

        private GestureDetector gestureDetector;

        public SwipeListener(View view)
        {
            int threshold = 100;
            int velocity_threshold = 100;

            //Initialize simple gesture listener.
            GestureDetector.SimpleOnGestureListener listener =
                    new GestureDetector.SimpleOnGestureListener() {

                        @Override
                        public boolean onDoubleTap (MotionEvent event)
                        {
                            selectImageEventHandler();
                            return true;
                        }

                        @Override
                        public boolean onDown(MotionEvent event)
                        {
                            return true;
                        }

                        @Override
                        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                            //Calculate difference between x and y.
                            float xDiff = e2.getX() - e1.getX();
                            float yDiff = e2.getY() - e1.getY();

                            try
                            {
                                System.out.println("Swipe Listener run");
                                //Check if fling occurred.
                                if (Math.abs(xDiff) > Math.abs(yDiff))
                                {
                                    //If x is greater than y.
                                    if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold)
                                    {
                                        if (xDiff > 0) {
                                            //Means we have swiped right.
                                            //Go to next image here.
                                            loadPreviousImage();
                                        }
                                        else
                                        {
                                            //Means we have swiped left.
                                            //Go to next image here.
                                            //Works based on the assumption images are stored in drawables.
                                            currentPage++;
                                            String resourceName = "o" + Integer.valueOf(currentPage);

                                            //Fetch the drawable identifier using the above string.
                                            int resourceID = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                                            loadNextImage(getApplicationContext(), resourceID);
                                        }
                                    }
                                }
                                else
                                {
                                    //If y is greater than x.
                                    if (Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold)
                                    {
                                        if (yDiff > 0)
                                        {
                                            //Means we have swiped down.
                                            //Just in case we want vertical scrolling.
                                        }
                                        else
                                        {
                                            //Means we have swiped up.
                                            //Just in case we want vertical scrolling.
                                        }
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                            return false;
                        }
                    };

            //Initialize Gesture Detector.
            gestureDetector = new GestureDetector(getApplicationContext(), listener);

            //Set listener on view.
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //Return gesture event.
            return gestureDetector.onTouchEvent(event);
        }
    }
}