package com.arash.altafi.mvp.allNews;

import com.arash.altafi.mvp.data.DataSource;
import com.arash.altafi.mvp.data.News;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllNews_Presenter implements AllNews_Contract.presenter{

    private AllNews_Contract.view view;
    private DataSource dataSource;
    private Disposable disposable;

    public AllNews_Presenter(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public void getAllNews() {
        dataSource.getAllNews().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<News>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(@NotNull List<News> news) {
                view.showAllNews(news);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                view.showError("خطا در اتصال");
            }
        });
    }

    @Override
    public void atachView(AllNews_Contract.view view) {
        this.view = view;
        getAllNews();
    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable.isDisposed() && disposable != null)
        {
            disposable.dispose();
        }
    }

}