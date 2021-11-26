package com.arash.altafi.mvp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.data.Banners;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Banners extends RecyclerView.Adapter<Adapter_Banners.ViewHolder>{

    List<Banners> banners;

    public Adapter_Banners(List<Banners> banners) {
        this.banners = banners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banners,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Banners.ViewHolder holder, int position) {
        holder.bind(banners.get(position));
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private CircleImageView circleImageView;
        private TextView txtDate,txtTitle,txtWriter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_Banners);
            circleImageView = itemView.findViewById(R.id.image_writer_banner);
            txtDate = itemView.findViewById(R.id.txt_date_banner);
            txtTitle = itemView.findViewById(R.id.txt_title_banner);
            txtWriter = itemView.findViewById(R.id.txt_writer_banner);
        }

        public void bind(Banners bannersList)
        {
            Glide.with(itemView.getContext()).load(bannersList.getImage()).into(imageView);
            Glide.with(itemView.getContext()).load(bannersList.getImage_writer()).into(circleImageView);
            txtWriter.setText(bannersList.getWriter());
            txtTitle.setText(bannersList.getTitle());
            txtDate.setText(bannersList.getDate());
        }

    }

}