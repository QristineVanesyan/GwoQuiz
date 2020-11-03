package com.example.gwoquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button prevButton;
    private TextView textView;
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

        Log.d(TAG, "Create");

        textView = (TextView) findViewById(R.id.textView);
        updateQuestion();
        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = (counter + 1) % questions.length;
                updateQuestion();
            }
        });
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                //Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_LONG).show();
                checkAnswer(true);
                counter = (counter + 1) % questions.length;
                updateQuestion();

            }
        });

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_LONG).show();
                checkAnswer(false);
                counter = (counter + 1) % questions.length;
                updateQuestion();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, counter);
    }

    private void updateQuestion() {
        int question = questions[counter].getQuestionId();
        textView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = questions[counter].isAnswer();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
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
        Log.d(TAG, "Destroy\n");
    }

}