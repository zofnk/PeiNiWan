package peiniwan.youzhizhe.com.peiniwan.peiniwan;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import peiniwan.youzhizhe.com.peiniwan.R;

public class MessageActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initUI();
    }
    private void initUI() {
        ListView chat_lv = (ListView) findViewById(R.id.chat_lv);
        ImageView img = (ImageView) findViewById(R.id.imageView1);
        img.setOnClickListener(this);
        Myadapter myadapter = new Myadapter();
        chat_lv.setAdapter(myadapter);
    }

    class Myadapter extends BaseAdapter
    {
        public static final int TYPE_ONE = 0;
        public static final int TYPE_TWO = 1;
        String[] s = new String[]{"a","ssss","b","dddd","c","wersdas","d","hjskakdjh"};

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return s.length;
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
        public int getItemViewType(int position) {
            if (s[position].length() == 1) {
                return TYPE_ONE;
            }else {
                return TYPE_TWO;
            }
        }

        @Override
        public int getViewTypeCount() {

            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layout = null;
            switch (getItemViewType(position)) {
                case TYPE_ONE:
                    layout = getLayoutInflater().inflate(R.layout.chat_left, null);
                    TextView tl = (TextView) layout.findViewById(R.id.textView1);
                    tl.setText(s[position]);
                    break;

                case TYPE_TWO:
                    layout = getLayoutInflater().inflate(R.layout.chat_right, null);
                    TextView tr = (TextView) layout.findViewById(R.id.textView1);
                    tr.setText(s[position]);
                    break;
            }
            return layout;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.xiaoxi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imageView1 :
                finish();
                break;

            default:
                break;
        }
    }
}
