package com.example.skorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toast toast ;
    private ImageButton btnless1;
    private ImageButton btnless2;
    private ImageButton btnmore1;
    private ImageButton btnmore2;

    //Contador de los resultados de los equipos
    private int result1;
    private int result2;

    //Resultados de los equipos
    private TextView resultText1;
    private TextView resultText2;

    //Variables para guardar el resultado de los equipos
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnless1 = (ImageButton) findViewById(R.id.lessTeamOne);
        btnless2 = (ImageButton) findViewById(R.id.lessTeamTwo);
        btnmore1 = (ImageButton) findViewById(R.id.moreTeamOne);
        btnmore2 = (ImageButton) findViewById(R.id.moreTeamTwo);

        resultText1 = findViewById(R.id.resultTeam1);
        resultText2 = findViewById(R.id.resultTeam2);


        if (savedInstanceState != null) {
            result1 = savedInstanceState.getInt(STATE_SCORE_1);
            result2 = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            resultText1.setText(String.valueOf(result1));
            resultText2.setText(String.valueOf(result2));
        }
    }
    //Eventos onclick para bajar el resultado de los dos equipos
    public void decreaseResult(View view){
        int viewID = view.getId();
        switch (viewID){
            case R.id.lessTeamOne:
                if(result1>0) {
                    result1--;
                    resultText1.setText(String.valueOf(result1));
                }else{
                    resultText1.setText("0");
                    toast = Toast.makeText(getApplicationContext(), "Valores negativos no validos", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.lessTeamTwo:

                if(result2>0) {
                    result2--;
                    resultText2.setText(String.valueOf(result2));
                }else{
                    resultText2.setText("0");
                    toast = Toast.makeText(getApplicationContext(), "Valores negativos no validos", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    //Eventos onclick para subir el resultado de los dos equipos
    public void increaseResult(View view){
        int viewID = view.getId();
        switch (viewID){
            case R.id.moreTeamOne:
                result1++;
                resultText1.setText(String.valueOf(result1));
                break;
            case R.id.moreTeamTwo:
                result2++;
                resultText2.setText(String.valueOf(result2));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        }else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Guardar los resultados
        outState.putInt(STATE_SCORE_1, result1);
        outState.putInt(STATE_SCORE_2, result2);
        super.onSaveInstanceState(outState);
    }
}
