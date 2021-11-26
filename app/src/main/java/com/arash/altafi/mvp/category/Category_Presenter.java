package com.arash.altafi.mvp.category;

import com.arash.altafi.mvp.data.Category;
import com.arash.altafi.mvp.data.DataSource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Category_Presenter implements Category_contract.presenter {

    private DataSource dataSource;
    Category_contract.view view;
    CompositeDisposable disposable = new CompositeDisposable();
    boolean isRender;

    public Category_Presenter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void getCategory() {
        dataSource.getCategory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<Category>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NotNull List<Category> categories) {
                view.showCategory(categories);
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
    public void atachView(Category_contract.view view) {
        this.view = view;
        if (!isRender)
        {
            getCategory();
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