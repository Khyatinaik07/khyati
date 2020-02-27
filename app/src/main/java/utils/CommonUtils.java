package utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.util.Log;

import com.example.onlineserviceportal.R;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CommonUtils {

    private CommonUtils() {
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static String toBase64(String string) {
        String encodeValue = null;
        encodeValue = Base64.encodeToString(string.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT).trim();
        Log.i("toBase64", "encodeValue: " + encodeValue);
        return encodeValue;
    }

    public static String numberFormatter(double data)
    {
        NumberFormat formatter = new DecimalFormat("##.##");
        return formatter.format(data);
    }

}
