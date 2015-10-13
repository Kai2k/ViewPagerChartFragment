package com.example.android.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    ChartPagerAdapter mChartPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChartPagerAdapter =
                new ChartPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mChartPagerAdapter);

        initialiseButtons();
    }

    private void initialiseButtons() {
        Button btn3Months = (Button) findViewById(R.id.btn_3_month);
        btn3Months.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyFragment) mChartPagerAdapter.mCurrentFragment).rebuildChart(MyFragment.months.THREE);
            }
        });

        Button btn6Months = (Button) findViewById(R.id.btn_6_month);
        btn6Months.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyFragment) mChartPagerAdapter.mCurrentFragment).rebuildChart(MyFragment.months.SIX);
            }
        });

        Button btn12Months = (Button) findViewById(R.id.btn_12_month);
        btn12Months.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyFragment) mChartPagerAdapter.mCurrentFragment).rebuildChart(MyFragment.months.TWELVE);
            }
        });
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
}
