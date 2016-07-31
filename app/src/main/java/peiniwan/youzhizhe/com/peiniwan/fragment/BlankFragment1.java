package peiniwan.youzhizhe.com.peiniwan.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.OnClick;
import peiniwan.youzhizhe.com.peiniwan.ChoiceCity.CityActivity;
import peiniwan.youzhizhe.com.peiniwan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment1 extends Fragment {


    private LayoutInflater mInflater;
    private View layout;

    public BlankFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layout == null) {
            mInflater = inflater;
            initUi(inflater);
            initData();
        }
        return layout;
    }

    private void initData() {
        // TODO 数据的添加

    }

    //UI界面的搭建
    private void initUi(LayoutInflater inflater) {

        this.mInflater = inflater;
        layout = inflater.inflate(R.layout.frag_blank1, null);
        ListView guangCh_lv = (ListView) layout.findViewById(R.id.guangchang_listview);
        final ImageView cityChoice= (ImageView) layout.findViewById(R.id.imageView1);
        cityChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CityActivity.class);
                startActivityForResult(intent , 0);
            }
        });
        MyAdapter myAdapter = new MyAdapter();
        guangCh_lv.setAdapter(myAdapter);

    }

    @OnClick(R.id.imageView1)
    public void onClick() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode) {
                case 0:
                    //TODO
                    break;
            }
        }
    }

    class MyAdapter extends BaseAdapter {

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
            View guangCh_layout = mInflater.inflate(R.layout.item_guangchang_layout, null);
            return guangCh_layout;
        }

    }
}
