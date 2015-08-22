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
    private ArrayAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view =inflater.inflate(R.layout.product_brand_fragment, container, false);
        //ListView lv = (ListView)view.findViewById(android.R.id.list);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //do when hidden
            adapter.notifyDataSetChanged();
            adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);

            adapter.notifyDataSetChanged();
        } else {
            //do when show
        }
    }
    public void setAdapterValue(String[] values){
       this.values=values;

      // productBrandsListAdapter=new ProductBrandsListAdapter(getActivity(),values);

    }

    @Override
    public void onResume() {
        super.onResume();

    }




    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
