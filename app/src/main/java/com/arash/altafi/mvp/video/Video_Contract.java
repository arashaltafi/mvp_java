package com.arash.altafi.mvp.video;

import com.arash.altafi.mvp.base.BasePresenter;
import com.arash.altafi.mvp.base.BaseView;
import com.arash.altafi.mvp.data.Videos;

import java.util.List;

public interface Video_Contract {

    interface view extends BaseView
    {
        void showVideos(List<Videos> showVideos);
    }

    interface presenter extends BasePresenter<view>
    {
        void getVideos();
    }

}