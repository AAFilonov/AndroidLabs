package com.example.lr_9.utils;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.lr_9.ListData;
import com.example.lr_9.fragments.FragmentContent;
import com.example.lr_9.fragments.FragmentEdit;

public class MyFragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Просмотр", "Редактирование"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentContent.newInstance(position + 1);
            case 1:
                return FragmentEdit.newInstance(position+1);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // генерируем заголовок в зависимости от позиции
        return tabTitles[position];
    }


}