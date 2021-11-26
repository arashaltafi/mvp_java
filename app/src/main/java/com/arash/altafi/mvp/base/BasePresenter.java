package com.arash.altafi.mvp.base;

public interface BasePresenter <T extends BaseView> {

    void atachView(T view);

    void detachView();

}
