package com.arash.altafi.mvp.data;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

     @GET("senddata.php")
     Single<List<News>> getAllNews();

     @GET("lastnews.php")
    Single<List<News>> getNews();

     @GET("banner.php")
     Single<List<Banners>> getBanners();

     @GET("group.php")
    Single<List<Category>> getCategory();

     @GET("video_news.php")
    Single<List<Videos>> getVideos();

     @GET("search.php")
     Single<List<News>> getSearch(@Query("search") String search);

     @GET("cat.php")
    Single<List<News>> getListCategory(@Query("grouping") String grouping);
}
