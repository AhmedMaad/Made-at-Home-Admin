package com.maad.madeathome;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final Activity activity;
    private final ArrayList<ProductModel> productModels;
    private OnItemClickListener onItemClickListener;
    private OnDeleteItemClickListener onDeleteItemClickListener;

    public ProductAdapter(Activity activity, ArrayList<ProductModel> productModels) {
        this.activity = activity;
        this.productModels = productModels;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(int position);
    }

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener onDeleteItemClickListener) {
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ProductViewHolder(view, onItemClickListener, onDeleteItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productTV.setText(productModels.get(position).getTitle());
        Glide
                .with(activity)
                .load(productModels.get(position).getImage())
                .into(holder.productIV);
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView productIV;
        public TextView productTV;
        public ImageView deleteIV;

        public ProductViewHolder(@NonNull View itemView, OnItemClickListener clickListener
                , OnDeleteItemClickListener deleteItemClickListener) {
            super(itemView);
            productIV = itemView.findViewById(R.id.iv_product);
            productTV = itemView.findViewById(R.id.tv);
            deleteIV = itemView.findViewById(R.id.iv_delete);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                clickListener.onItemClick(position);
            });

            deleteIV.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                deleteItemClickListener.onDeleteItemClick(position);
                return true;
            });

        }
    }
}
