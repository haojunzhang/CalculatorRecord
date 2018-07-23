package idv.haojun.calculatorrecorder.helper;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import idv.haojun.calculatorrecorder.R;

public class DialogHelper {
    public static void showMessageDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}
