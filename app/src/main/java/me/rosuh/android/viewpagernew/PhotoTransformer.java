package me.rosuh.android.viewpagernew;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;


public class PhotoTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();

        if (position < -1){
            page.setAlpha(1);
        }else if (position <= 1){
            page.setPivotX(position < 0f ? page.getWidth() : 0f);
            page.setPivotY(page.getHeight() * 0.5f);
            page.setRotationY(90f * position);

        }else {
            page.setAlpha(1);
        }
    }
}
