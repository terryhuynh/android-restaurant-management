package com.tdtu.project2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.tdtu.project2.models.Employee;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.EmployeeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String EMPLOYEE_NAME = "username";
    private static final String EMPLOYEE_ROLE = "role";

    private EditText txtUsername;
    private EditText txtPassword;
    private EmployeeService eService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        Button btnLogin = findViewById(R.id.btnLogin);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        eService = RetrofitClient.getInstance().create(EmployeeService.class);

        btnLogin.setOnClickListener(view -> login());
    }

    private void login() {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        eService.loginEmployee(username, password).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Employee employee = response.body();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra(EMPLOYEE_NAME, employee.getUsername());
                        intent.putExtra(EMPLOYEE_ROLE, employee.getRole());
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}