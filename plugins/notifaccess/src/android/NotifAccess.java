package com.yourapp.notifaccess;

import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class NotifAccess extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext cb) throws JSONException {
    if ("isEnabled".equals(action)) {
      cb.success(isNotificationServiceEnabled() ? 1 : 0);
      return true;
    }
    if ("openSettings".equals(action)) {
      openNotificationAccessSettings();
      cb.success(1);
      return true;
    }
    return false;
  }

  private boolean isNotificationServiceEnabled() {
    String pkgName = cordova.getActivity().getPackageName();
    final String flat = Settings.Secure.getString(
        cordova.getActivity().getContentResolver(),
        "enabled_notification_listeners"
    );
    if (TextUtils.isEmpty(flat)) return false;
    return flat.contains(pkgName);
  }

  private void openNotificationAccessSettings() {
    Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    cordova.getActivity().startActivity(intent);
  }
}
