package com.tdtu.project2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tdtu.project2.adapters.ListEmployeesAdapter;
import com.tdtu.project2.models.Employee;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.EmployeeService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployeesActivity extends AppCompatActivity {

    private ListView lsvEmployees;
    private List<Employee> employees;
    private ListEmployeesAdapter employeesAdapter;
    private EmployeeService eService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_manage_accounts);

        lsvEmployees = findViewById(R.id.lstEmployees);
        FloatingActionButton fabInsertEmployee = findViewById(R.id.fabInsertEmployee);
        eService = RetrofitClient.getInstance().create(EmployeeService.class);

        setListEmployeeAdapter();

        fabInsertEmployee.setOnClickListener(view -> {
            Intent intent = new Intent(ListEmployeesActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void setListEmployeeAdapter() {
        eService.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        employees = response.body();
                        employeesAdapter = new ListEmployeesAdapter(ListEmployeesActivity.this,
                            R.layout.layout_item_account, employees);
                        lsvEmployees.setAdapter(employeesAdapter);
                        employeesAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(ListEmployeesActivity.this, "Lỗi khi lấy danh sách", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
