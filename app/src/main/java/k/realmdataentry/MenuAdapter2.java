package k.realmdataentry;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Dsoloman on 30-03-2017.
 */

public class MenuAdapter2 extends FragmentPagerAdapter {

    int numItems = 4;
    List<Fragment> fragmentList;

    public MenuAdapter2(FragmentManager fm,List<Fragment> list) {
        super(fm);
        fragmentList=list;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PagerFragment.newInstance("Title",10);
            case 1:
                return PagerFragment2.newInstance("Title",1);
            case 2:
                return PagerFragment3.newInstance("Title",2);
            case 3:
                return PagerFragment4.newInstance("Title",3);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numItems;
    }

}
