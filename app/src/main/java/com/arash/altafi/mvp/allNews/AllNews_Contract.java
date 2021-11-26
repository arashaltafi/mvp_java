package com.arash.altafi.mvp.allNews;

import com.arash.altafi.mvp.base.BasePresenter;
import com.arash.altafi.mvp.base.BaseView;
import com.arash.altafi.mvp.data.News;

import java.util.List;

public interface AllNews_Contract {

    interface view extends BaseView
    {
        void showAllNews(List<News> showAllNews);
    }

    interface presenter extends BasePresenter<view>
    {
        void getAllNews();
    }

}