package k.realmdataentry;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_search)
    ImageView btn_search;

    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.menu_btn)
    ImageView menu_btn;

    @Nullable
    @BindView(R.id.menu_popup)
    LinearLayout menu_popup;


    BufferedReader reader;
    String name, desc;
    Realm realm;
    RecyclerView rv_list;
    RecyclerAdapter adapter;
    private List<ModelClass> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        rv_list = (RecyclerView) findViewById(R.id.rv_items);

        ButterKnife.bind(this);
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();

        if (new File(realm.getConfiguration().getPath()).exists()) {
            if (realm.isEmpty()) {
                ParseInBackground task = new ParseInBackground();
                task.execute();
            }

        }
        if (new File(realm.getConfiguration().getPath()).exists()) {
            if (!realm.isEmpty()) {
                RealmResults<ModelClass> realmResults = realm.where(ModelClass.class).findAllSorted("name");
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                rv_list.setLayoutManager(layoutManager);
                rv_list.setItemAnimator(new DefaultItemAnimator());
                adapter = new RecyclerAdapter(MainActivity.this, realmResults);
                rv_list.setAdapter(adapter);
            }
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setItemAnimator(new DefaultItemAnimator());
    }


    @OnTouch(R.id.et_search)
    boolean TextEntered() {
        et_search.setFocusable(true);
        et_search.setFocusableInTouchMode(true);
        return false;
    }


    @OnClick(R.id.btn_search)
    void OnSearchClicked() {
        et_search.setFocusable(false);
        et_search.setFocusableInTouchMode(false);
        String searchedword = et_search.getText().toString();
        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        RealmResults<ModelClass> results = realm.where(ModelClass.class).contains("name", searchedword, Case.INSENSITIVE).findAll();
        if (results.size() > 0) {
            adapter.setFilteredList(results);
            adapter.notifyDataSetChanged();
        } else
            Toast.makeText(this, "I thu zawn hi a awmlo tlat mai", Toast.LENGTH_SHORT).show();

    }

    public class ParseInBackground extends AsyncTask<Void, Integer, Integer> {
        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {

            this.progressDialog.setMessage("Lo nghak lawk rawh");
            this.progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                realm = Realm.getDefaultInstance();

                try {
                    InputStream file = getAssets().open("watevar.txt");
                    reader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
                    String lineRead = null;

                    while ((lineRead = reader.readLine()) != null) {
                        if (lineRead != null && lineRead.length() > 0) {
                            ModelClass modelClass = new ModelClass();
                            String[] namelist = lineRead.split(":");
                            if (namelist.length >= 2) {
                                name = namelist[0];
                                desc = namelist[1];
                                modelClass.setName(name);
                                modelClass.setDesc(desc);
                                list.add(modelClass);
                            }
                        }
                    }
                    realm.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm bgRealm) {
                                                     bgRealm.insert(list);

                                                 }

                                             }
                    );

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return list.size();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            realm = Realm.getDefaultInstance();
            RealmResults<ModelClass> refresh = realm.where(ModelClass.class).findAllSorted("name");
            adapter = new RecyclerAdapter(MainActivity.this, refresh);
            rv_list.setAdapter(adapter);
        }
    }

    @OnClick(R.id.tv_header)
    void HeaderClicked() {
        realm = Realm.getDefaultInstance();
        RealmResults<ModelClass> refresh = realm.where(ModelClass.class).findAllSorted("name");
        adapter.setFilteredList(refresh);
        adapter.notifyDataSetChanged();
        et_search.setText("");
    }

    @OnClick(R.id.menu_btn)
    void OnMenuClicked() {

        PopupMenu popup = new PopupMenu(MainActivity.this, menu_btn);

        popup.getMenuInflater().inflate(R.menu.menu_layout, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.faq:
                        Intent i = new Intent(MainActivity.this, Tex.class);
                        i.putExtra("id", 0);
                        startActivity(i);
                        return false;

                    case R.id.contact:
                        i = new Intent(MainActivity.this, Tex.class);
                        i.putExtra("id", 1);
                        startActivity(i);
                        return false;

                    case R.id.contribute:
                        i = new Intent(MainActivity.this, Tex.class);
                        i.putExtra("id", 2);
                        startActivity(i);
                        return false;

                    case R.id.complaintbox:
                        i = new Intent(MainActivity.this, Tex.class);
                        i.putExtra("id", 3);
                        startActivity(i);
                        return false;

                    default:
                        i = new Intent(MainActivity.this, Tex.class);
                        startActivity(i);
                        return true;
                }

            }
        });
        popup.show();
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
}
