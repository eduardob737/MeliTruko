package com.example.melitruko;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melitruko.model.Player;
import com.example.melitruko.view.adapter.PlayerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static List<Player> getList(){
        File imagem = new File("/data/data/com.example.melitruko/files/foto_isabela.jpeg");

        Uri foto = Uri.fromFile(imagem);

        Player player1 = new Player();
        player1.setName("Eduardo");
        player1.setPhoto(foto);

        Player player2 = new Player();
        player2.setName("Wilson");
        player2.setPhoto(foto);

        Player player3 = new Player();
        player3.setName("Bruno");
        player3.setPhoto(foto);

        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        return playerList;
    }

    public static void setupActionButtonPlayer(Context context, View view){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_players_list);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        PlayerAdapter adapter = new PlayerAdapter(getList()) ;
        RecyclerView recycler = dialog.findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);

        view.setOnClickListener(view1 -> {
            dialog.show();
        });
    }

}
