package com.example.jc321013.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    private Button quit;
    private Context context;
    private ActionBar actionBar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
            context = this;
            quit = (Button) findViewById(R.id.quit);
            quit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainScreen.class);
                    startActivity(intent);
                }
            });
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_page, menu);
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
    }
