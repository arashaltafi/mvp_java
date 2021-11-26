package com.arash.altafi.mvp.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.Utility.CenterZoomLayoutManager;
import com.arash.altafi.mvp.allNews.AllNews_Activity;
import com.arash.altafi.mvp.base.BaseFragment;
import com.arash.altafi.mvp.data.Banners;
import com.arash.altafi.mvp.data.News;
import com.arash.altafi.mvp.data.Repository;
import com.arash.altafi.mvp.search.Search_Activity;

import java.util.List;

public class Home_Fragment extends BaseFragment implements HomeContract.View{

    private HomeContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new HomePresenter(new Repository(context()));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.atachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void showNews(List<News> showNewsList) {
        RecyclerView recyclerViewLastNews = rootView.findViewById(R.id.recy_Last_News);
        Adapter_News adapter_news = new Adapter_News(showNewsList);
        recyclerViewLastNews.setLayoutManager(new LinearLayoutManager(context(),RecyclerView.VERTICAL,false));
        recyclerViewLastNews.setAdapter(adapter_news);
        recyclerViewLastNews.setNestedScrollingEnabled(false);
    }

    @Override
    public void showBanners(List<Banners> showBannersList) {
        RecyclerView recyclerViewBanners = rootView.findViewById(R.id.recy_Banner);

        // Select Item
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewBanners);

        // Effect
        CenterZoomLayoutManager centerZoomLayoutManager = new CenterZoomLayoutManager(context(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewBanners.setLayoutManager(centerZoomLayoutManager);

        Adapter_Banners adapter_banners = new Adapter_Banners(showBannersList);
//        recyclerViewBanners.setLayoutManager(new LinearLayoutManager(context() , RecyclerView.HORIZONTAL ,false));
        recyclerViewBanners.setAdapter(adapter_banners);
        recyclerViewBanners.setNestedScrollingEnabled(false);
    }

    @Override
    public void showProgressBar(boolean isProgress) {
        getBaseActivity().setProgressBar(isProgress);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(context(), error , Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void setUpView() {
        ImageView imageViewAllNews = rootView.findViewById(R.id.image_All_News);
        ImageView imageSearch = rootView.findViewById(R.id.img_search);

        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context() , Search_Activity.class));
            }
        });

        imageViewAllNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context() , AllNews_Activity.class));
            }
        });

    }
}