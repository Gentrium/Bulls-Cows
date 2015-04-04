package com.example.home.bullscows;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainScreenActivity extends ActionBarActivity implements View.OnClickListener {

    Generator g;
    EditText numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        numbers = (EditText) findViewById(R.id.numbers);

        Button btnCheck = (Button) findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(this);
    }

    public void onClick(View v){

        int bulls = 0;
        int cows = 0;
        int[] secret = g.generate();
        if(numbers.getText().length() < 4){
            Toast.makeText(this,R.string.tooSmallNumber,Toast.LENGTH_LONG);
        }else{
            for(int i = 0; i < 4; i++) {
                for(int j = 0;j < 4; j++){
                    if((int) numbers.getText().toString().charAt(i) == secret[j]){
                        if(i == j)
                            bulls++;
                        cows++;
                    }
                }
            }
            Toast.makeText(this, "Bulls " + bulls + " and Cows " + cows,Toast.LENGTH_LONG);
        }

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
}
