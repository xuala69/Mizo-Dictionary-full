package k.realmdataentry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dsoloman on 23-03-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {



    private Context mCOntext;
    private List<ModelClass> mList;
    private List<ModelClass> filteredList;


    public RecyclerAdapter(MainActivity context, List<ModelClass> list) {
        mList = list;
        mCOntext=context;
    }


    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
        ModelClass obj = mList.get(position);
        holder.tv_word.setText("" + obj.getName());
        holder.tv_desc.setText("" + obj.getDesc());


    }

    @Override
    public int getItemCount() {
        if (mList.size()!=0)
            return mList.size();
        else
            return 0;
    }

    public void setFilteredList(List<ModelClass> filteredList) {
        this.mList = filteredList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_word, tv_desc;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_word = (TextView) itemView.findViewById(R.id.tv_word);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
