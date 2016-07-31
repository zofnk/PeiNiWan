package peiniwan.youzhizhe.com.peiniwan.ChoiceCity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import peiniwan.youzhizhe.com.peiniwan.ChoiceCity.view.LetterSideBar;
import peiniwan.youzhizhe.com.peiniwan.R;
import peiniwan.youzhizhe.com.peiniwan.entity.Cities;
import peiniwan.youzhizhe.com.peiniwan.entity.City;
import peiniwan.youzhizhe.com.peiniwan.utils.GsonUtils;
import peiniwan.youzhizhe.com.peiniwan.utils.HTTPUtils;
import peiniwan.youzhizhe.com.peiniwan.utils.VolleyListener;

public class CityActivity extends Activity {
    private View nocityView;
    private List<City> mAllCities = new ArrayList();
    private List<City> mSearchCities = new ArrayList();
    private CityAdatper mCityAdapter;
    private TextView tvLetter;
    private ListView mCityListView;
    private LetterSideBar mLetterSideBar;
    private EditText mSearchEdit;
    private ListView mSearchListView;
    private SearchAdapter mSearchAdapter;
    private String mInputKey;
    private static final String FILE_NAME = "city.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initUI();
        initData();
    }

    private void initUI() {
        nocityView = findViewById(R.id.nocity);
        mSearchListView = (ListView) findViewById(R.id.list_search);
        mSearchAdapter = new SearchAdapter();
        mSearchListView.setAdapter(mSearchAdapter);
        mSearchEdit = (EditText) findViewById(R.id.citylist_search);
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mInputKey = s.toString().toLowerCase();
                if ("".equals(mInputKey)) {
                    mSearchListView.setVisibility(View.GONE);
                } else {
                    mSearchListView.setVisibility(View.VISIBLE);
                    mSearchCities.clear();// 清空上一次的搜索结果
                    // 更新SearchListView数据
                    for (int i = 0; i < mAllCities.size(); i++) {
                        City City = mAllCities.get(i);
                        // 是否以用户输入的拼音或中文为开头
                        if (City.getName().startsWith(mInputKey) || City.getPinyin().startsWith(mInputKey)) {
                            mSearchCities.add(City);
                            mSearchAdapter.notifyDataSetChanged();
                        }
                    }
                    if (mSearchCities.size() == 0) {
                        nocityView.setVisibility(View.VISIBLE);
                    } else {
                        nocityView.setVisibility(View.GONE);
                    }
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
        tvLetter = (TextView) findViewById(R.id.overlay_alpha_text);
        mCityListView = (ListView) findViewById(R.id.list_flight_city);
        mCityAdapter = new CityAdatper();
        mCityListView.setAdapter(mCityAdapter);
        mLetterSideBar = (LetterSideBar) findViewById(R.id.citylist_alpha_bar);
        mLetterSideBar.setOnLetterChangedListener(new LetterSideBar.OnLetterChangedListener() {
            @Override
            public void onTouchAction(int action) {
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        tvLetter.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        tvLetter.setVisibility(View.GONE);

                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onLetterChanged(String letter) {
                tvLetter.setText(letter);
                // 根据传入的letter，查找字母开头的组名的position
                for (int i = 0; i < mAllCities.size(); i++) {
                    City City = mAllCities.get(i);
                    String string = City.getPinyin();
                    if (letter.equals(string.substring(0, 1).toUpperCase())) {
                        mCityListView.setSelection(i);
                        return;
                    }
                }
            }
        });
    }

    class SearchAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mSearchCities.size();
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
            View layout = getLayoutInflater().inflate(R.layout.citylist_title_item, null);
            TextView tvTitle = (TextView) layout.findViewById(R.id.citylist_title);
            City City = mSearchCities.get(position);
            String name = City.getName();
            if (name.startsWith(mInputKey)) {
                // 设置某个文字颜色
                SpannableStringBuilder builder = new SpannableStringBuilder(name);
                ForegroundColorSpan greenSpan = new ForegroundColorSpan(Color.parseColor("#ff06c1ae"));
                builder.setSpan(greenSpan, 0, mInputKey.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                tvTitle.setText(builder);
            } else {
                tvTitle.setText(name);
            }
            return layout;
        }

    }

    class CityAdatper extends BaseAdapter {
        public int getCount() {
            return mAllCities.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View layout = getLayoutInflater().inflate(R.layout.citylist_item, null);
            TextView tvCity = (TextView) layout.findViewById(R.id.citylist_textview);
            TextView tvLabel = (TextView) layout.findViewById(R.id.citylist_letter_textview);
            City City = mAllCities.get(position);
            String city = City.getPinyin();
            // 获得当前行的首字母
            String firstLetter = city.substring(0, 1).toUpperCase();
            tvLabel.setText(firstLetter);
            // 如果第0行，需要显示首字母，从第1行开始需要与上一行比较
            if (position > 0) {
                // 获得上一行的首字母
                City previousCity = mAllCities.get(position - 1);
                String previousLetter = previousCity.getPinyin().substring(0, 1).toUpperCase();
                // 用户：同样首字母，第一个显示
                // 程序：当前行首字母与上一行不同
                if (!firstLetter.equals(previousLetter)) {
                    tvLabel.setVisibility(View.VISIBLE);
                } else {
                    tvLabel.setVisibility(View.GONE);
                }
            }
            tvCity.setText(City.getName());
            return layout;
        }
    }

   /* private String getJsonFile() {

        String result = "";
        try {
            InputStream is = getResources().getAssets().open(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bf.readLine()) != null)
                result += line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }*/

    private void initData() {
        String url = "http://7xos3n.com1.z0.glb.clouddn.com/cities.txt";
        HTTPUtils.get(this, url, new VolleyListener() {
            public void onResponse(String arg0) {
                Cities cities = GsonUtils.parseJSON(arg0, Cities.class);
                List<City> City = cities.getAllcity();
                mAllCities.addAll(City);
                mCityAdapter.notifyDataSetChanged();
            }

            public void onErrorResponse(VolleyError arg0) {
            }
        });
    }
}
