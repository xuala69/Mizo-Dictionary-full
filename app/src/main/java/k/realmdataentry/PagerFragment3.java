package k.realmdataentry;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dsoloman on 31-03-2017.
 */

public class PagerFragment3 extends Fragment {

    Context mContext;

    Realm realm;

    Button wordAddBTN, CancelAddBTN;
    EditText wordaddET;
    EditText descaddET;

    String wordtemp;
    String desctemp;


    public PagerFragment3() {
    }

    public static PagerFragment3 newInstance(String title, int page) {
        PagerFragment3 pagefrag = new PagerFragment3();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someString", title);
        pagefrag.setArguments(args);
        return pagefrag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(mContext);
        realm = Realm.getDefaultInstance();
        mContext = getContext();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment3, container, false);
        wordaddET = (EditText) v.findViewById(R.id.wordaddET);
        descaddET = (EditText) v.findViewById(R.id.descaddET);
        wordAddBTN = (Button) v.findViewById(R.id.wordAddBTN);
        CancelAddBTN = (Button) v.findViewById(R.id.cancelAddBTN);

        WordAddClicked();
        CancelClicked();
        return v;

    }

     void WordAddClicked() {
        wordAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordtemp = wordaddET.getText().toString();
                desctemp = descaddET.getText().toString();
                if (TextUtils.isEmpty(wordtemp) && (TextUtils.isEmpty(desctemp))) {
                    wordaddET.setError("Thumal ila ziaklut lo");
                    descaddET.setError("A sawifiahna ila ziak lo");
                }
                if (TextUtils.isEmpty(wordtemp)) {
                    wordaddET.setError("Thumal ila ziaklut lo");
                } else if (TextUtils.isEmpty(desctemp)) {
                    descaddET.setError("A sawifiahna ila ziak lo");
                } else if (!TextUtils.isEmpty(wordtemp) && (!TextUtils.isEmpty(desctemp))) {
                    String temp = wordtemp + " ";
                    RealmResults<ModelClass> wordChecker = realm.where(ModelClass.class).equalTo("name", temp, Case.INSENSITIVE).findAll();
                    if (wordChecker.size() > 0) {
                        Toast.makeText(mContext, "I thumal belh tum hi a awm sa tawh", Toast.LENGTH_SHORT).show();
                    } else {
                        realm.beginTransaction();
                        ModelClass modelClass = realm.createObject(ModelClass.class);
                        modelClass.setName(wordtemp);
                        modelClass.setDesc(desctemp);
                        realm.commitTransaction();
                        Toast.makeText(mContext, "I thumal belh chu dahthat ani e", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    void CancelClicked() {
        CancelAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


    }
}