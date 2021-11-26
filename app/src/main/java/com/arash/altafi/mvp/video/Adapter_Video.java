package com.arash.altafi.mvp.video;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.data.Videos;
import com.arash.altafi.mvp.play_video;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter_Video extends RecyclerView.Adapter<Adapter_Video.ViewHolder> {

    List<Videos> videos;

    public Adapter_Video(List<Videos> videos) {
        this.videos = videos;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_videos , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_video;
        TextView txt_video;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            img_video = itemView.findViewById(R.id.image_videos);
            txt_video = itemView.findViewById(R.id.txt_videos);
        }

        private void bind(Videos videos)
        {
            Glide.with(itemView.getContext()).load(videos.getImage()).into(img_video);
            txt_video.setText(videos.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext() , play_video.class);
                    intent.putExtra("video" , videos.getVideolink());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }

}
