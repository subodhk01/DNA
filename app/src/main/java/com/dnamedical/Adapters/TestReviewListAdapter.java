package com.dnamedical.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dnamedical.Models.testReviewlistnew.Data;
import com.dnamedical.R;

import java.util.List;

public class TestReviewListAdapter extends RecyclerView.Adapter<TestReviewListAdapter.MyViewHolder> {


    private Context context;
    private TestClickListener testClickListener;
    public void setData(Data data) {
        this.datalist = data;
    }

    private Data datalist;


    public TestReviewListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tesreview_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (datalist.getQuestionList().get(position).getCategoryId() != null) {
            holder.txtTestName.setText(""+(position+1)+". " + datalist.getQuestionList().get(position).getTitle());
        }

        if (datalist.getQuestionList().get(position).getCategoryName() != null) {
            holder.txtTestCategoryName.setText("" + datalist.getQuestionList().get(position).getCategoryName());
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(testClickListener!=null)
                {
                    testClickListener.onTestClicklist(position);
                    Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        if (datalist != null && datalist.getQuestionList().size() > 0) {
            return datalist.getQuestionList().size();
        } else {
            return 0;
        }
    }

    public void setTestClickListener(TestClickListener testClickListener) {
        this.testClickListener = testClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTestName, txtTestCategoryName;
        private ImageView imgBookmark;
        private CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            txtTestName = view.findViewById(R.id.txt_test_question);
            txtTestCategoryName = view.findViewById(R.id.txt_test_categoryname);
            imgBookmark = view.findViewById(R.id.imgbookmark);
            cardView=view.findViewById(R.id.cardviewlist);

        }
    }
    public interface TestClickListener {
        public void onTestClicklist(int postion);

    }


}