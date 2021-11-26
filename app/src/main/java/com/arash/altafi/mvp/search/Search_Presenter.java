package com.arash.altafi.mvp.search;

import com.arash.altafi.mvp.data.DataSource;
import com.arash.altafi.mvp.data.News;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Search_Presenter implements Search_Contract.presenter{

    private Search_Contract.view view;
    private DataSource dataSource;
    private Disposable disposable;

    public Search_Presenter(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void search(CharSequence keyword) {
        dataSource.getSearch(keyword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<News>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(@NotNull List<News> news) {
                if (news.isEmpty())
                {
                    view.show_Empty();
                }
                else
                {
                    view.show_ResultNews(news);
                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                view.showError("خطا در اتصال");
            }
        });
    }

    @Override
    public void atachView(Search_Contract.view view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable != null && disposable.isDisposed())
        {
            disposable.dispose();
        }
    }

}