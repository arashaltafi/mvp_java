package com.arash.altafi.mvp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.DELETE;

@Dao
public abstract class LocalDataSource implements DataSource {

    @Override
    public Single<List<News>> getAllNews() {
        return null;
    }

    @Override
    public Single<List<News>> getNews() {
        return null;
    }

    @Override
    public Single<List<Banners>> getBanners() {
        return null;
    }

    @Override
    public Single<List<Category>> getCategory() {
        return null;
    }

    @Override
    public Single<List<Videos>> getVideos() {
        return null;
    }

    //    @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Override
    public abstract void bookmark(News news);

    @Query("SELECT * FROM tbl_news WHERE is_bookmarked LIKE 1")
    @Override
    public abstract Single<List<News>> getBookMark_News();

    @Delete
    @Override
    public abstract void deleteBookMark(News news);

    @Override
    public Single<List<News>> getSearch(CharSequence keyword) {
        return null;
    }

    @Override
    public Single<List<News>> getListCategory(String grouping) {
        return null;
    }

    @Query("SELECT * FROM tbl_news")
    @Override
    public abstract List<News> getAllBookMark_News();
}