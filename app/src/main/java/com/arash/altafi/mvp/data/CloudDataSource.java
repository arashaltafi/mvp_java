package com.arash.altafi.mvp.data;

import java.util.List;
import io.reactivex.Single;

public class CloudDataSource implements DataSource {

    private ApiService apiService;

    public CloudDataSource() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    @Override
    public Single<List<News>> getAllNews() {
        return apiService.getAllNews();
    }

    @Override
    public Single<List<News>> getNews() {
        return apiService.getNews();
    }

    @Override
    public Single<List<Banners>> getBanners() {
        return apiService.getBanners();
    }

    @Override
    public Single<List<Category>> getCategory() {
        return apiService.getCategory();
    }

    @Override
    public Single<List<Videos>> getVideos() {
        return apiService.getVideos();
    }

    @Override
    public void bookmark(News news) {

    }

    @Override
    public void deleteBookMark(News news) {

    }

    @Override
    public Single<List<News>> getBookMark_News() {
        return null;
    }

    @Override
    public List<News> getAllBookMark_News() {
        return null;
    }

    @Override
    public Single<List<News>> getSearch(CharSequence keyword) {
        return apiService.getSearch(keyword.toString());
    }

    @Override
    public Single<List<News>> getListCategory(String grouping) {
        return apiService.getListCategory(grouping);
    }
}
