package com.arash.altafi.mvp.category;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.data.Category;
import com.arash.altafi.mvp.listCategory.List_Category_Activity;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter_Category extends RecyclerView.Adapter<Adapter_Category.ViewHolder> {

    List<Category> categories;

    public Adapter_Category(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_category);
            textView = itemView.findViewById(R.id.txt_category);
        }

        public void bind(Category category)
        {
            Glide.with(itemView.getContext()).load(category.getImage()).into(imageView);
            textView.setText(category.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext() , List_Category_Activity.class);
                    intent.putExtra("grouping" , category.getNumber());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }

}