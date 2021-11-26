package com.arash.altafi.mvp.listCategory;

import com.arash.altafi.mvp.base.BasePresenter;
import com.arash.altafi.mvp.base.BaseView;
import com.arash.altafi.mvp.data.News;

import java.util.List;

public interface List_Category_Contrcat {

    interface view extends BaseView
    {
        void show_ListCategory(List<News> showListCategory);
        void show_Empty();
    }

    interface presenter extends BasePresenter<view>
    {
        void getListCategory(String grouping);
    }

}