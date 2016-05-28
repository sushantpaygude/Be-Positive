package com.example.rnztx.donors.feeds.intro;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.feeds.intro.auth.SigninActivity;

public class SplashScreenActivity extends AppCompatActivity {


    private static final int STOPSPLASH = 0;
    //time duration in millisecond for which your splash screen should visible to
    //user. here i have taken half second
    private static final long SPLASHTIME = 5;
    TextView bannertxt;
    //handler for splash screen
    private Handler splashHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STOPSPLASH:
                    //Generating and Starting new intent on splash time out
                    Intent intent = new Intent(getApplicationContext(),
                            SigninActivity.class);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        bannertxt=(TextView)findViewById(R.id.banner);
        bannertxt.setText("Be Positive");
        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/SimplyRoundedBold.ttf");
        bannertxt.setTypeface(typeface);
        //Generating message and sending it to splash handle
        Message msg = new Message();
        msg.what = STOPSPLASH;
        splashHandler.sendMessageDelayed(msg, SPLASHTIME);


    }

}
