package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model.ProdDetailAttributeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailAtributteRecViewAdapter  extends
        RecyclerView.Adapter<ProductDetailAtributteRecViewAdapter.ViewHolder> {

    private final Context mContext;
    private final List<ProdDetailAttributeModel> mValues;

    public ProductDetailAtributteRecViewAdapter(Context mContext,
                                                List<ProdDetailAttributeModel> items) {
        this.mContext = mContext;
        this.mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product_detail_attribute_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ProdDetailAttributeModel mItem = mValues.get(position);
        if(position % 2 == 0){
            holder.mView.setBackgroundColor(mContext.getResources().getColor(R.color.colorOneTable));
        }else{
            holder.mView.setBackgroundColor(mContext.getResources().getColor(R.color.colorTwoTable));
        }
//        set up attribute name
        holder.name.setText(mItem.getName());
//        set up attribute name value
        holder.nameValue.setText(mItem.getValueName());
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItems(List<ProdDetailAttributeModel> productAttributes) {
        if (getItemCount() > 0)
            mValues.clear();
        mValues.addAll(productAttributes);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        @BindView(R.id.attribute_name)
        TextView name;
        @BindView(R.id.attribute_name_value)
        TextView nameValue;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "" + "'";
        }
    }
}
