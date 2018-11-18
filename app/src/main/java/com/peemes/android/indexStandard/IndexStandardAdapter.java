package com.peemes.android.indexStandard;

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
 * Created by cshao on 2018/11/8.
 */

public class IndexStandardAdapter extends RecyclerView.Adapter<IndexStandardAdapter.ViewHolder>{
    private List<Index> mIndexList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        EditText editTextVal;
        TextView textViewUOM;
        Button buttonModify;
        public ViewHolder(View view){
            super(view);
            textViewName = (TextView)view.findViewById(R.id.textView_standard_item_name);
            editTextVal = (EditText)view.findViewById(R.id.editText_standard_item_val);
            textViewUOM = (TextView)view.findViewById(R.id.textView_standard_item_uom);
            buttonModify = (Button)view.findViewById(R.id.button_standard_item_modify);
        }
    }
    public IndexStandardAdapter(List<Index> IndexList){
        mIndexList = IndexList;
    }

    @Override
    //在该方法中获得ViewHolder的实例，然后对任意子项注册点击事件
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standard_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.buttonModify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                int position = holder.getAdapterPosition();
                Index index = mIndexList.get(position);
                String temp = holder.editTextVal.getText().toString();
                index.setVal(temp);
                Toast.makeText(v.getContext(),"修改之前的值："+index.getVal()+"  修改后的值为："+temp,
                        Toast.LENGTH_SHORT).show();
                //更新服务端的数据库
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String json = gson.toJson(index);
                RequestBody requestBody = FormBody.create(MediaType
                        .parse("application/json; charset = utf-8"),json);
                Request request = new Request.Builder()
                        .url("http://10.6.102.10:8080/PEEMES/NewIndexStandardValServlet")
                        .post(requestBody)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("IndexStandardAdapter",call.toString());
                        Log.d("IndexStandardAdapter","修改指标基准值是吧，在往服务器中传输被修改的值失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String res = response.body().string();
                        Looper.prepare();
                        if (res.equals("success")) {
                            Toast.makeText(v.getContext(),"同步服务器数据成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(v.getContext(),"同步服务器数据失败",Toast.LENGTH_SHORT).show();
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
        Index index = mIndexList.get(position);
        holder.textViewName.setText(index.getName());
        holder.editTextVal.setText(index.getVal());
        holder.textViewUOM.setText(index.getUom());
    }

    @Override
    public int getItemCount() {
        return mIndexList.size();
    }
}
