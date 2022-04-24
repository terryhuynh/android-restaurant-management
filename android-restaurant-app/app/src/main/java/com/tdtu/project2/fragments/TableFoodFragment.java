package com.tdtu.project2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.tdtu.project2.HomeActivity;
import com.tdtu.project2.R;
import com.tdtu.project2.adapters.TableFoodAdapter;
import com.tdtu.project2.models.TableFood;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.TableFoodService;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableFoodFragment extends Fragment {

    private static final int REQUEST_CODE = 111;
    private static final String RESULT = "result";

    private GridView gridView;
    private List<TableFood> tables;
    private TableFoodAdapter tableAdapter;
    private TableFoodService tblService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tablefood, container, false);
        Objects.requireNonNull(
            ((HomeActivity) Objects.requireNonNull(getActivity())).getSupportActionBar())
            .setTitle(R.string.list_tables);

        gridView = view.findViewById(R.id.grvTables);

        tblService = RetrofitClient.getInstance().create(TableFoodService.class);

        return view;
    }

    private void setTableFoodAdapter() {
        tblService.getAllTables().enqueue(new Callback<List<TableFood>>() {
            @Override
            public void onResponse(Call<List<TableFood>> call, Response<List<TableFood>> response) {
                if (response.body() != null) {
                    tables = response.body();
                    tableAdapter = new TableFoodAdapter(getActivity(), R.layout.layout_custom_table, tables);
                    gridView.setAdapter(tableAdapter);
                    tableAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<TableFood>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi khi lấy danh sách", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setTableFoodAdapter();
    }
}
