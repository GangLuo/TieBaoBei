package com.zhixiangzhonggong.tiebaobei.CustomizedClass;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.zhixiangzhonggong.tiebaobei.R;
import com.zhixiangzhonggong.tiebaobei.adapter.ProductBrandsListAdapter;


public class ProductBrandFragment extends ListFragment {
    private ProductBrandsListAdapter productBrandsListAdapter;
    private String[] values;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view =inflater.inflate(R.layout.product_brand_fragment, container, false);
        //ListView lv = (ListView)view.findViewById(android.R.id.list);
       values = new String[] { "不限", "三一重工", "卡特彼勒",
                "小松", "日立", "斗山", "现代", "神钢",
                "住友", "柳工","临工", "加藤", "凯斯", "阿特拉斯", "福田雷沃",
               "玉柴", "中联重科","徐工", "沃得重工", "山东犀牛", "夏工", "合肥振宇"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        productBrandsListAdapter=new ProductBrandsListAdapter(getActivity(),values);
        //lv.setAdapter(productBrandsListAdapter);
      // setListAdapter(productBrandsListAdapter);
        setListAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
