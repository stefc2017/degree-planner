package comp3350.degree_planner.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import comp3350.degree_planner.R;

/**
 * Created by Penny He on 6/3/2017.
 */

public class Splash extends Activity {
    private static int DURATION = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(DURATION);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openMainActivity = new Intent(".presentation.MainActivity");
                    startActivity(openMainActivity);
                }
            }
        };
        timer.start();
    }
}
