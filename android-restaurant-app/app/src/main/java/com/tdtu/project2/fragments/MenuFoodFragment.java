package com.tdtu.project2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tdtu.project2.HomeActivity;
import com.tdtu.project2.R;
import com.tdtu.project2.adapters.SectionsPagerAdapter;
import com.tdtu.project2.constants.ValueConstants;
import com.tdtu.project2.models.FoodType;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.FoodTypeService;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFoodFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private SectionsPagerAdapter sectionAdapter;
    private FoodTypeService typeService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tab_order_food, container, false);
        Objects.requireNonNull(((HomeActivity) Objects.requireNonNull(getActivity())).getSupportActionBar())
            .setTitle(R.string.menu_manage_food);

        tabLayout = view.findViewById(R.id.tabs);
        viewPager2 = view.findViewById(R.id.viewpager);

        typeService = RetrofitClient.getInstance().create(FoodTypeService.class);

        Bundle args = getArguments();
        String billId = args != null ? args.getString(ValueConstants.BILL_ID) : null;
        Long tableId = args != null ? args.getLong(ValueConstants.TABLE_ID) : null;
        int billStatus = args != null ? args.getInt(ValueConstants.BILL_STATUS) : null;
        getListFoodTypes(billId, tableId, billStatus);

        return view;
    }

    private void getListFoodTypes(String billId, Long tableId, int billStatus) {
        typeService.getAllFoodTypes().enqueue(new Callback<List<FoodType>>() {
            @Override
            public void onResponse(Call<List<FoodType>> call, Response<List<FoodType>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        sectionAdapter = new SectionsPagerAdapter(getActivity(), response.body(), billId, tableId,
                            billStatus);
                        viewPager2.setAdapter(sectionAdapter);
                        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                            tab.setText(response.body().get(position).getName())).attach();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FoodType>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi khi lấy danh sách", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
