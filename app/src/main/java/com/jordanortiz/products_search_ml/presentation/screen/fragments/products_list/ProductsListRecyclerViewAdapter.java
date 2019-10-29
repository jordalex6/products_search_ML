package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jordanortiz.products_search_ml.R;
import com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}
 */
public class ProductsListRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductsListRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private ProductsListAdapterListener mListener;

    public ProductsListRecyclerViewAdapter(List<DummyItem> items, ProductsListAdapterListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected alliance in callback
                    mListener.onProductSelected(mValues.get(getAdapterPosition()));
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public interface ProductsListAdapterListener {
        void onProductSelected(DummyItem dummy);
    }
}
