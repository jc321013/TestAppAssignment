package com.example.jc321013.test;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnSystemUiVisibilityChangeListener {


    private Button quit;
    private Button data;
    private Context context;
    private TextView settingsText;


    private View decorView;
    private ActionBar actionBar;
    private GestureDetectorCompat gestureDetector;

    private static final int NO_CONTROLS =
            View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

    private static final int FULLSCREEN =
            View.SYSTEM_UI_FLAG_IMMERSIVE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        actionBar = getSupportActionBar();
        gestureDetector = new GestureDetectorCompat(this, new Settings.GestureHandler());
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(FULLSCREEN);
        decorView.setOnSystemUiVisibilityChangeListener(this);

        settingsText = (TextView) findViewById(R.id.settingsText);
        settingsText.setText("Welcome to the Settings Page");


        context = this;
        quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainScreen.class);
                startActivity(intent);
            }
        });

        context = this;
        data = (Button) findViewById(R.id.data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccelarometreData.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuItem = getMenuInflater();
        menuItem.inflate(R.menu.main_page, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.actionBarHighScore) {
            Intent intent = new Intent(this, HighestScoreActivity.class);
            startActivity(intent);

        } else if (i == R.id.actionBarHome) {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleControls() {
        int flags = decorView.getSystemUiVisibility();
        flags ^= NO_CONTROLS;
        decorView.setSystemUiVisibility(flags);
    }

    private class GestureHandler extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
            toggleControls();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onSystemUiVisibilityChange(int visibility) {
        if (actionBar == null) return;
        switch (visibility) {
            case NO_CONTROLS:
                actionBar.hide();
                break;
            default:
                actionBar.show();
        }
    }

    public void tipButtonHandler(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Take your time to read the questions"
                + System.getProperty("line.separator") + "Try to beat your high score"
                + System.getProperty("line.separator") + "Click About Quiz to know what the quiz is about";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void aboutButtonHandler(View view) {
        Context context = getApplicationContext();
        CharSequence text = "This quiz raises awareness for Natural Disasters"
                + System.getProperty("line.separator") + "This educational game will teach you about the main natural disasters!"
                + System.getProperty("line.separator") + "If you need tips click the Tips button";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
