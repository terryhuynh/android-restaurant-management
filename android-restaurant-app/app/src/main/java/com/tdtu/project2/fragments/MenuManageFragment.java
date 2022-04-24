package com.tdtu.project2.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.tdtu.project2.HomeActivity;
import com.tdtu.project2.ListEmployeesActivity;
import com.tdtu.project2.R;
import java.util.Objects;

public class MenuManageFragment extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_menu_management, container, false);
        Objects.requireNonNull(
            ((HomeActivity) Objects.requireNonNull(getActivity())).getSupportActionBar())
            .setTitle(R.string.menu_management);

        Button btnMgrEmployee = view.findViewById(R.id.btnMgrEmployees);
        btnMgrEmployee.setOnClickListener(this);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnMgrEmployees) {
            Intent employeeIntent = new Intent(getActivity(), ListEmployeesActivity.class);
            startActivity(employeeIntent);
        }
    }
}
