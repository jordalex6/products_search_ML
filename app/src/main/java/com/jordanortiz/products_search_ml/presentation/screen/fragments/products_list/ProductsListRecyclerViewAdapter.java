package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model.ProductModel}
 */
public class ProductsListRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductsListRecyclerViewAdapter.ViewHolder> {

    private final List<ProductModel> mValues;
    private ProductsListAdapterListener mListener;
    private Context mContext;

    public ProductsListRecyclerViewAdapter(List<ProductModel> items, Context context) {
        mValues = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ProductModel mItem = mValues.get(position);

//        setup product title
        holder.mItemTitle.setText(mItem.getTitle());
//        setup product price
        holder.mItemPrice.setText(String.format("$ %s", mItem.getPrice()));
//        setup product installments
        if(mItem.getProductInstallments() != null){
            if(mItem.getProductInstallments().getRate() == 0){
                holder.mItemInstallments.setText(String.format(
                        "Hasta %s cuotas sin interés",
                        mItem.getProductInstallments().getQuantity())
                );
            }else{
                holder.mItemInstallments.setVisibility(View.GONE);
            }
        }
//        setup product shipping
        if(!mItem.isShipping())
            holder.mItemShipping.setVisibility(View.GONE);
//        setup product thumbnail
        if(mItem.getThumbnailImg() != null){
            Glide.with(mContext)
                    .load(mItem.getThumbnailImg())
                    .placeholder(R.drawable.loading)
                    .into(holder.mThumbnailItem);
        }


    }

    public void setCallbackListener(ProductsListAdapterListener listener){
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItems(List<ProductModel> productList) {
        if (getItemCount() > 0)
            mValues.clear();

        mValues.addAll(productList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;

        @BindView(R.id.item_thumbnail)
        ImageView mThumbnailItem;
        @BindView(R.id.item_title)
        TextView mItemTitle;
        @BindView(R.id.item_price)
        TextView mItemPrice;
        @BindView(R.id.item_installments)
        TextView mItemInstallments;
        @BindView(R.id.item_shipping)
        TextView mItemShipping;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected product in callback
                    mListener.onProductSelected(mValues.get(getAdapterPosition()));
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "" + "'";
        }
    }

    public interface ProductsListAdapterListener {
        void onProductSelected(ProductModel product);
    }
}
