package com.example.ownroadrider;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Image_Slider_Haman_Mujinjeong extends FragmentStateAdapter {

    public int mCount;

    public Image_Slider_Haman_Mujinjeong(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new Haman_Mujinjeong_Img1();
        else if(index==1) return new Haman_Mujinjeong_Img2();
        else if(index==2) return new Haman_Mujinjeong_Img3();
        else return new Haman_Mujinjeong_Img4();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }

}