package com.arash.altafi.mvp.listCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.Utility.G;
import com.arash.altafi.mvp.base.BaseActivity;
import com.arash.altafi.mvp.data.News;
import com.arash.altafi.mvp.data.Repository;
import com.arash.altafi.mvp.home.Adapter_News;
import com.arash.altafi.mvp.search.Search_Activity;

import java.util.List;

public class List_Category_Activity extends BaseActivity implements List_Category_Contrcat.view {

    private RecyclerView recy_list_catyegory;
    private ImageView img_search;
    private List_Category_Contrcat.presenter presenter;
    private Adapter_List adapter_list;
    private TextView txt_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        presenter = new List_Category_Presenter(new Repository(this));
        presenter.atachView(this);

        FindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void FindView()
    {
        img_search = findViewById(R.id.img_search);
        recy_list_catyegory = findViewById(R.id.recy_List_Category);
        txt_empty = findViewById(R.id.txt_empty);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context() , Search_Activity.class));
            }
        });

        adapter_list = new Adapter_List();
        recy_list_catyegory.setLayoutManager(new LinearLayoutManager(context() , RecyclerView.VERTICAL , false));
        recy_list_catyegory.setAdapter(adapter_list);
        txt_empty.setVisibility(View.GONE);
        recy_list_catyegory.setVisibility(View.VISIBLE);
        presenter.getListCategory(getIntent().getStringExtra("grouping"));

    }

    @Override
    public void setProgressBar(boolean isProgress) {

    }

    @Override
    public void show_ListCategory(List<News> showListCategory) {
        adapter_list.setNews(showListCategory);
        if (showListCategory.isEmpty())
        {
            txt_empty.setVisibility(View.VISIBLE);
            recy_list_catyegory.setVisibility(View.GONE);
        }
        else
        {
            txt_empty.setVisibility(View.GONE);
            recy_list_catyegory.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show_Empty() {
        recy_list_catyegory.setVisibility(View.GONE);
        txt_empty.setVisibility(View.VISIBLE);
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