package com.arash.altafi.mvp.video;

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
import com.arash.altafi.mvp.data.DataSource;
import com.arash.altafi.mvp.data.Repository;
import com.arash.altafi.mvp.data.Videos;
import com.arash.altafi.mvp.search.Search_Activity;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class Video_Fragment extends BaseFragment implements Video_Contract.view {

    Video_Contract.presenter presenter;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new Video_Presenter(new Repository(context()));
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
        return R.layout.fragment_video;
    }

    @Override
    public void setUpView() {
        ImageView imageSearch = rootView.findViewById(R.id.img_search);
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context() , Search_Activity.class));
            }
        });
    }

    @Override
    public void showVideos(List<Videos> showVideos) {
        RecyclerView recy_video = rootView.findViewById(R.id.recy_Video);
        recy_video.setLayoutManager(new LinearLayoutManager(context() , RecyclerView.VERTICAL , false));
        Adapter_Video adapter_video = new Adapter_Video(showVideos);
        recy_video.setAdapter(adapter_video);
        recy_video.setNestedScrollingEnabled(false);
    }

    @Override
    public void showProgressBar(boolean isProgress) {
        getBaseActivity().setProgressBar(isProgress);
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