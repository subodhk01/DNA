package edu.com.medicalapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.com.medicalapp.Models.video.Price;
import edu.com.medicalapp.R;

public class VideoListPriceAdapter extends RecyclerView.Adapter<VideoListPriceAdapter.ViewHolder> {

    private Context applicationContext;
    private List<Price> priceList;
    VideoListPriceAdapter.OnCategoryClick onUserClickCallback;

     public VideoListPriceAdapter(Context applicationContext)
     {
         this.applicationContext=applicationContext;
     }



    @NonNull
    @Override
    public VideoListPriceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_items, viewGroup, false);
        return new VideoListPriceAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final VideoListPriceAdapter.ViewHolder holder, int i) {

        holder.title.setText(priceList.get(holder.getAdapterPosition()).getTitle());
        holder.row_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUserClickCallback != null) {
                    onUserClickCallback.onCateClick(priceList.get(holder.getAdapterPosition()).getUrl());
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        if (priceList != null) {
            return priceList.size();
        } else {
            return 0;
        }

    }


    public void setData(List<Price> CourseLists) {
        this.priceList = CourseLists;
    }

    public void setListener(VideoListPriceAdapter.OnCategoryClick onUserClickCallback) {
        this.onUserClickCallback = onUserClickCallback;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_view)
        LinearLayout row_view;

        @BindView(R.id.vid_title)
        TextView title;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnCategoryClick {
        public void onCateClick(String url);
    }


}
