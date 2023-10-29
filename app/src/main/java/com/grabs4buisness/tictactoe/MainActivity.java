package com.grabs4buisness.tictactoe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.grabs4buisness.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    int flag = 0;
    int count = 0;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rematch.setOnClickListener(v -> new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.drawable.ttt)
                .setTitle("Restart")
                .setMessage("Are you really want to restart?")
                .setPositiveButton("Yes",((dialog, which) -> restart()))
                .setNegativeButton("No",((dialog, which) -> dialog.dismiss()))
                .create()
                .show());
    }

    public void check(View view) {
        AppCompatButton btnCurrent = (AppCompatButton) view;
        binding.rematch.setVisibility(View.VISIBLE);

        if(btnCurrent.getText().toString().equals("")) {
            count++;

            if (flag == 0) {
                btnCurrent.setText("X");
                flag = 1;
            } else {
                btnCurrent.setText("O");
                flag = 0;
            }

            if (count > 4) {
                b1 = binding.btn1.getText().toString();
                b2 = binding.btn2.getText().toString();
                b3 = binding.btn3.getText().toString();
                b4 = binding.btn4.getText().toString();
                b5 = binding.btn5.getText().toString();
                b6 = binding.btn6.getText().toString();
                b7 = binding.btn7.getText().toString();
                b8 = binding.btn8.getText().toString();
                b9 = binding.btn9.getText().toString();

                //Conditions
                if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
                    animateLayout(binding.topLine,b1);
                } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
                    animateLayout(binding.middleLine,b4);
                } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
                    animateLayout(binding.bottomLine,b7);
                } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
                    animateLayout(binding.leftLine,b1);
                } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
                    animateLayout(binding.middleVerLine,b2);
                } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
                    animateLayout(binding.rightLine,b3);
                } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
                    animateLayout(binding.leftRightDiagonal,b1);
                } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
                    animateLayout(binding.rightLeftDiagonal,b3);
                }
                else if(count==9)
                {
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.ttt)
                            .setTitle("Tic Tac Toe")
                            .setMessage("Match has drawn")
                            .setPositiveButton("Restart",((dialog, which) -> restart()))
                            .setNegativeButton("Show",((dialog, which) -> dialog.dismiss()))
                            .setCancelable(false)
                            .create()
                            .show();
                }

            }
        }

    }

    private void animateLayout(View view, String winner) {
        // Create an ObjectAnimator to animate the alpha property for fading in
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        animator.setDuration(2500); // Adjust the duration as needed

        // Add a listener to handle visibility changes
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                showWinner(winner);
            }
        });

        // Start the animation
        animator.start();
    }


    private void showWinner(String winner)
    {
            new AlertDialog.Builder(MainActivity.this)
                    .setIcon(R.drawable.ttt)
                    .setTitle("Tic Tac Toe")
                    .setMessage("Winner is: "+winner)
                    .setPositiveButton("Restart",((dialog, which) -> restart()))
                    .setNegativeButton("Show", (dialog, which) -> {
                        freezeButtons();
                        dialog.dismiss();
                    })
                    .setCancelable(false)
                    .create()
                    .show();
    }

    private void restart()
    {
        binding.btn1.setText("");
        binding.btn2.setText("");
        binding.btn3.setText("");
        binding.btn4.setText("");
        binding.btn5.setText("");
        binding.btn6.setText("");
        binding.btn7.setText("");
        binding.btn8.setText("");
        binding.btn9.setText("");
        count=0;
        flag=0;
        binding.rematch.setVisibility(View.INVISIBLE);

        binding.topLine.setVisibility(View.INVISIBLE);
        binding.middleLine.setVisibility(View.INVISIBLE);
        binding.bottomLine.setVisibility(View.INVISIBLE);
        binding.leftLine.setVisibility(View.INVISIBLE);
        binding.middleVerLine.setVisibility(View.INVISIBLE);
        binding.rightLine.setVisibility(View.INVISIBLE);
        binding.leftRightDiagonal.setVisibility(View.INVISIBLE);
        binding.rightLeftDiagonal.setVisibility(View.INVISIBLE);

        binding.btn1.setEnabled(true);
        binding.btn2.setEnabled(true);
        binding.btn3.setEnabled(true);
        binding.btn4.setEnabled(true);
        binding.btn5.setEnabled(true);
        binding.btn6.setEnabled(true);
        binding.btn7.setEnabled(true);
        binding.btn8.setEnabled(true);
        binding.btn9.setEnabled(true);
    }
    private void freezeButtons()
    {
        binding.btn1.setEnabled(false);
        binding.btn2.setEnabled(false);
        binding.btn3.setEnabled(false);
        binding.btn4.setEnabled(false);
        binding.btn5.setEnabled(false);
        binding.btn6.setEnabled(false);
        binding.btn7.setEnabled(false);
        binding.btn8.setEnabled(false);
        binding.btn9.setEnabled(false);
    }
}