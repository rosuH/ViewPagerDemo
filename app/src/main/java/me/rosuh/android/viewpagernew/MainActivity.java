package me.rosuh.android.viewpagernew;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_NUMBER = 1000;
    private static final int START_POSITION = MAX_NUMBER/2;

    private List<TextView> mTextViews;
    private LinearLayout mLinearLayout;
    private Handler mHandler = new Handler();


    private ViewPager mViewPager;
    private String[] mStringList = {
            "T1", "T2", "T3", "T4", "T5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager_inside);
        mLinearLayout = findViewById(R.id.ll_inside);
        mHandler.postDelayed(new TimerRunnable(),5000);

        initCircle();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            private int mIndex;

            @Override
            public Fragment getItem(int position) {
                mIndex = Math.abs(position - START_POSITION) % mStringList.length;

                if (position < START_POSITION && mIndex != 0){
                    mIndex = mStringList.length - mIndex;
                }

                return PageFragment.newInstance(mIndex);
            }

            @Override
            public int getCount() {
                return MAX_NUMBER;
            }


        });
        mViewPager.setPageTransformer(true, new PhotoTransformer());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int mIndex;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndex = Math.abs(position - START_POSITION) % mStringList.length;
                if (position < START_POSITION && mIndex != 0){
                    mIndex = mStringList.length - mIndex;
                }
                changePoints(mIndex);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mViewPager.setCurrentItem(START_POSITION);

    }
    private void changePoints(int pos){
        if (mTextViews != null){
            for (int i = 0; i < mTextViews.size(); i++){
                if (pos == i){
                    mTextViews.get(i).setBackgroundResource(R.drawable.dot_selected);
                }else {
                    mTextViews.get(i).setBackgroundResource(R.drawable.dot_normal);
                }
            }
        }
    }

    private void initCircle() {
        mTextViews = new ArrayList<>();
        int d = 20;
        int m = 7;

        for (int i = 0; i < mStringList.length; i++){
            TextView textView = new TextView(this);
            if (i == 0){
                textView.setBackgroundResource(R.drawable.dot_selected);
            }else {
                textView.setBackgroundResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(d, d);

            params.setMargins(m, m, m, m);
            textView.setLayoutParams(params);
            mTextViews.add(textView);
            mLinearLayout.addView(textView);
        }
    }

    class TimerRunnable implements Runnable{

        @Override
        public void run() {
            int curItem = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(curItem+1);
            if (mHandler!=null){
                mHandler.postDelayed(this,5000);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler = null; //此处在Activity退出时及时 回收
    }
}
