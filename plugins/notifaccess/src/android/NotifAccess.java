package com.notifaccess;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;

import org.apache.cordova.CordovaPlugin;

public class NotifAccess extends CordovaPlugin {

    @Override
    protected void pluginInitialize() {
        try {
            Context ctx = cordova.getContext();
            if (!isNotificationListenerEnabled(ctx)) {
                // Buka layar akses notifikasi (Notification Listener)
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        } catch (Exception ignored) {
        }
    }

    private boolean isNotificationListenerEnabled(Context context) {
        try {
            String enabledListeners = Settings.Secure.getString(
                    context.getContentResolver(),
                    "enabled_notification_listeners"
            );
            if (TextUtils.isEmpty(enabledListeners)) return false;

            // format string biasanya: "com.app/.ServiceName:com.app2/.Service2"
            return enabledListeners.contains(context.getPackageName());
        } catch (Exception e) {
            return false;
        }
    }
}
