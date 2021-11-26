package com.arash.altafi.mvp.listCategory;

import com.arash.altafi.mvp.data.DataSource;
import com.arash.altafi.mvp.data.News;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class List_Category_Presenter implements List_Category_Contrcat.presenter {

    private List_Category_Contrcat.view view;
    private Disposable disposable;
    private DataSource dataSource;

    public List_Category_Presenter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void getListCategory(String grouping) {
        dataSource.getListCategory(grouping).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<News>>() {
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
                    view.show_ListCategory(news);
                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                view.showError("خطا در اتصال");
            }
        });
    }

    @Override
    public void atachView(List_Category_Contrcat.view view) {
        this.view = view;
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