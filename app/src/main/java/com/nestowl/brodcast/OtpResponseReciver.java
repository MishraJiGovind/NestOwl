package com.nestowl.brodcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

public class OtpResponseReciver extends BroadcastReceiver {
    private  static EditText editText;
    public void setEditText(EditText editText){
        OtpResponseReciver.editText =  editText;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        for (SmsMessage sms : smsMessages){
                String massge =  sms.getMessageBody();
                String otp  = massge.split("is")[1];
                editText.setText(otp, null);
        }
    }
}
