package com.fy.flames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Fname;
    EditText Sname;
    Button Flames;
    TextView flamesText;
    ImageView flamesImg;
    ProgressBar circularpb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fname = findViewById(R.id.etname1);
        Sname = findViewById(R.id.etname2);
        Flames = findViewById(R.id.flames);
        flamesText = findViewById(R.id.flamesres);
        flamesImg = findViewById(R.id.flamesimg);
        circularpb = findViewById(R.id.flamesbar);
        Flames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flamesFunc();
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }
    public void flamesFunc(){
        String fname = Fname.getText().toString().toLowerCase().replaceAll(" ", "");
        String sname = Sname.getText().toString().toLowerCase().replaceAll(" ", "");
        if((!fname.isEmpty() && !sname.isEmpty()) && !fname.equals(sname)){
            circularpb.setVisibility(View.VISIBLE);
            char[] first = fname.toCharArray();
            char[] second = sname.toCharArray();
            int f = first.length;
            int s = second.length;
            int t = s;
            for(char i: first) {
               for (int j = 0; j < s; j++) {
                   try{
                   if (i == second[j]) {
                       second = remove_index(second, j);
                       s -= 1;
                       break;
                   }
                   }catch (Exception e){
                       e.printStackTrace();
                   }
               }

           }
            int r = f - (t-s);
            if (r < 0){
                r = -(r);
            }
            int n = flamesResult(r+s);
            switch(n){
                case 1:
                    circularpb.setVisibility(View.INVISIBLE);
                    flamesText.setText("Friends");
                    flamesImg.setImageDrawable(getResources().getDrawable(R.drawable.friends));
                    break;
                case 2:
                    circularpb.setVisibility(View.INVISIBLE);
                    flamesText.setText("Lovers");
                    flamesImg.setImageDrawable(getResources().getDrawable(R.drawable.lovestickers));
                    break;
                case 3:
                    circularpb.setVisibility(View.INVISIBLE);
                    flamesText.setText("Admirers");
                    flamesImg.setImageDrawable(getResources().getDrawable(R.drawable.admirer));
                    break;
                case 4:
                    circularpb.setVisibility(View.INVISIBLE);
                    flamesText.setText("Marriage");
                    flamesImg.setImageDrawable(getResources().getDrawable(R.drawable.marriage));
                    break;
                case 5:
                    circularpb.setVisibility(View.INVISIBLE);
                    flamesText.setText("Enemies");
                    flamesImg.setImageDrawable(getResources().getDrawable(R.drawable.enemies));
                    break;
                case 6:
                    circularpb.setVisibility(View.INVISIBLE);
                    flamesText.setText("Secret Lovers");
                    flamesImg.setImageDrawable(getResources().getDrawable(R.drawable.secretlover));
                    break;
                default:
                    circularpb.setVisibility(View.INVISIBLE);
                    flamesText.setText("Secret Lovers");
                    flamesImg.setImageDrawable(getResources().getDrawable(R.drawable.secretlover));
                    break;
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Fill the both blanks with different names", Toast.LENGTH_LONG).show();
        }
    }

    public int flamesResult(int r){
        int i = 0;
        while(r!=0){
            ++i;
            --r;
            if(i==6){
                i=0;
            }
        }
        return i;
    }
    public char[] remove_index(char[] arr, int loc){
        int last_inserted;
        last_inserted = 0;
        if(arr.length == 1){
            return null;
        }
        char[] newArray = new char[arr.length-1];
        for(int l=0; l<arr.length; l++){
            if(l == loc){
                l++;
            }
            newArray[last_inserted++] = arr[l];
        }
        return newArray;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.rate_us:
                final String appPackageName = getPackageName();
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                }catch (android.content.ActivityNotFoundException anfe){
                    startActivity(new Intent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName))));
                }
                return true;
            case R.id.privacy_policy:
                Intent intent = new Intent(this, PrivacyPolicy.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}