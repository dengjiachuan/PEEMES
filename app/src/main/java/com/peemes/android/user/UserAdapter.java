package com.peemes.android.user;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.peemes.android.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by cshao on 2018/11/20.
 */

public class UserAdapter extends ArrayAdapter<User>{
    private int resourceID;
    public UserAdapter(Context context, int textViewResourceId, List<User> objects){
        super(context,textViewResourceId,objects);
        resourceID = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        final View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        }else{
            view = convertView;
        }
        //注册子项的每一个控件，以获取相应的实例
        final TextView textViewID = (TextView)view.findViewById(R.id.user_item_number);
        final TextView textViewName = (TextView)view.findViewById(R.id.user_item_name);
        Button buttonModify = (Button)view.findViewById(R.id.user_item_modify);
        Button buttonDetele = (Button)view.findViewById(R.id.user_item_delete);
        //对每个子项的控件进行相应的操作
        textViewID.setText(user.getUserID());
        textViewName.setText(user.getUserName());
        //修改密码操作
        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),ModifyActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        //删除用户操作
        buttonDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = textViewID.getText().toString();
                String account = textViewName.getText().toString();
                //将工号和用户名传入数据库中，根据工号和用户名进行用户删除
                User user1 = new User();
                OkHttpClient client = new OkHttpClient();
                user1.setUserID(id);
                user1.setUserName(account);
                //使用GSON将对象转化成json格式的数据
                Gson gson = new Gson();
                String json = gson.toJson(user1);
                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset = utf-8"),json);
                Request request = new Request.Builder()
                        .url("http://10.6.12.124:8080/PEEMES/DeleteUserServlet")
                        .post(requestBody)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("UserAdapter",call.toString());
                        Log.d("UserAdapter","往服务器传送数据失败，删除用户数据失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        Looper.prepare();
                        if (res.equals("{\"success\":\"success\"}")) {
                            Toast.makeText(view.getContext(),"删除用户成功",Toast.LENGTH_SHORT).show();
                        } else if (res.equals("{\"success\":\"failed\"}")) {
                            Toast.makeText(view.getContext(),"用户不存在,删除用户失败",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(view.getContext(),"删除用户失败",Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();
                    }
                });
            }
        });
        return view;
    }
}
