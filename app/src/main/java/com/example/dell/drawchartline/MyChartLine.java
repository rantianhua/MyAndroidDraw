package com.example.dell.drawchartline;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class MyChartLine extends Activity {

    private LinearLayout linear;
    private TextView viewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chart_line);
        List<Integer> list = new ArrayList<Integer>();
        list.add(65);
        list.add(78);
        list.add(65);
        list.add(43);
        list.add(62);
        list.add(34);
        list.add(45);
        list.add(24);
        list.add(23);
        list.add(56);
        list.add(86);

        List<String> Data = new ArrayList<String>() ;
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        Data.add("09-01-6-05");
        viewText = (TextView) findViewById(R.id.view_text);
        viewText.setText("当前温度：" + 88);
        linear = (LinearLayout) findViewById(R.id.linear);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int width = metrics.widthPixels;
//        int height = metrics.heightPixels;


        BrokeLine broken = new BrokeLine(this,Data,list);
        broken.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        linear.addView(broken);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_chart_line, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
