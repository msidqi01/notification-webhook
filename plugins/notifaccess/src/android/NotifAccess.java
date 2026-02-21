package com.dormasaaktif.notifaccess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

public class NotifAccess extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("requestPermission".equals(action)) {
            openNotificationListenerSettings(callbackContext);
            return true;
        }
        if ("isEnabled".equals(action)) {
            boolean enabled = isNotificationServiceEnabled(this.cordova.getActivity());
            callbackContext.success(enabled ? 1 : 0);
            return true;
        }
        return false;
    }

    private void openNotificationListenerSettings(final CallbackContext cb) {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.cordova.getActivity().startActivity(intent);
            cb.success(1);
        } catch (Exception e) {
            cb.error("Failed to open notification access settings: " + e.getMessage());
        }
    }

    // cek apakah user sudah enable Notification Listener untuk app ini
    private boolean isNotificationServiceEnabled(Context context) {
        String pkgName = context.getPackageName();
        final String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(flat)) return false;

        final String[] names = flat.split(":");
        for (String name : names) {
            final ComponentName cn = ComponentName.unflattenFromString(name);
            if (cn != null && TextUtils.equals(pkgName, cn.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}            return false;
        }
    }
}
