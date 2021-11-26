package com.arash.altafi.mvp.home;

import com.arash.altafi.mvp.base.BasePresenter;
import com.arash.altafi.mvp.base.BaseView;
import com.arash.altafi.mvp.data.Banners;
import com.arash.altafi.mvp.data.News;

import java.util.List;

public interface HomeContract {

    interface View extends BaseView
    {
        void showNews(List<News> showNewsList);
        void showBanners(List<Banners> showBannersList);
    }

    interface Presenter extends BasePresenter<View>
    {
        void getNews();
        void getBanners();
    }

}