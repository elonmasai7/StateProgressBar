package com.kofigyan.stateprogressbar.components;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class TooltipUtil {

    public static void showTooltip(Context context, View view, String message) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        Toast tooltip = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        tooltip.setGravity(Gravity.TOP | Gravity.START, location[0], location[1] - view.getHeight());
        tooltip.show();
    }
}
