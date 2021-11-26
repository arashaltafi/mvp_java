package com.arash.altafi.mvp.data;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;

public class Repository implements DataSource {

    CloudDataSource cloudDataSource = new CloudDataSource();
    LocalDataSource localDataSource;

    public Repository(Context context) {
        localDataSource = AppDataBase.getInstance(context).getLocalDataSource();
    }

    @Override
    public Single<List<News>> getAllNews() {
        return cloudDataSource.getAllNews();
    }

    @Override
    public Single<List<News>> getNews() {
        return cloudDataSource.getNews();
    }

    @Override
    public Single<List<Banners>> getBanners() {
        return cloudDataSource.getBanners();
    }

    @Override
    public Single<List<Category>> getCategory() {
        return cloudDataSource.getCategory();
    }

    @Override
    public Single<List<Videos>> getVideos() {
        return cloudDataSource.getVideos();
    }

    @Override
    public void bookmark(News news) {
        localDataSource.bookmark(news);
    }

    @Override
    public void deleteBookMark(News news) {
        localDataSource.deleteBookMark(news);
    }

    @Override
    public Single<List<News>> getBookMark_News() {
        return localDataSource.getBookMark_News();
    }

    @Override
    public List<News> getAllBookMark_News() {
        return localDataSource.getAllBookMark_News();
    }

    @Override
    public Single<List<News>> getSearch(CharSequence keyword) {
        return cloudDataSource.getSearch(keyword);
    }

    @Override
    public Single<List<News>> getListCategory(String grouping) {
        return cloudDataSource.getListCategory(grouping);
    }

}