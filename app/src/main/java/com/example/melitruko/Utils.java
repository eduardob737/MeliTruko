package com.example.melitruko;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melitruko.domain.model.Player;
import com.example.melitruko.presentation.ui.view.adapter.PlayerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static List<Player> getList(){
        File imagem = new File("/data/data/com.example.melitruko/files/foto_isabela.jpeg");

        Uri foto = Uri.fromFile(imagem);

        List<Player> playerList = new ArrayList<>();

        Player player1 = new Player();
        player1.setName("Eduardo");
        player1.setPhoto(foto);
        playerList.add(player1);

        Player player2 = new Player();
        player2.setName("Wilson");
        player2.setPhoto(foto);
        playerList.add(player2);

        Player player3 = new Player();
        player3.setName("Bruno");
        player3.setPhoto(foto);
        playerList.add(player3);

        Player player4 = new Player();
        player4.setName("Leo");
        player4.setPhoto(foto);
        playerList.add(player4);

        Player player5 = new Player();
        player5.setName("Denis");
        player5.setPhoto(foto);
        playerList.add(player5);

        Player player6 = new Player();
        player6.setName("Lucas");
        player6.setPhoto(foto);
        playerList.add(player6);

        Player player7 = new Player();
        player7.setName("Bruno");
        player7.setPhoto(foto);
        playerList.add(player7);

        Player player8 = new Player();
        player8.setName("Leo");
        player8.setPhoto(foto);
        playerList.add(player8);

        Player player9 = new Player();
        player9.setName("Denis");
        player9.setPhoto(foto);
        playerList.add(player9);

        Player player10 = new Player();
        player10.setName("Lucas");
        player10.setPhoto(foto);
        playerList.add(player10);

        /*Player player11 = new Player();
        player11.setName("Bruno");
        player11.setPhoto(foto);
        playerList.add(player11);

        Player player12 = new Player();
        player12.setName("Leo");
        player12.setPhoto(foto);
        playerList.add(player12);

        Player player13 = new Player();
        player13.setName("Denis");
        player13.setPhoto(foto);
        playerList.add(player13);

        Player player14 = new Player();
        player14.setName("Ultimo");
        player14.setPhoto(foto);
        playerList.add(player14);
*/
        return playerList;
    }

    public static void setupActionButtonPlayer(Context context, View view){
        Dialog dialog = new Dialog(context);
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
