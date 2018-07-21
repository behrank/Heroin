package com.yunusseker.heroin.ui.main.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yunusseker.heroin.R;
import com.yunusseker.heroin.data.model.BannerModel;
import com.yunusseker.heroin.databinding.ItemBannerBinding;

import java.util.List;

public class BannerPagerAdapter extends PagerAdapter {

    private Context context;
    private List<BannerModel> bannerList;
    private ItemBannerBinding dataBinding;

    public BannerPagerAdapter(Context context, List<BannerModel> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        dataBinding= DataBindingUtil.inflate(inflater,R.layout.item_banner,container,false);
        //dataBinding.setBannerModel(bannerList.get(position));
        container.addView(dataBinding.getRoot());
        return dataBinding.getRoot();
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
