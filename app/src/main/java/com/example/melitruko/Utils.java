package com.example.melitruko;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import java.util.Objects;

public class Utils {

    public static void setupActionButtonPlayer(Context context, View view){
        view.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.layout_lista_jogadores);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
    }

}
