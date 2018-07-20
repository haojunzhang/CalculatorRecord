package idv.haojun.calculatorrecorder;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class DialogHelper {
    public static void showMessageDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}
