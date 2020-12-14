package com.android.game2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.NewsViewHolder> {
    private List<ScoreBean> scoreBeans;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setNewsBeans(List<ScoreBean> newsBeans) {
        this.scoreBeans = newsBeans;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new NewsViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {
        ScoreBean newsBean = scoreBeans.get(position);
        holder.tvRank.setText((position + 1) + "");
        holder.tvTime.setText(newsBean.getCreateTime());
        holder.tvSore.setText(newsBean.getScore() + "");

    }

    @Override
    public int getItemCount() {
        return scoreBeans == null ? 0 : scoreBeans.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvRank;
        private TextView tvSore;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvRank = itemView.findViewById(R.id.tv_rank);
            tvSore = itemView.findViewById(R.id.tv_score);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
