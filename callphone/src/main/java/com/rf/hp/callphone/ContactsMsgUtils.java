package com.rf.hp.callphone;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2016/9/6.
 */
public class ContactsMsgUtils {

    /**
     * 获取联系人
     *
     * @param context
     * @return
     */
    public static List<ContactsInfo> getContactsInfos(Context context) {
        ContentResolver resolver = context.getContentResolver();
        List<ContactsInfo> infos = new ArrayList<ContactsInfo>();
        // 获取联系人数据 访问联系人的内容提供者
        // ContactsContract.AUTHORITY com.android.contacts 授权
        // 该内容提供者操作是需要读写权限
        // matcher.addURI(ContactsContract.AUTHORITY, "raw_contacts",
        // RAW_CONTACTS);
        // matcher.addURI(ContactsContract.AUTHORITY, "raw_contacts/#/data",
        // RAW_CONTACTS_DATA);
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        //Uri uri = Uri.parse("content://icc/adn");
        Cursor cursor1 = resolver.query(uri, new String[] { "_id" }, null,
                null, null);
        while (cursor1.moveToNext()) {
            int _id = cursor1.getInt(0);
            ContactsInfo info = new ContactsInfo();
            uri = Uri.parse("content://com.android.contacts/raw_contacts/"
                    + _id + "/data");
            Cursor cursor2 = resolver.query(uri, new String[] { "data1",
                    "mimetype" }, null, null, null);
            while (cursor2.moveToNext()) {
                String data1 = cursor2.getString(0);
                String mimetype = cursor2.getString(1);
                if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {// 号码
                    info.phone = data1;
                } else if ("vnd.android.cursor.item/name".equals(mimetype)) {// 姓名
                    info.name = data1;
                }
            }

            cursor2.close();
            infos.add(info);
        }
        cursor1.close();

        return infos;
    }

}
