package com.kepss.expenseentry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kepss.expenseentry.R;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<View> alView ;

    public ViewPagerAdapter(Context context,List<View> alView) {
        this.context = context;
        this.alView = alView;
    }

    @Override
    public int getCount() {
        return alView.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llText);
        linearLayout.removeAllViews();
        if (linearLayout.getParent()!=null){
            ((ViewGroup)linearLayout.getParent()).removeView(linearLayout); // <- fix
        }
        linearLayout.addView(alView.get(position));

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}