package k.realmdataentry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dsoloman on 31-03-2017.
 */

public class PagerFragment4 extends Fragment {

    private String title;
    private int page;

    @BindView(R.id.feedbackET)
    EditText feedbackET;
    Button feedsbmtBT;


    public PagerFragment4() {
    }

    public static PagerFragment4 newInstance(String title, int page) {
        PagerFragment4 pagefrag = new PagerFragment4();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someString", title);
        pagefrag.setArguments(args);
        return pagefrag;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("Some Int");
        title = getArguments().getString("Some String");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment4, container, false);
         feedbackET=(EditText)v.findViewById(R.id.feedbackET);
        feedsbmtBT=(Button)v.findViewById(R.id.feedsbmtBT);
        FeedSubmitClicked();
        return v;

    }

    void FeedSubmitClicked() {
        feedsbmtBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] To=new String[]{"synxuala@gmail.com"};
                 Intent feedmail=new Intent(Intent.ACTION_SENDTO);
                feedmail.setType("message/rfc822");
                feedmail.putExtra(Intent.EXTRA_EMAIL,To);
                 feedmail.putExtra(Intent.EXTRA_SUBJECT,"FeedBack");
                feedmail.putExtra(Intent.EXTRA_TEXT,feedbackET.getText());
                feedmail.setData(Uri.parse("mailto:"));
                try {
                    startActivity(Intent.createChooser(feedmail,"Send email with"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}