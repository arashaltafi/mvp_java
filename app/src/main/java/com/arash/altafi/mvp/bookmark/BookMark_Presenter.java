package com.arash.altafi.mvp.bookmark;

import com.arash.altafi.mvp.data.DataSource;
import com.arash.altafi.mvp.data.News;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookMark_Presenter implements BookMark_Contract.presenter {

    BookMark_Contract.view view;
    private DataSource dataSource;
    CompositeDisposable disposable = new CompositeDisposable();

    public BookMark_Presenter(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void getBookMark() {
        view.showProgressBar(true);
        dataSource.getBookMark_News().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<News>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NotNull List<News> news) {
                if (news.isEmpty())
                {
                    view.showEmptyView();
                }
                else
                {
                    view.showBookMark(news);
                    view.hideEmptyView();
                }

                view.showProgressBar(false);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                view.showError("خطا در اتصال");
                view.showProgressBar(false);
            }
        });
    }

    @Override
    public void atachView(BookMark_Contract.view view) {
        this.view = view;
        getBookMark();
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