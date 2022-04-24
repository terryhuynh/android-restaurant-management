package com.tdtu.project2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tdtu.project2.R;
import com.tdtu.project2.models.PaymentDetail;
import com.tdtu.project2.utils.FormatUtils;
import com.tdtu.project2.viewholders.PaymentDetailViewHolder;

import java.util.List;

public class PaymentDetailAdapter extends BaseAdapter {

    private final Context context;
    private final int layout;
    private final List<PaymentDetail> payments;
    private PaymentDetailViewHolder viewHolder;

    public PaymentDetailAdapter(Context context, int layout, List<PaymentDetail> payments) {
        this.context = context;
        this.layout = layout;
        this.payments = payments;
    }

    @Override
    public int getCount() {
        return this.payments.size();
    }

    @Override
    public Object getItem(int position) {
        return this.payments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (inflater != null) {
                convertView = inflater.inflate(layout, parent, false);
                viewHolder = new PaymentDetailViewHolder();
                viewHolder.txtDetailFoodName = convertView.findViewById(R.id.txtDetailFoodName);
                viewHolder.txtDetailQuantity = convertView.findViewById(R.id.txtDetailQuantity);
                viewHolder.txtDetailPrice = convertView.findViewById(R.id.txtDetailPrice);

                convertView.setTag(viewHolder);
            }
        }
        else {
            viewHolder = (PaymentDetailViewHolder) convertView.getTag();
        }

        PaymentDetail paymentDetail = payments.get(position);
        viewHolder.txtDetailFoodName.setText(paymentDetail.getFoodName());
        viewHolder.txtDetailQuantity.setText(String.valueOf(paymentDetail.getQuantity()));
        viewHolder.txtDetailPrice.setText(FormatUtils.formatCurrency(paymentDetail.getTotalPrice()));

        return convertView;
    }
}
