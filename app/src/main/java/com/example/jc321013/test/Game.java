package com.example.jc321013.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    private MultiChoiceQuestionLibrary mQuestionLibrary = new MultiChoiceQuestionLibrary();
    private TextView scoreView;
    private TextView questionView;
    private Button choice1;
    private Button choice2;
    private Button choice3;
    private Button quitGame;
    private Context context;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // setup screen for the first question with four alternative to answer
        scoreView = (TextView) findViewById(R.id.score);
        questionView = (TextView) findViewById(R.id.question);
        choice1 = (Button) findViewById(R.id.choice1);
        choice2 = (Button) findViewById(R.id.choice2);
        choice3 = (Button) findViewById(R.id.choice3);

        mQuestionLibrary.initQuestions(getApplicationContext());
        updateQuestion();
        // show current total score for the user
        updateScore(mScore);

        // when the user clicks the quit button it takes them back to the mainscreen
        context = this;
        quitGame = (Button) findViewById(R.id.quitGame);
        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainScreen.class);
                startActivity(intent);
            }
        });

    }

    private void updateQuestion() {
        if (mQuestionNumber < mQuestionLibrary.getLength()) {
            // This sets the new questions
            questionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            // This sets the new answers for the new question
            choice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            choice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            choice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        } else {
            Toast.makeText(Game.this, "The was the final question, yay :)", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Game.this, HighestScoreActivity.class);
            // pass the current score to the second screen
            intent.putExtra("score", mScore);
            startActivity(intent);
        }
    }

    // show current total score for the user
    private void updateScore(int point) {
        scoreView.setText("" + mScore + "/" + mQuestionLibrary.getLength());
    }

    public void onClick(View view) {
        Button answer = (Button) view;
        // if the user answers correct the score is updated
        if (answer.getText().equals(mAnswer)) {
            mScore = mScore + 1;
            //if the user answers correctly "correct" is displayed
            Toast.makeText(Game.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else
            //if the user answers incorrectly "wrong" is displayed
            Toast.makeText(Game.this, "Wrong!", Toast.LENGTH_SHORT).show();
        // show current total score for the user
        updateScore(mScore);
        // once the current question is answered, the next is displayed
        updateQuestion();
    }


    //creates gestures for the user
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        // when a down event occurs from the user it displays "onDown"
        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        // when a fling event occurs from the user it displays "onFling"
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
            return true;
        }

        // when the user scolls it displays "onScroll"
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                float distanceY) {
            Log.d(DEBUG_TAG, "onScroll: " + e1.toString() + e2.toString());
            return true;
        }
    }


}
