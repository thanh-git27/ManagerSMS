package com.example.managersms.receivers;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.provider.Telephony;
import android.widget.Toast;
import com.example.managersms.data.BlacklistDatabaseHelper;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage msg : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String sender = msg.getDisplayOriginatingAddress();
                if (BlacklistDatabaseHelper.getInstance(context).isBlacklisted(sender)) {
                    abortBroadcast();
                    Toast.makeText(context, "Tin nhắn từ số bị chặn: " + sender, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}