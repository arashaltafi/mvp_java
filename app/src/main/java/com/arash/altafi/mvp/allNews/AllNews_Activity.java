package com.arash.altafi.mvp.allNews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.base.BaseActivity;
import com.arash.altafi.mvp.data.News;
import com.arash.altafi.mvp.data.Repository;
import com.arash.altafi.mvp.home.Adapter_News;
import com.arash.altafi.mvp.search.Search_Activity;

import java.util.List;

public class AllNews_Activity extends BaseActivity implements AllNews_Contract.view {

    private RecyclerView recy_All_News;
    private ImageView img_search;
    private AllNews_Contract.presenter presenter;
    private Adapter_News adapter_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);

        presenter = new AllNews_Presenter(new Repository(this));
        presenter.atachView(this);
        FindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void FindView() {
        img_search = findViewById(R.id.img_search);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context() , Search_Activity.class));
            }
        });

    }

    @Override
    public void setProgressBar(boolean isProgress) {

    }

    @Override
    public void showAllNews(List<News> showAllNews) {
        recy_All_News = findViewById(R.id.recy_All_News);
        adapter_news = new Adapter_News(showAllNews);
        recy_All_News.setLayoutManager(new LinearLayoutManager(context() , RecyclerView.VERTICAL , false));
        recy_All_News.setAdapter(adapter_news);
    }

    @Override
    public void showProgressBar(boolean isProgress) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(context(), error , Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

}