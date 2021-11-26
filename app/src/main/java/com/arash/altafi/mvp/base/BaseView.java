package com.arash.altafi.mvp.base;

import android.content.Context;

public interface BaseView {

    void showProgressBar(boolean isProgress);

    void showError(String error);

    Context context();

}