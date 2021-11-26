package com.arash.altafi.mvp.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.base.BaseFragment;
import com.arash.altafi.mvp.data.Category;
import com.arash.altafi.mvp.data.Repository;
import com.arash.altafi.mvp.search.Search_Activity;

import java.util.ArrayList;
import java.util.List;

public class Category_Fragment extends BaseFragment implements Category_contract.view {

    RecyclerView recy_category;
    private Category_contract.presenter presenter;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new Category_Presenter(new Repository(context()));
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
        return R.layout.fragment_category;
    }

    @Override
    public void setUpView() {
        recy_category = rootView.findViewById(R.id.recy_Category);
        ImageView imageSearch = rootView.findViewById(R.id.img_search);
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context() , Search_Activity.class));
            }
        });
    }

    @Override
    public void showCategory(List<Category> showCategoryList) {
        recy_category.setLayoutManager(new GridLayoutManager(context() , 2));
        Adapter_Category adapter_category = new Adapter_Category(showCategoryList);
        recy_category.setAdapter(adapter_category);
        recy_category.setNestedScrollingEnabled(false);
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