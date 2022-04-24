package com.tdtu.project2.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.tdtu.project2.FoodDetailActivity;
import com.tdtu.project2.R;
import com.tdtu.project2.constants.ValueConstants;
import com.tdtu.project2.models.Food;
import com.tdtu.project2.utils.FormatUtils;
import com.tdtu.project2.utils.ImageUtils;
import com.tdtu.project2.viewholders.FoodViewHolder;
import java.util.List;

public class FoodAdapter extends BaseAdapter implements OnClickListener {

    private final Context context;
    private final int layout;
    private final List<Food> listFoods;
    private FoodViewHolder foodViewHolder;
    private final String billId;
    private final Long tableId;
    private final int billStatus;

    public FoodAdapter(Context context, int layout, List<Food> foods, String billId, Long tableId, int billStatus) {
        this.context = context;
        this.layout = layout;
        this.listFoods = foods;
        this.billId = billId;
        this.tableId = tableId;
        this.billStatus = billStatus;
    }

    @Override
    public int getCount() {
        return listFoods.size();
    }

    @Override
    public Object getItem(int i) {
        return listFoods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listFoods.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (inflater != null) {
                view = inflater.inflate(layout, viewGroup, false);
                foodViewHolder = new FoodViewHolder();
                foodViewHolder.imageFood = view.findViewById(R.id.imgFood);
                foodViewHolder.txtFoodName = view.findViewById(R.id.txtFoodName);
                foodViewHolder.txtFoodPrice = view.findViewById(R.id.txtPrice);
                view.setTag(foodViewHolder);
            }
        }
        else {
            foodViewHolder = (FoodViewHolder) view.getTag();
        }

        Food food = listFoods.get(i);
        Bitmap bitmap = ImageUtils.convertByteToBitMap(food.getImage());
        foodViewHolder.imageFood.setImageBitmap(bitmap);
        foodViewHolder.txtFoodName.setText(food.getFoodName());
        foodViewHolder.txtFoodPrice.setText(FormatUtils.formatCurrency(food.getPriceSmall()));
        foodViewHolder.imageFood.setTag(i);
        foodViewHolder.imageFood.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        foodViewHolder = (FoodViewHolder) ((View) view.getParent()).getTag();

        if (id == R.id.imgFood) {
            int position = (int) view.getTag();
            Food selectedFood = (Food) getItem(position);
            Intent intent = new Intent(context, FoodDetailActivity.class);
            if (this.billId != null) {
                intent.putExtra(ValueConstants.BILL_ID, this.billId);
            }
            intent.putExtra(ValueConstants.TABLE_ID, this.tableId);
            intent.putExtra(ValueConstants.BILL_STATUS, this.billStatus);
            intent.putExtra(ValueConstants.FOOD_ID, selectedFood.getId());
            intent.putExtra(ValueConstants.FOOD_NAME, selectedFood.getFoodName());
            intent.putExtra(ValueConstants.SMALL_PRICE, selectedFood.getPriceSmall());
            intent.putExtra(ValueConstants.MEDIUM_PRICE, selectedFood.getPriceMedium());
            intent.putExtra(ValueConstants.LARGE_PRICE, selectedFood.getPriceLarge());

            context.startActivity(intent);
        }
    }
}
