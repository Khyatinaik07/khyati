package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public final  class NetworkUtils {

    private NetworkUtils() {
        // This class is not publicly instantiable
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    public static void registerToFirebaseTopics(List<String> topics) {
        // Register to firebase topic
        for (String topic : topics) {
            FirebaseMessaging.getInstance().subscribeToTopic(topic);
        }
    }

}
