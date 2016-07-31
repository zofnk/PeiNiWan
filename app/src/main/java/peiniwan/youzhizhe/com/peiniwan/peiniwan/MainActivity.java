package peiniwan.youzhizhe.com.peiniwan.peiniwan;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import peiniwan.youzhizhe.com.peiniwan.R;
import peiniwan.youzhizhe.com.peiniwan.fragment.BlankFragment1;
import peiniwan.youzhizhe.com.peiniwan.fragment.BlankFragment2;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabhost;
    private String item_name[] = new String[]{"广场","消息","玩","发现","我的"};
    private Class clas[] = new Class[] { BlankFragment1.class, BlankFragment2.class, BlankFragment1.class,
            BlankFragment2.class, BlankFragment1.class };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < item_name.length; i++) {
            View indicatorView = inflater.inflate(R.layout.indicator_item, null);
            TextView tv_indicator = (TextView) indicatorView.findViewById(R.id.tv_title_indicator);
            tv_indicator.setText(item_name[i]);
            mTabhost.addTab(mTabhost.newTabSpec(item_name[i]).setIndicator(indicatorView), clas[i], null);
        }
    }
}
