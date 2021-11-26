package com.arash.altafi.mvp.home;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.mvp.NewsDetails.NewsDetails_Activity;
import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.data.Banners;
import com.arash.altafi.mvp.data.News;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_News extends RecyclerView.Adapter<Adapter_News.ViewHolder>{

    List<News> news = new ArrayList<>();

    public Adapter_News()
    {

    }

    public Adapter_News(List<News> news) {
        this.news = news;
    }

    public void setNews(List<News> news)
    {
        this.news = news;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_last_news,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_News.ViewHolder holder, int position) {
        holder.bind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private CircleImageView circleImageView;
        private TextView txtDate,txtTitle,txtWriter,txtVideos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_last_news);
            circleImageView = itemView.findViewById(R.id.img_writer_last_news);
            txtDate = itemView.findViewById(R.id.txt_date_last_news);
            txtTitle = itemView.findViewById(R.id.txt_title_last_news);
            txtWriter = itemView.findViewById(R.id.txt_writer_last_news);
            txtVideos = itemView.findViewById(R.id.txt_videos_show);
        }

        public void bind(News newsList)
        {
            Glide.with(itemView.getContext()).load(newsList.getImage()).into(imageView);
            Glide.with(itemView.getContext()).load(newsList.getImage_writer()).into(circleImageView);
            txtWriter.setText(newsList.getWriter());
            txtTitle.setText(newsList.getTitle());
            txtDate.setText(newsList.getDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext() , NewsDetails_Activity.class);
                    intent.putExtra("details" , newsList);
                    itemView.getContext().startActivity(intent);
                }
            });

            if (newsList.isVideoShow1())
            {
                txtVideos.setText("#ویدیو");
            }
            else
            {
                txtVideos.setText("#مقاله");
            }
        }

    }

}