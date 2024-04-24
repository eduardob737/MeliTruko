package com.example.melitruko.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melitruko.R;
import com.example.melitruko.model.Player;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        if ((players != null) && (!players.isEmpty())) {

            Player player = players.get(position);
            holder.tvPlayer.setText(player.getName());
            holder.ivPlayer.setImageURI(player.getPhoto());
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        public ShapeableImageView ivPlayer;
        public TextView tvPlayer;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPlayer = itemView.findViewById(R.id.iv_player);
            tvPlayer = itemView.findViewById(R.id.tv_player);
        }
    }
}
