package com.tdtu.project2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tdtu.project2.constants.ResponseCode;
import com.tdtu.project2.fragments.DatePickerFragment;
import com.tdtu.project2.models.Employee;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.EmployeeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements OnClickListener,
    OnFocusChangeListener {

    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtBirthday;
    private RadioGroup rdgGender;
    private RadioGroup rdgRole;

    public String gender = null;
    public String role = null;

    private EmployeeService eService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtBirthday = findViewById(R.id.txtBirthday);
        Button btnAddNew = findViewById(R.id.btnAddNew);
        Button btnExit = findViewById(R.id.btnExit);
        rdgGender = findViewById(R.id.rdgGender);
        rdgRole = findViewById(R.id.rdgRole);

        btnAddNew.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        txtBirthday.setOnFocusChangeListener(this);

        eService = RetrofitClient.getInstance().create(EmployeeService.class);
    }

    @SuppressLint("NonConstantResourceId")
    private void addEmployee() {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String birthday = txtBirthday.getText().toString();

        switch (rdgGender.getCheckedRadioButtonId()) {
            case R.id.rdMale:
                gender = "Male";
                break;
            case R.id.rdFemale:
                gender = "Female";
                break;
            default:
                break;
        }

        switch (rdgRole.getCheckedRadioButtonId()) {
            case R.id.rdAdmin:
                role = "Administrator";
                break;
            case R.id.rdEm:
                role = "Employee";
                break;
            default:
                break;
        }

        if (("").equals(username)) {
            Toast.makeText(SignUpActivity.this, "Username trống", Toast.LENGTH_SHORT).show();
        }
        else if (("").equals(password)) {
            Toast.makeText(SignUpActivity.this, "Password trống", Toast.LENGTH_SHORT).show();
        }
        else {
            Employee employee = new Employee();
            employee.setUsername(username);
            employee.setPassword(password);
            employee.setGender(gender);
            employee.setBirthday(birthday);
            employee.setRole(role);
            employee.setStatus(1);

            eService.insertEmployee(employee).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == ResponseCode.RESPONSE_CODE_OK) {
                            Toast.makeText(SignUpActivity.this, "Đăng ký thành công!",
                                Toast.LENGTH_SHORT).show();
                            clearData();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT)
                        .show();
                }
            });
        }
    }

    private void clearData() {
        txtUsername.getText().clear();
        txtPassword.getText().clear();
        txtBirthday.getText().clear();
        rdgGender.clearCheck();
        rdgRole.clearCheck();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnAddNew) {
            addEmployee();
        }
        else if (id == R.id.btnExit) {
            finish();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();

        if (id == R.id.txtBirthday && hasFocus) {
            DatePickerFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "Birthday");
        }
    }
}
