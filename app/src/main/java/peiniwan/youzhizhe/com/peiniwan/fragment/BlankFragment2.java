package peiniwan.youzhizhe.com.peiniwan.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import peiniwan.youzhizhe.com.peiniwan.R;
import peiniwan.youzhizhe.com.peiniwan.peiniwan.MessageActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {
    private View layout;
    private LayoutInflater mInflater;

    public BlankFragment2() {
        // Required empty public constructor
//		inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if (layout == null) {
            mInflater = inflater;
            initUI(inflater);
            initData();
        }
        return layout;
    }

    private void initData() {
        // TODO 数据添加

    }

    //界面UI搭建
    private void initUI(LayoutInflater inflater) {
        this.mInflater = inflater;
        layout = inflater.inflate(R.layout.frag_blank2, null);
        ListView xiaoxi_lv = (ListView) layout.findViewById(R.id.xiaoxi_lv);
        MyAdapter adapter = new MyAdapter();
        xiaoxi_lv.setAdapter(adapter);

        //listview点击事件
        xiaoxi_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //要写逻辑代码,判断是哪个客户的消息,传到主界面
                Intent intent = new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 10;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View xiaoxi_layout = mInflater.inflate(R.layout.item_xiaoxi, null);
            return xiaoxi_layout;
        }

    }

}
