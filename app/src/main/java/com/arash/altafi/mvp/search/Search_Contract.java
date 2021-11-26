package com.arash.altafi.mvp.search;

import com.arash.altafi.mvp.base.BasePresenter;
import com.arash.altafi.mvp.base.BaseView;
import com.arash.altafi.mvp.data.News;

import java.util.List;

public interface Search_Contract {

    interface view extends BaseView
    {
        void show_ResultNews(List<News> newsList);
        void show_Empty();
    }

    interface presenter extends BasePresenter<view>
    {
        void search(CharSequence keyword);
    }

}
