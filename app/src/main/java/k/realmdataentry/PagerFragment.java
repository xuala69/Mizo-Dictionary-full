package k.realmdataentry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dsoloman on 31-03-2017.
 */

public class PagerFragment extends Fragment {

    private String title;
    private int page;


    public PagerFragment() {
    }

    public static PagerFragment newInstance(String title, int page) {
        PagerFragment pagefrag = new PagerFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someString", title);
        pagefrag.setArguments(args);
        return pagefrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager, container, false);
        return v;

    }
}
