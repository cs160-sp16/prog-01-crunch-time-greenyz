package com.example.zachgreen.crunchtime;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    int checked_id = -1;
    final double pushup_factor = 100 / (double) 350;
    final double situp_factor = 100 / (double) 200;
    final double jumping_factor = 100 / (double) 10;
    final double jogging_factor = 100 / (double) 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onExerciseClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {

            case R.id.pushup:
            case R.id.situp:
                if (checked) {
                    checked_id = view.getId();
                    RelativeLayout minutesReps = (RelativeLayout)findViewById(R.id.minutesRepsLayout);
                    minutesReps.setVisibility(View.INVISIBLE);
                    TextView t = (TextView) findViewById(R.id.minutes_reps);
                    t.setText(R.string.num_reps);
//                    t = (TextView) findViewById(R.id.minutes_rep_word);
//                    t.setText(R.string.reps);
                    minutesReps.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.jumping_jack:
            case R.id.jogging:
                if (checked) {
                    checked_id = view.getId();
                    RelativeLayout minutesReps = (RelativeLayout) findViewById(R.id.minutesRepsLayout);
                    minutesReps.setVisibility(View.INVISIBLE);
                    TextView t = (TextView) findViewById(R.id.minutes_reps);
                    t.setText(R.string.num_minutes);
//                    t = (TextView) findViewById(R.id.minutes_rep_word);
//                    t.setText(R.string.minutes);
                    minutesReps.setVisibility(View.VISIBLE);
                }
                break;
        }
        findViewById(R.id.results_cont).setVisibility(View.GONE);
    }

    public void calculateClicked(View view) {
        if (checked_id == -1) {
            return;
        }
        EditText input_text = (EditText)findViewById(R.id.input_cals);
        int user_input = 0;
        try {
            user_input = Integer.valueOf(input_text.getText().toString());
        } catch (Exception e) {
            user_input = 0;
        }
        RelativeLayout pushup_cont = (RelativeLayout)findViewById(R.id.pushup_cont);
        RelativeLayout situp_cont = (RelativeLayout)findViewById(R.id.situp_cont);
        RelativeLayout jumping_cont = (RelativeLayout)findViewById(R.id.jumping_cont);
        RelativeLayout jogging_cont = (RelativeLayout)findViewById(R.id.jogging_cont);
        pushup_cont.setVisibility(View.VISIBLE);
        situp_cont.setVisibility(View.VISIBLE);
        jumping_cont.setVisibility(View.VISIBLE);
        jogging_cont.setVisibility(View.VISIBLE);
        double cals = 0;
        switch(checked_id) {
            case R.id.pushup:
                cals = user_input * pushup_factor;
                pushup_cont.setVisibility(View.GONE);
                break;
            case R.id.situp:
                cals = user_input * situp_factor;
                situp_cont.setVisibility(View.GONE);
                break;
            case R.id.jumping_jack:
                cals = user_input * jumping_factor;
                jumping_cont.setVisibility(View.GONE);
                break;
            case R.id.jogging:
                cals = user_input * jogging_factor;
                jogging_cont.setVisibility(View.GONE);
                break;
        }
        TextView t = (TextView) findViewById(R.id.num_calories);
        t.setText(String.format("%.1f", cals));
        double pushup_reps = cals / pushup_factor;
        double situp_reps = cals / situp_factor;
        double jumping_min = cals / jumping_factor;
        double jogging_min = cals / jogging_factor;
        t = (TextView) findViewById(R.id.num_pushups);
        t.setText(String.format("%.0f", pushup_reps));
        t = (TextView) findViewById(R.id.num_situps);
        t.setText(String.format("%.0f", situp_reps));
        t = (TextView) findViewById(R.id.num_jumping);
        t.setText(String.format("%.1f", jumping_min));
        t = (TextView) findViewById(R.id.num_jogging);
        t.setText(String.format("%.1f", jogging_min));
        findViewById(R.id.results_cont).setVisibility(View.VISIBLE);
    }
}
