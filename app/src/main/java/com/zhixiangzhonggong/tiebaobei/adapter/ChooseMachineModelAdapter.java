package com.zhixiangzhonggong.tiebaobei.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;

import java.util.ArrayList;

public class ChooseMachineModelAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<String> chooseModelList;

    public ChooseMachineModelAdapter(Activity activity, ArrayList<String> chooseModelList) {
        this.activity = activity;
        this.chooseModelList=chooseModelList;
    }

    @Override
    public int getCount() {
        return chooseModelList.size();
    }

    @Override
    public Object getItem(int location) {
        return chooseModelList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.choose_machine_items, null);
        TextView chooseMachineModelName = (TextView) convertView.findViewById(R.id.chooseMachineModelName);
        chooseMachineModelName.setText(chooseModelList.get(position));

        return convertView;
    }
}
