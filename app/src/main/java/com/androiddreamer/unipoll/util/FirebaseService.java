package com.androiddreamer.unipoll.util;


import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FirebaseService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

//    private StoreUserData storeUserData;

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("mariaa", "onTokenRefresh: " + refreshedToken);

        UDHelper udHelper = new UDHelper(getApplicationContext());
        udHelper.setString(UDHelper.KEY_FIREBASE_TOKEN, refreshedToken);

    }
    // [END refresh_token]


}
