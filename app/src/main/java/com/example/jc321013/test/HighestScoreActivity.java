package com.example.jc321013.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighestScoreActivity extends AppCompatActivity {
    private Button startAgain;
    private Context context;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        TextView txtScore = (TextView) findViewById(R.id.textScore);
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        // display current score
        txtScore.setText("Your score: " + score);

        // use Shared preferences to save the best score
        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore",0);
        if(highscore>= score)
            txtHighScore.setText("High score: "+highscore);
        else
        {
            txtHighScore.setText("New highscore: "+score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }

        context = this;
        startAgain = (Button) findViewById(R.id.startAgain);
        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        if (i == R.id.actionBarSettings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);

        } else if (i == R.id.actionBarHome) {
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
