package com.arash.altafi.mvp.category;

import com.arash.altafi.mvp.base.BasePresenter;
import com.arash.altafi.mvp.base.BaseView;
import com.arash.altafi.mvp.data.Category;

import java.util.List;

public interface Category_contract {

    interface view extends BaseView
    {
        void showCategory(List<Category> showCategoryList);
    }

    interface presenter extends BasePresenter<view>
    {
        void getCategory();
    }

}
