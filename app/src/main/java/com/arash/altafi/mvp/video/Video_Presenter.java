package com.arash.altafi.mvp.video;

import com.arash.altafi.mvp.data.DataSource;
import com.arash.altafi.mvp.data.Videos;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Video_Presenter implements Video_Contract.presenter{

    Video_Contract.view view;
    DataSource dataSource;
    CompositeDisposable disposable = new CompositeDisposable();
    boolean isRender;

    public Video_Presenter(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public void getVideos() {
        view.showProgressBar(true);
        dataSource.getVideos().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<Videos>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NotNull List<Videos> videos) {
                view.showVideos(videos);
                view.showProgressBar(false);
                isRender = true;
            }

            @Override
            public void onError(@NotNull Throwable e) {
                view.showError("خطا در اتصال");
                view.showProgressBar(false);
            }
        });
    }

    @Override
    public void atachView(Video_Contract.view view) {
        this.view = view;
        if (!isRender)
        {
            getVideos();
        }
    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable.size() > 0 && disposable != null)
        {
            disposable.dispose();
            disposable.clear();
        }
    }

}