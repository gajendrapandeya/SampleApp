package com.codermonkeys.sampleapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.sampleapp.ProductDetailActivity;
import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.models.RewardsModel;

import org.w3c.dom.Text;

import java.util.List;

public class MyRewardsAdapter extends RecyclerView.Adapter<MyRewardsAdapter.ViewHolder> {

    private List<RewardsModel> rewardsModelList;
    private Boolean useMiniLayout = false;

    public MyRewardsAdapter(List<RewardsModel> rewardsModelList, Boolean useMiniLayout) {
        this.rewardsModelList = rewardsModelList;
        this.useMiniLayout = useMiniLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(useMiniLayout) {
           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_rewards_item_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent, false);
        }

       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = rewardsModelList.get(position).getTitle();
        String date = rewardsModelList.get(position).getExpiryDate();
        String body = rewardsModelList.get(position).getCoupenBody();

        holder.setData(title, date, body);
    }

    @Override
    public int getItemCount() {
        return rewardsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView coupenTitle;
        private TextView coupenExpiryDate;
        private TextView coupenBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            coupenTitle = itemView.findViewById(R.id.coupen_title);
            coupenExpiryDate = itemView.findViewById(R.id.coupen_validity);
            coupenBody = itemView.findViewById(R.id.coupen_body);
        }

        private void setData(final String title, final String date, final String body) {

            coupenTitle.setText(title);
            coupenExpiryDate.setText(date);
            coupenBody.setText(body);

            if(useMiniLayout) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ProductDetailActivity.coupenTitle.setText(title);
                        ProductDetailActivity.coupenBody.setText(body);
                        ProductDetailActivity.coupenExpiryDate.setText(date);
                        ProductDetailActivity.showDialogRecyclerView();
                    }
                });
            }
        }
    }
}



