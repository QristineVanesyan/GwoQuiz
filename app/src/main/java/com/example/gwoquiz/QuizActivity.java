package com.example.gwoquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button prevButton;
    private TextView textView;
    private int score;
    private int counter = 0;
    private Question[] questions = new Question[]{
            new Question(R.string.q1, true),
            new Question(R.string.q2, true),
            new Question(R.string.q3, false),
            new Question(R.string.q4, false),
            new Question(R.string.q5, true),
            new Question(R.string.q6, true),/*aynqan el chisht tarberak che modeli tvyalneri pahpanman, heshtutyan hamar stexcvac e kontrollerum.*/
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(KEY_SCORE, 0);
        }
        

        textView = (TextView) findViewById(R.id.textView);

        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trueButton.setEnabled(true);
                Log.i(TAG, "true");
                falseButton.setEnabled(true);
                Log.i(TAG, "true");
                counter = (counter + 1) % questions.length;
                updateQuestion();
            }
        });
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trueButton.setEnabled(true);
                Log.i(TAG, "true");
                falseButton.setEnabled(true);
                counter = (counter + 1) % questions.length;

                updateQuestion();
            }
        });
     /*   prevButton = findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = ((counter - 1)+questions.length)%questions.length;
                updateQuestion();
            }
        });*/
        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trueButton.setEnabled(false);
                Log.i(TAG, "false");
                falseButton.setEnabled(false);
                checkAnswer(true);
                counter = (counter + 1) % questions.length;
                updateQuestion();

            }
        });

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trueButton.setEnabled(false);
                Log.i(TAG, "false");
                falseButton.setEnabled(false);
                checkAnswer(false);
                counter = (counter + 1) % questions.length;
                updateQuestion();

            }
        });
        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        Log.i(TAG, score + "");
        savedInstanceState.putInt(KEY_INDEX, counter);
        savedInstanceState.putInt(KEY_SCORE, score);
    }

    private void updateQuestion() {
        int question = questions[counter].getQuestionId();
        textView.setText(question);
        trueButton.setEnabled(true);
        Log.i(TAG, "true");
        falseButton.setEnabled(true);
        /*
         mFalseButton.setVisibility(View.VISIBLE);
         mTrueButton.setVisibility(View.VISIBLE);
         for (Integer i = 0; i < mQuestionAsked.size(); i++) {
                if (mCurrentIndex == mQuestionAsked.get(i)) {
                    mFalseButton.setVisibility(View.INVISIBLE);
                    mTrueButton.setVisibility(View.INVISIBLE);
                }
            }
   */
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = questions[counter].isAnswer();
        int messageResId = 0;
        int scoreID = 0;
        if (userPressedTrue == answerIsTrue) {
            score++;

            messageResId = R.string.correct_toast;
            scoreID = R.string.score;
        } else {
            messageResId = R.string.incorrect_toast;
            scoreID = R.string.score;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "" + counter);
        if (counter == questions.length - 1) {
            Toast.makeText(this, (score*100 / questions.length) + "%", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resume\n");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Stop\n");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Start\n");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}