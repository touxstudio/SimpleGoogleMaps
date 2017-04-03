package org.coursera.mygooglemaps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.app.PendingIntent.getActivity;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView one = (ImageView) findViewById(R.id.goMadrid);
        one.setOnClickListener(onClickListener); // calling onClick() method
        ImageView two = (ImageView) findViewById(R.id.goBCN);
        two.setOnClickListener(onClickListener);
        ImageView three = (ImageView) findViewById(R.id.goCoruna);
        three.setOnClickListener(onClickListener);
        ImageView four = (ImageView) findViewById(R.id.goFuerte);
        four.setOnClickListener(onClickListener);


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
    public void onClick(View v) {

            switch (v.getId()) {

                case R.id.goMadrid:
                    goMapsHome("40.4167754", "-3.7037901999999576");
                    Log.v("TOUX", "Madrid");
                    break;
                case R.id.goBCN:
                    goMapsHome("41.3850639", "2.1734034999999494");
                    Log.v("TOUX", "BCN");
                    break;
                case R.id.goCoruna:
                    goMapsHome("43.3618688", "-8.4477034");
                    Log.v("TOUX", "Coruña");
                    break;
                case R.id.goFuerte:
                    goMapsHome("28.462479","-13.998657");


                    //goMapsHome(28.3992981, -14.7267504);
                    Log.v("TOUX", "Fuerte");
                    break;
            }
        }

    };


    public String goMapsHome(String LAT, String LNG){

        SharedPreferences prefs = this.getSharedPreferences("MAPS", this.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("LAT", LAT);
        editor.putString("LNG", LNG);
        editor.apply();

        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(i);

        return null;
    }
}
