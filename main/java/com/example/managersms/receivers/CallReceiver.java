package com.example.managersms.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.example.managersms.data.BlacklistDatabaseHelper;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                if (state == TelephonyManager.CALL_STATE_RINGING &&
                        BlacklistDatabaseHelper.getInstance(context).isBlacklisted(incomingNumber)) {
                    // Lưu ý: ngắt cuộc gọi yêu cầu quyền đặc biệt hoặc thiết bị root
                    // Có thể gửi thông báo hoặc ghi log
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }
}