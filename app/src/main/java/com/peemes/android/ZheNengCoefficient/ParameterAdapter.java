package com.peemes.android.ZheNengCoefficient;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by cshao on 2018/10/24.
 */

public class ParameterAdapter extends RecyclerView.Adapter<ParameterAdapter.ViewHolder> {
    private List<ZheNengParameter> myZnpLsit;
    static class ViewHolder extends RecyclerView.ViewHolder{
        Button button_modify;
        TextView textView_id;
        TextView textView_name;
        EditText editText_val;
        TextView textView_uom;
        public ViewHolder(View view){
            super(view);
            button_modify = (Button)view.findViewById(R.id.button_item_modify);
            textView_id = (TextView)view.findViewById(R.id.textview_item_id);
            textView_name = (TextView)view.findViewById(R.id.textview_item_name);
            textView_uom = (TextView)view.findViewById(R.id.textview_item_uom);
            editText_val = (EditText)view.findViewById(R.id.edit_item_value);
        }
    }

    public ParameterAdapter(List<ZheNengParameter> myZnpLsit) {

        this.myZnpLsit = myZnpLsit;
    }

    @Override
    //在该方法中获取ViewHolder的实例，然后对子项的任意控件注册点击事件
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paramete_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.button_modify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                int position = holder.getAdapterPosition();
                ZheNengParameter znp = myZnpLsit.get(position);
                //得到修改的值，然后传入书库中，更新数据库,此处先将数据输出即可；
                String temp = holder.editText_val.getText().toString();
                Toast.makeText(v.getContext(),"修改前的值为"+znp.getVal()+"   修改后的值"
                        +temp,Toast.LENGTH_SHORT).show();
                //同时将对应的值改正
                znp.setVal(temp);
                //更新服务端的数据库
                //ZheNengParameter zheNengParameter = new ZheNengParameter();
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String json = gson.toJson(znp);
                RequestBody requestBody = FormBody.
                        create(MediaType.parse("application/json; charset = utf-8"),json);
                Request request = new Request.Builder()
                        .url("http://10.6.76.128:8080/PEEMES/NewZheNengCanShuServlet")
                        .post(requestBody)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("ParameterAdapter",call.toString());
                        Log.d("ParameterAdapter","修改折能参数失败，在往服务器中传输被修改的值失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String res = response.body().string();
                        Looper.prepare();
                        if (res.equals("success")) {
                            Toast.makeText(v.getContext(),"同步服务器数据成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(v.getContext(),"服务器数据同步失败",Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();
                    }
                });
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ZheNengParameter znp = myZnpLsit.get(position);
        holder.textView_id.setText(znp.getId());
        holder.textView_name.setText(znp.getName());
        holder.editText_val.setText(znp.getVal());
        holder.textView_uom.setText(znp.getUom());
    }

    @Override
    public int getItemCount() {
        return myZnpLsit.size();
    }
}
