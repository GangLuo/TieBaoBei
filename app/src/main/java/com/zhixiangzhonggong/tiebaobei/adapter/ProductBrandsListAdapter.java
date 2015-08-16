package com.zhixiangzhonggong.tiebaobei.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhixiangzhonggong.tiebaobei.R;

public class ProductBrandsListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private  String[] values;
    private LayoutInflater inflater;
    public ProductBrandsListAdapter(Activity context, String[] values) {
        super(context, R.layout.product_brand_fragment, values);
        this.context=context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.show_products_items, null);
        TextView textView = (TextView) convertView.findViewById(R.id.brand_name_id);
        textView.setText(values[position]);
        return convertView;
    }
}
