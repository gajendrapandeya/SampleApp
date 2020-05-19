package com.codermonkeys.sampleapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.sampleapp.R;
import com.codermonkeys.sampleapp.models.WishListModel;

import java.util.List;

public class WishListAdapter  extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private List<WishListModel> wishListModelList;

    public WishListAdapter(List<WishListModel> wishListModelList) {
        this.wishListModelList = wishListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resource = wishListModelList.get(position).getProductImage();
        String title = wishListModelList.get(position).getProductTitle();
        int freeCoupens = wishListModelList.get(position).getFreeCoupens();
        String rating = wishListModelList.get(position).getRating();
        int totalRatings = wishListModelList.get(position).getTotalRatings();
        String productPrice = wishListModelList.get(position).getProductPrice();
        String cuttedPrice = wishListModelList.get(position).getCuttedPrice();
        String payMethod = wishListModelList.get(position).getPaymentMethod();

        holder.setData(resource, title, freeCoupens, rating, totalRatings, productPrice, cuttedPrice, payMethod);


    }

    @Override
    public int getItemCount() {
        return wishListModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupens;
        private ImageView coupenIcon;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private TextView rating;
        private TextView totalRating;
        private View priceCut;
        private ImageButton deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupens = itemView.findViewById(R.id.free_coupens);
            coupenIcon = itemView.findViewById(R.id.coupen_icon);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            paymentMethod = itemView.findViewById(R.id.payment_method);
            rating = itemView.findViewById(R.id.tv_product_rating_miniview);
            totalRating = itemView.findViewById(R.id.total_ratings);
            priceCut = itemView.findViewById(R.id.price_cut);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
            productPrice = itemView.findViewById(R.id.product_price);
        }

        @SuppressLint("SetTextI18n")
        private void setData(int resource, String title, int freeCoupensNo, String averageRate, int totalRatingNo, String price, String cuttedPriceValue, String payMethod) {

            productImage.setImageResource(resource);
            productTitle.setText(title);

            if(freeCoupensNo != 0) {
                coupenIcon.setVisibility(View.VISIBLE);
                if(freeCoupensNo == 1) {
                    freeCoupens.setText("free " + freeCoupensNo + " coupen");
                } else {
                    freeCoupens.setText("free " + freeCoupensNo + " coupens");
                }
            } else {
                coupenIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);
            }
            rating.setText(averageRate);
            totalRating.setText(totalRatingNo + "(ratings)");
            productPrice.setText(price);
            cuttedPrice.setText(cuttedPriceValue);
            paymentMethod.setText(payMethod);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
