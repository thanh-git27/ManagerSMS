package com.example.managersms.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.example.managersms.models.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactUtils {
    public static List<Contact> getContacts(Context context) {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(new Contact(name, phone));
            }
            cursor.close();
        }

        return contacts;
    }
}
