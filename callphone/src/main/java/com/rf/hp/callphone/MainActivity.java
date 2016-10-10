package com.rf.hp.callphone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvFriend;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                String name = Thread.currentThread().getName();

                Toast.makeText(MainActivity.this, "1234", Toast.LENGTH_SHORT).show();


            }
            return true;
        }
    });
    private String nativePhoneNumber;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDeviceInfo myDeviceInfo = new MyDeviceInfo(this);
        nativePhoneNumber = myDeviceInfo.getNativePhoneNumber();
        lvFriend = (ListView) findViewById(R.id.lv_friends);
        final List<ContactsInfo> infos = ContactsMsgUtils.getContactsInfos(getApplicationContext());
        MyAdapter adapter = new MyAdapter(infos, this);
        lvFriend.setAdapter(adapter);
        lvFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                phone = infos.get(i).getPhone();

                //Toast.makeText(MainActivity.this, "==>"+i, Toast.LENGTH_SHORT).show();

                Dialog alertDialog = new AlertDialog.Builder(MainActivity.this).
                        setTitle("拨号").
                        setMessage("是否打电话给" + infos.get(i).getName() + "?").
                        setIcon(R.mipmap.ic_launcher).
                        setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String name = Thread.currentThread().getName();
                                        String replace = nativePhoneNumber.replace("+86", "");
                                        SDKTestCallback sdkTestCallback = new SDKTestCallback(replace, phone);
                                        sdkTestCallback.callFriend();
                                        System.out.println("===1234==>"+name);
                                        Message msg = new Message();
                                        msg.what = 1;
                                        handler.sendMessage(msg);
                                    }
                                });
                                thread.start();


                            }
                        }).
                        setNegativeButton("否", null).
                        create();
                alertDialog.show();
            }
        });

    }

    private class MyAdapter extends BaseAdapter {
        //
        List<ContactsInfo> infos = new ArrayList<ContactsInfo>();
        Context context;

        public MyAdapter(List<ContactsInfo> infos, Context context) {
            super();
            this.context = context;
            this.infos = infos;
        }

        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.contacts_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ContactsInfo info = infos.get(position);
            if (!TextUtils.isEmpty(info.phone)) {
                viewHolder.tv_number.setText(info.phone);
                viewHolder.tv_name.setText(info.name);
            }

            return convertView;
        }

        class ViewHolder {
            TextView tv_number;
            TextView tv_name;
        }

    }
}
