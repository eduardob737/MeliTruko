package com.example.melitruko.presentation.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.melitruko.R;
import com.example.melitruko.domain.model.Player;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private final List<Player> players;
    private int colorBackgroundChosenPlayer;
    private int colorTextChosenPlayer;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_player, parent, false);
        colorBackgroundChosenPlayer = view.getResources().getColor(R.color.gray, view.getContext().getTheme());
        colorTextChosenPlayer = view.getResources().getColor(R.color.gray_text, view.getContext().getTheme());
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        if ((players != null) && (!players.isEmpty())) {

            Player player = players.get(position);
            holder.tvPlayer.setText(player.getName());

            Glide.with(holder.itemView)
                    .load(player.getPhoto())
                    .into(holder.ivPlayer);

            if (players.get(position).isPartOfATeam()){
                holder.constraintLayout.setBackgroundColor(colorBackgroundChosenPlayer);
                holder.constraintLayout.setAlpha(0.5f);
                holder.tvPlayer.setTextColor(colorTextChosenPlayer);
                holder.itemView.setEnabled(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        public ShapeableImageView ivPlayer;
        public TextView tvPlayer;
        public ConstraintLayout constraintLayout;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPlayer = itemView.findViewById(R.id.iv_player);
            tvPlayer = itemView.findViewById(R.id.tv_player);
            constraintLayout = itemView.findViewById(R.id.constraint_main);
        }
    }
}
