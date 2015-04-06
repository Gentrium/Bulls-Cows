package com.example.home.bullscows;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainScreenActivity extends ActionBarActivity implements View.OnClickListener {

    Generator g = new Generator();
    EditText numbers;

    Button btnZero;
    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        numbers = (EditText) findViewById(R.id.numbers);
        numbers.setFocusable(false);
        numbers.setCursorVisible(false);
        numbers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        initializeButtons();
        Button btnCheck = (Button) findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(MainScreenActivity.this);
        Intent intent = new Intent(this, BackgroundMusic.class);
        startService(intent);
    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnOne:
                numbers.append("1");
                btnOne.setEnabled(false);
                break;
            case R.id.btnTwo:
                numbers.append("2");
                btnTwo.setEnabled(false);
                break;
            case R.id.btnThree:
                numbers.append("3");
                btnThree.setEnabled(false);
                break;
            case R.id.btnFour:
                numbers.append("4");
                btnFour.setEnabled(false);
                break;
            case R.id.btnFive:
                numbers.append("5");
                btnFive.setEnabled(false);
                break;
            case R.id.btnSix:
                numbers.append("6");
                btnSix.setEnabled(false);
                break;
            case R.id.btnSeven:
                numbers.append("7");
                btnSeven.setEnabled(false);
                break;
            case R.id.btnEight:
                numbers.append("8");
                btnEight.setEnabled(false);
                break;
            case R.id.btnNine:
                numbers.append("9");
                btnNine.setEnabled(false);
                break;
            case R.id.btnZero:
                if (TextUtils.isEmpty(numbers.getText())) {
                    Toast.makeText(MainScreenActivity.this, R.string.zeroFirstError, Toast.LENGTH_SHORT).show();
                } else {
                    numbers.append("0");
                    btnZero.setEnabled(false);
                }
                break;
            case R.id.btnDelete:
                if (numbers.getText().length() > 1) {
                    numbers.setText(numbers.getText().subSequence(0, numbers.getText().length() - 1));
                } else {
                    numbers.setText("");
                    btnZero.setEnabled(false);
                }
                break;
            case R.id.btn_check:
                int bulls = 0;
                int cows = 0;
                int[] secret = g.generate();

                if (numbers.getText().length() < 4) {
                    Toast.makeText(this, R.string.tooSmallNumber, Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (Character.getNumericValue(numbers.getText().toString().charAt(i)) == secret[j]) {
                                if (i == j)
                                    bulls++;
                                cows++;
                            }
                        }
                    }
                    Toast.makeText(this, "Bulls " + bulls + " and Cows " + cows, Toast.LENGTH_SHORT).show();
                    showDialog(cows,bulls);
                    enableButtons();
                    numbers.setText("");
                }
                break;
        }
    }

    void showDialog(int cows, int bulls) {
        final DialogFragment newFragment = AnimationFragment.newInstance(cows, bulls);
        newFragment.setStyle(DialogFragment.STYLE_NO_TITLE,0);
        newFragment.setCancelable(false);
        newFragment.show(getFragmentManager(), "dialog");
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                newFragment.dismiss();
                timer.cancel();
            }
        }, 5000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeButtons() {
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnZero = (Button) findViewById(R.id.btnZero);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void enableButtons(){
        btnOne.setEnabled(true);
        btnTwo.setEnabled(true);
        btnThree.setEnabled(true);
        btnFour.setEnabled(true);
        btnFive.setEnabled(true);
        btnSix.setEnabled(true);
        btnSeven.setEnabled(true);
        btnEight.setEnabled(true);
        btnNine.setEnabled(true);
        btnZero.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, BackgroundMusic.class);
        stopService(intent);
    }
}
