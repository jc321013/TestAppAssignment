package com.example.jc321013.test;


import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;



public class MainScreen extends AppCompatActivity implements View.OnSystemUiVisibilityChangeListener
{

    private Button button1;
    private Button highScore;
    private TextView textView;
    private Button settings;
    private TextView textView2;
    private Context context;

    private static final int AUTHENTICATE = 1;
    Twitter twitter = TwitterFactory.getSingleton();

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
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        gestureDetector = new GestureDetectorCompat(this, new GestureHandler());
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(FULLSCREEN);
        decorView.setOnSystemUiVisibilityChangeListener(this);




        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText("Natural Disaster Awareness Test! Begin Test :)");

        textView2 = (TextView) findViewById(R.id.text_view2);
        textView2.setMovementMethod(new ScrollingMovementMethod());

        context = this;
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starts the game when new game is clicked, places user onto the multipul choice screen

                Intent intent = new Intent(context, Game.class);
                startActivity(intent);
            }
        });

        context = this;
        settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Settings.class);
                startActivity(intent);
            }
        });

        context = this;
        highScore = (Button) findViewById(R.id.highScore);
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HighestScoreActivity.class);
                startActivity(intent);
            }
        });


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





    public void authorise(View view) {
        Intent intent = new Intent(this, Authenticate.class);
        startActivityForResult(intent, AUTHENTICATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == AUTHENTICATE && resultCode == RESULT_OK) {
            Background.run(new Runnable() {
                @Override
                public void run() {
                    String token = data.getStringExtra("access token");
                    String secret = data.getStringExtra("access token secret");
                    AccessToken accessToken = new AccessToken(token, secret);
                    twitter.setOAuthAccessToken(accessToken);

                    Query query = new Query("@twitterapi");
                    QueryResult result;
                    try {
                        twitter.updateStatus("everything is fine!");
                        result = twitter.search(query);
                    } catch (final Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView2.setText(e.toString());
                            }
                        });
                        return;
                    }

                    // convert tweets into text
                    final StringBuilder builder = new StringBuilder();
                    for (Status status : result.getTweets()) {
                        builder.append(status.getUser().getScreenName())
                                .append(" said ")
                                .append(status.getText())
                                .append("\n");
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setText(builder.toString().trim());
                        }
                    });
                }
            });
        }

    }

//    public void onStart() {
//        super.onStart();
//    }
//
//    public void onPause() {
//        super.onPause();
//    }






}
