package com.dnamedical.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dnamedical.Activities.FacultyChatActivity;
import com.dnamedical.Models.get_chat_history.GetChatHistoryResp;
import com.dnamedical.Models.get_faculty_channel.Chat;
import com.dnamedical.R;
import com.dnamedical.utils.Constants;
import com.dnamedical.utils.DnaPrefs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FacultyChannelAdapter extends RecyclerView.Adapter<FacultyChannelAdapter.ViewHolder> {
    private Context applicationContext;
    private GetChatHistoryResp getChatHistoryResp;
    FacultyChannelAdapter.OnCategoryClick onUserClickCallback;
    private ArrayList<com.dnamedical.Models.get_faculty_channel.Chat> messageArrayList;

    public FacultyChannelAdapter(Context applicationContext,ArrayList<Chat> messageArrayList) {
        this.applicationContext = applicationContext;
        this.messageArrayList = messageArrayList;
    }

    @Override
    public FacultyChannelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.facultychannelrow, viewGroup, false);
        return new FacultyChannelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FacultyChannelAdapter.ViewHolder holder, final int position) {
        String userId;
        holder.tvChannel.setText(messageArrayList.get(holder.getAdapterPosition()).getChannelName());

        holder.tvChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent ii = new Intent(v.getContext(), FacultyChatActivity.class);
                v.getContext().startActivity(ii);
            }
        });


    }


    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    

    /**
     * View Holder for CONFIG LIST
     */

    public class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.tvChannel)
        TextView tvChannel;


        View itemView;

        public ViewHolder(View view) {
            super(view);
            itemView = view;
            ButterKnife.bind(this, view);


        }
    }


    public interface OnCategoryClick {
        public void onCateClick(String id);

        void onInstituteClick(String name);
    }

}
