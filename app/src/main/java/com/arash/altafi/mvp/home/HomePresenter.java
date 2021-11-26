package com.arash.altafi.mvp.home;

import androidx.annotation.NonNull;

import com.arash.altafi.mvp.data.Banners;
import com.arash.altafi.mvp.data.News;
import com.arash.altafi.mvp.data.DataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private DataSource newsDataSource;
    private CompositeDisposable disposable = new CompositeDisposable();
    private boolean isRenderd ;

    public HomePresenter(DataSource newsDataSource)
    {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getNews() {
        view.showProgressBar(true);
        newsDataSource.getNews().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<News>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<News> news) {
                view.showNews(news);
                view.showProgressBar(false);
                isRenderd = true;
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError("خطا در اتصال");
                view.showProgressBar(false);
            }
        });
    }

    @Override
    public void getBanners() {
        view.showProgressBar(true);
        newsDataSource.getBanners().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<Banners>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull List<Banners> banners) {
                view.showBanners(banners);
                view.showProgressBar(false);
                isRenderd = true;
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError("خطا در اتصال");
                view.showProgressBar(false);
            }
        });
    }

    @Override
    public void atachView(HomeContract.View view) {
        this.view = view;
        if (!isRenderd)
        {
            getNews();
            getBanners();
        }

    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable != null && disposable.size() > 0)
        {
            disposable.dispose();
            disposable.clear();
        }
    }

}