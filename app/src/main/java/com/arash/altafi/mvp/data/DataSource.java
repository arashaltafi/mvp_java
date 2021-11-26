package com.arash.altafi.mvp.data;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {

    Single<List<News>> getAllNews();

    Single<List<News>> getNews();

    Single<List<Banners>> getBanners();

    Single<List<Category>> getCategory();

    Single<List<Videos>> getVideos();

    void bookmark(News news);
    void deleteBookMark(News news);
    Single<List<News>> getBookMark_News();

    List<News> getAllBookMark_News();

    Single<List<News>> getSearch(CharSequence keyword);

    Single<List<News>> getListCategory(String grouping);

}