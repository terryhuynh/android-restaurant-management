package com.tdtu.project2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.tdtu.project2.R;
import com.tdtu.project2.adapters.FoodAdapter;
import com.tdtu.project2.constants.ValueConstants;
import com.tdtu.project2.models.Food;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.FoodService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFoodFragment extends Fragment {

    private ListView lstFood;
    private FoodService foodService;
    private List<Food> listOfFood;
    private FoodAdapter adapter;
    private String billId;
    private Long tableId;
    private int billStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_list_food, container, false);

        lstFood = view.findViewById(R.id.lstFoods);
        foodService = RetrofitClient.getInstance().create(FoodService.class);
        listOfFood = new ArrayList<>();

        Bundle arguments = getArguments();
        Long idType = arguments != null ? arguments.getLong(ValueConstants.FOOD_TYPE_ID) : 0;
        billId = arguments != null ? arguments.getString(ValueConstants.BILL_ID) : null;
        tableId = arguments != null ? arguments.getLong(ValueConstants.TABLE_ID) : null;
        billStatus = arguments != null ? arguments.getInt(ValueConstants.BILL_STATUS) : null;

        setListFoodAdapter(idType);

        return view;
    }

    private void setListFoodAdapter(Long idType) {
        foodService.getListFoodById(idType).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listOfFood = response.body();
                        adapter = new FoodAdapter(getActivity(), R.layout.layout_custom_item_food, listOfFood,
                            billId, tableId, billStatus);
                        lstFood.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi khi lấy danh sách món ăn", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
