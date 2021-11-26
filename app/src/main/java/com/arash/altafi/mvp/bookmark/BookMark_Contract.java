package com.arash.altafi.mvp.bookmark;

import com.arash.altafi.mvp.base.BasePresenter;
import com.arash.altafi.mvp.base.BaseView;
import com.arash.altafi.mvp.data.News;

import java.util.List;

public interface BookMark_Contract {

    interface view extends BaseView
    {
        void showBookMark(List<News> showBookMark);
        void showEmptyView();
        void hideEmptyView();
    }

    interface presenter extends BasePresenter<view>
    {
        void getBookMark();
    }

}