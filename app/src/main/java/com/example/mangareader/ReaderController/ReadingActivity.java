package com.example.mangareader.ReaderController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangareader.R;

public class ReadingActivity extends AppCompatActivity {

    private ImageView bookImage;
    private TextView test;
    private SwipeListener swipeListener;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        //Find UI elements.
        bookImage = findViewById(R.id.bookImage);
        constraintLayout = findViewById(R.id.readingConstraintLayout);

        //Initialize swipe listener.
        swipeListener = new SwipeListener(constraintLayout);
    }

    public static Intent getIntent(Context context) {
        Intent readingActivityIntent = new Intent(context, ReadingActivity.class);

        return readingActivityIntent;
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
                                //Check if fling occurred.
                                if (Math.abs(xDiff) > Math.abs(yDiff))
                                {
                                    //If x is greater than y.
                                    if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold)
                                    {
                                        if (xDiff > 0) {
                                            //Means we have swiped right.
                                            //Go to next image here.
                                        }
                                        else
                                        {
                                            //Means we have swiped left.
                                            //Go to previous image here.
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