package com.tdtu.project2.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.tdtu.project2.R;
import com.tdtu.project2.models.Employee;
import com.tdtu.project2.viewholders.EmployeeViewHolder;
import java.util.List;

public class ListEmployeesAdapter extends BaseAdapter implements OnClickListener {

    private final Context context;
    private final int layout;
    private final List<Employee> employees;
    private EmployeeViewHolder viewHolder;

    public ListEmployeesAdapter(Context context, int layout,
        List<Employee> employees) {
        this.context = context;
        this.layout = layout;
        this.employees = employees;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (inflater != null) {
                view = inflater.inflate(layout, viewGroup, false);
                viewHolder = new EmployeeViewHolder();
                viewHolder.txtItemUsername = view.findViewById(R.id.txtItemUsername);
                viewHolder.txtItemRole = view.findViewById(R.id.txtItemRole);
                view.setTag(viewHolder);
            }
        }
        else {
            viewHolder = (EmployeeViewHolder) view.getTag();
        }

        Employee employee = employees.get(i);
        viewHolder.txtItemUsername.setText(employee.getUsername());
        viewHolder.txtItemRole.setText(employee.getRole());

        return view;
    }
}
