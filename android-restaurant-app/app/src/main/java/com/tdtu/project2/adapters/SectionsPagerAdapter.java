package com.tdtu.project2.adapters;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.tdtu.project2.constants.ValueConstants;
import com.tdtu.project2.fragments.ListFoodFragment;
import com.tdtu.project2.models.FoodType;
import java.util.List;

public class SectionsPagerAdapter extends FragmentStateAdapter {

    private final List<FoodType> foodTypes;
    private final String billId;
    private final Long tableId;
    private final int billStatus;

    public SectionsPagerAdapter(FragmentActivity fragmentActivity, List<FoodType> foodTypes, String billId,
        Long tableId, int billStatus) {
        super(fragmentActivity);
        this.foodTypes = foodTypes;
        this.billId = billId;
        this.tableId = tableId;
        this.billStatus = billStatus;
    }

    @Override
    public Fragment createFragment(int position) {
        Long idType = this.foodTypes.get(position).getId();
        Fragment fragment = new ListFoodFragment();

        Bundle arguments = new Bundle();
        arguments.putLong(ValueConstants.FOOD_TYPE_ID, idType);
        arguments.putLong(ValueConstants.TABLE_ID, this.tableId);
        arguments.putInt(ValueConstants.BILL_STATUS, this.billStatus);
        if (this.billId != null) {
            arguments.putString(ValueConstants.BILL_ID, this.billId);
        }
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return this.foodTypes.size();
    }
}
