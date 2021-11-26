package com.arash.altafi.mvp.bookmark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.base.BaseFragment;
import com.arash.altafi.mvp.data.News;
import com.arash.altafi.mvp.data.Repository;
import com.arash.altafi.mvp.home.Adapter_News;
import com.arash.altafi.mvp.search.Search_Activity;

import java.util.List;

public class BookMark_Fragment extends BaseFragment implements BookMark_Contract.view{

    BookMark_Contract.presenter presenter;
    private View emptyView;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new BookMark_Presenter(new Repository(context()));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.atachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_bookmark;
    }

    @Override
    public void setUpView() {
        emptyView = rootView.findViewById(R.id.frame_empty);
        ImageView imageSearch = rootView.findViewById(R.id.img_search);
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context() , Search_Activity.class));
            }
        });
    }

    @Override
    public void showBookMark(List<News> showBookMark) {
        RecyclerView recy_BookMark = rootView.findViewById(R.id.recy_BookMark);
        recy_BookMark.setLayoutManager(new LinearLayoutManager(context() , RecyclerView.VERTICAL , false));
        Adapter_News adapter_news = new Adapter_News(showBookMark);
        recy_BookMark.setAdapter(adapter_news);
        recy_BookMark.setNestedScrollingEnabled(false);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar(boolean isProgress) {
//        getBaseActivity().setProgressBar(isProgress);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(context(), error , Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getActivity();
    }
}