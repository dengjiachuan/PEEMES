package com.peemes.android.ZheNengCoefficient;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.peemes.android.R;

import java.util.List;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return myZnpLsit.size();
    }
}
