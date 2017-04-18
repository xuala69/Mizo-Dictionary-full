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

public class PagerFragment2 extends Fragment {

    private String title;
    private int page;


    public PagerFragment2() {
    }

    public static PagerFragment2 newInstance(String title, int page) {
        PagerFragment2 pagefrag = new PagerFragment2();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someString", title);
        pagefrag.setArguments(args);
        return pagefrag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("Some Int");
        title = getArguments().getString("Some String");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment2, container, false);
        return v;

    }
}