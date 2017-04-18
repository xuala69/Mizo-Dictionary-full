package k.realmdataentry;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

public class Tex extends FragmentActivity implements
        TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private TabHost mTabHost;
    private ViewPager mViewPager;
    private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, Tex.TabInfo>();
    private MenuAdapter2 mPagerAdapter;
    int position;


    private class TabInfo {
        private String tag;
        private Class<?> clss;
        private Bundle args;
        private Fragment fragment;

        TabInfo(String tag, Class<?> clazz, Bundle args) {
            this.tag = tag;
            this.clss = clazz;
            this.args = args;
        }

    }

    public static void setTabColor(TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#0000"));
        }
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#9796ea"));
    }


    class TabFactory implements TabContentFactory {

        private final Context mContext;


        public TabFactory(Context context) {
            mContext = context;
        }


        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity_layout);
        this.initialiseTabHost(savedInstanceState);
        this.intialiseViewPager();
        position = getIntent().getIntExtra("id", 0);
        this.mViewPager.setCurrentItem(position);
     }


    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag());
         super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mTabHost.setCurrentTabByTag(savedInstanceState
                    .getString("tab"));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


    private void intialiseViewPager() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, PagerFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, PagerFragment2.class.getName()));
        fragments.add(Fragment.instantiate(this, PagerFragment3.class.getName()));
        fragments.add(Fragment.instantiate(this, PagerFragment4.class.getName()));
        this.mPagerAdapter = new MenuAdapter2(
                super.getSupportFragmentManager(), fragments);
        this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setPageTransformer(true, new CubeOutTransformer());
        this.mViewPager.setOnPageChangeListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initialiseTabHost(Bundle args) {
        mTabHost = (TabHost) findViewById(R.id.tabHost);
        mTabHost.setup();
        TabInfo tabInfo = null;

        Tex.AddTab(this, this.mTabHost, this.mTabHost
                        .newTabSpec("Tab1").setIndicator("Zawhnate"),
                (tabInfo = new TabInfo("Tab1", PagerFragment.class, args)));
        TextView x = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        x.setTextSize(7);
        x.setMaxLines(1);
        x.setAllCaps(false);
        this.mapTabInfo.put(tabInfo.tag, tabInfo);

        Tex.AddTab(this, this.mTabHost, this.mTabHost
                        .newTabSpec("Tab2").setIndicator("Siamtu lam"),
                (tabInfo = new TabInfo("Tab2", PagerFragment2.class, args)));
        TextView y = (TextView) mTabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        y.setTextSize(7);
        y.setMaxLines(1);
        y.setAllCaps(false);
        this.mapTabInfo.put(tabInfo.tag, tabInfo);

        Tex.AddTab(this, this.mTabHost, this.mTabHost
                        .newTabSpec("Tab3").setIndicator("Thumal Belhna"),
                (tabInfo = new TabInfo("Tab3", PagerFragment3.class, args)));
        TextView z = (TextView) mTabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        z.setTextSize(7);
        z.setMaxLines(1);
        z.setAllCaps(false);
        this.mapTabInfo.put(tabInfo.tag, tabInfo);

        Tex.AddTab(this, this.mTabHost, this.mTabHost
                        .newTabSpec("Tab4").setIndicator("Fakselna"),
                (tabInfo = new TabInfo("Tab4", PagerFragment4.class, args)));
        TextView zc = (TextView) mTabHost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
        zc.setTextSize(7);
        zc.setMaxLines(1);
        zc.setAllCaps(false);
        this.mapTabInfo.put(tabInfo.tag, tabInfo);

        mTabHost.setOnTabChangedListener(this);
    }


    private static void AddTab(Tex activity,
                               TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
        // Attach a Tab view factory to the spec
        tabSpec.setContent(activity.new TabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    public void onTabChanged(String tag) {
         int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.mTabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
