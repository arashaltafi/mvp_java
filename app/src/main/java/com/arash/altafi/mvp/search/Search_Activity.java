package com.arash.altafi.mvp.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.base.BaseActivity;
import com.arash.altafi.mvp.data.News;
import com.arash.altafi.mvp.data.Repository;
import com.arash.altafi.mvp.home.Adapter_News;

import java.util.List;

public class Search_Activity extends BaseActivity implements Search_Contract.view{

    private RecyclerView recy_search;
    private ImageView img_back,img_clear;
    private TextView txt_empty;
    private EditText edt_search;
    private Adapter_News adapter_news;
    private Search_Contract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        presenter = new Search_Presenter(new Repository(context()));
        presenter.atachView(this);

        FindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void setProgressBar(boolean isProgress) {

    }

    public void FindView() {
        recy_search = findViewById(R.id.recy_search);
        img_back = findViewById(R.id.img_back);
        img_clear = findViewById(R.id.img_clear);
        txt_empty = findViewById(R.id.txt_empty);
        edt_search = findViewById(R.id.edt_search);
        adapter_news = new Adapter_News();

        img_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search.setText(null);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                {
                    presenter.search(charSequence);
                    img_clear.setVisibility(View.VISIBLE);
                }
                else
                {
                    img_clear.setVisibility(View.INVISIBLE);
                    recy_search.setVisibility(View.GONE);
                    txt_empty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void show_ResultNews(List<News> newsList) {
        recy_search.setLayoutManager(new LinearLayoutManager(context() , RecyclerView.VERTICAL , false));
        recy_search.setAdapter(adapter_news);
        adapter_news.setNews(newsList);
        txt_empty.setVisibility(View.GONE);
        recy_search.setVisibility(View.VISIBLE);
    }

    @Override
    public void show_Empty() {
        recy_search.setVisibility(View.GONE);
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