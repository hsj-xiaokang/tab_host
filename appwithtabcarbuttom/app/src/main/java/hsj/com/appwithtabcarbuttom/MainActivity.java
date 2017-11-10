package hsj.com.appwithtabcarbuttom;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import hsj.com.appwithtabcarbuttom.bean.Tab;
import hsj.com.appwithtabcarbuttom.fragment.CartFragment;
import hsj.com.appwithtabcarbuttom.fragment.CategoryFragment;
import hsj.com.appwithtabcarbuttom.fragment.HomeFragment;
import hsj.com.appwithtabcarbuttom.fragment.HotFragment;
import hsj.com.appwithtabcarbuttom.fragment.MineFragment;
import hsj.com.appwithtabcarbuttom.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

/**
 * http://blog.csdn.net/wuyinlei/article/details/50096989
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private ImageView img;
    private TextView text;

    //tab菜单的下标值
    private int fragmentContentItemMenu_0_index = 0;
    private int fragmentContentItemMenu_1_index = 1;
    private int fragmentContentItemMenu_2_index = 2;
    private int fragmentContentItemMenu_3_index = 3;
    private int fragmentContentItemMenu_4_index = 4;

    private List<Tab> mTabs = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();
    }


    public void initTab() {
        Tab home = new Tab(R.string.home,R.drawable.selector_icon_home,HomeFragment.class);
        Tab hot = new Tab(R.string.hot,R.drawable.selector_icon_hot, HotFragment.class);
        Tab category = new Tab(R.string.category,R.drawable.selector_icon_category,CategoryFragment.class);
        Tab cart = new Tab(R.string.cart,R.drawable.selector_icon_cart,CartFragment.class);
        Tab mine = new Tab(R.string.mine,R.drawable.selector_icon_mine,MineFragment.class);

        mTabs.add(home);
        mTabs.add(hot);
        mTabs.add(category);
        mTabs.add(cart);
        mTabs.add(mine);

        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab:mTabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(builderIndiator(tab));
            mTabHost.addTab(tabSpec,tab.getFragment(),null);
        }

        //去掉分割线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //设置当前项条目
        mTabHost.setCurrentTab(fragmentContentItemMenu_0_index);
        /**
         * 点击切换的事件
         */
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){

           @Override
           public void onTabChanged(String tabId){
               Log.d(TAG, "onTabChanged: "+tabId);
            }

        });
    }

    private View builderIndiator(Tab tab){
        View view = mInflater.inflate(R.layout.tab_indicator, null);

        img = (ImageView) view.findViewById(R.id.icon_tab);
        text = (TextView) view.findViewById(R.id.text_indicator);
        img.setBackgroundResource(tab.getImage());
        text.setText(tab.getTitle());

        return view;
    }

}
