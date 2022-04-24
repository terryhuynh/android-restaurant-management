package com.tdtu.project2.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.tdtu.project2.HomeActivity;
import com.tdtu.project2.PaymentActivity;
import com.tdtu.project2.R;
import com.tdtu.project2.constants.ValueConstants;
import com.tdtu.project2.fragments.MenuFoodFragment;
import com.tdtu.project2.models.Bill;
import com.tdtu.project2.models.TableFood;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.BillService;
import com.tdtu.project2.viewholders.TableFoodViewHolder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableFoodAdapter extends BaseAdapter implements OnClickListener {

    private final Context context;
    private final int layout;
    private final List<TableFood> tables;
    private TableFoodViewHolder tableFoodViewHolder;
    private final BillService billService;
    private final FragmentManager fragmentManager;

    public TableFoodAdapter(Context context, int layout, List<TableFood> tables) {
        this.context = context;
        this.layout = layout;
        this.tables = tables;
        billService = RetrofitClient.getInstance().create(BillService.class);
        fragmentManager = ((HomeActivity) context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return tables.size();
    }

    @Override
    public Object getItem(int i) {
        return tables.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private void getBillByTableId(Long tableId, FragmentTransaction menusTran, MenuFoodFragment fragment) {
        billService.getBillByTableIdAndStatus(tableId, 0).enqueue(new Callback<Bill>() {
            @Override
            public void onResponse(Call<Bill> call, Response<Bill> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(context, "Lấy bill thành công", Toast.LENGTH_SHORT).show();
                        Bundle args = new Bundle();
                        args.putLong(ValueConstants.TABLE_ID, tableId);
                        args.putString(ValueConstants.BILL_ID, response.body().getId());
                        args.putInt(ValueConstants.BILL_STATUS, response.body().getBillStatus());
                        fragment.setArguments(args);
                        menusTran.replace(R.id.content, fragment).addToBackStack("tableFood");
                        menusTran.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<Bill> call, Throwable t) {
                Toast.makeText(context, "Lỗi khi lấy bill", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (inflater != null) {
                view = inflater.inflate(layout, viewGroup, false);
                tableFoodViewHolder = new TableFoodViewHolder();

                tableFoodViewHolder.imgTable = view.findViewById(R.id.imgTable);
                tableFoodViewHolder.imgOrder = view.findViewById(R.id.imgOrder);
                tableFoodViewHolder.imgPayment = view.findViewById(R.id.imgPayment);
                tableFoodViewHolder.imgHide = view.findViewById(R.id.imgHide);
                tableFoodViewHolder.txtTableName = view.findViewById(R.id.txtTableName);

                view.setTag(tableFoodViewHolder);
            }
        }
        else {
            tableFoodViewHolder = (TableFoodViewHolder) view.getTag();
        }

        if (tables.get(position).isSelected()) {
            showButtons();
        }
        else {
            hideButtons();
        }

        TableFood table = tables.get(position);
        tableFoodViewHolder.txtTableName.setText(table.getTableName());
        Long tableStatus = tables.get(position).getStatus();
        if (tableStatus == 1) {
            tableFoodViewHolder.imgTable.setImageResource(R.drawable.table_served);
        }
        else {
            tableFoodViewHolder.imgTable.setImageResource(R.drawable.table_empty);
        }
        tableFoodViewHolder.imgTable.setTag(position);

        tableFoodViewHolder.imgTable.setOnClickListener(this);
        tableFoodViewHolder.imgOrder.setOnClickListener(this);
        tableFoodViewHolder.imgPayment.setOnClickListener(this);

        return view;
    }

    private void showButtons() {
        tableFoodViewHolder.imgOrder.setVisibility(View.VISIBLE);
        tableFoodViewHolder.imgPayment.setVisibility(View.VISIBLE);
        tableFoodViewHolder.imgHide.setVisibility(View.VISIBLE);
    }

    private void hideButtons() {
        tableFoodViewHolder.imgOrder.setVisibility(View.INVISIBLE);
        tableFoodViewHolder.imgPayment.setVisibility(View.INVISIBLE);
        tableFoodViewHolder.imgHide.setVisibility(View.INVISIBLE);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        tableFoodViewHolder = (TableFoodViewHolder) ((View) view.getParent()).getTag();
        int tablePosition = (int) tableFoodViewHolder.imgTable.getTag();
        Long tableId = tables.get(tablePosition).getId();

        switch (id) {
            case R.id.imgTable:
                int position = (int) view.getTag();
                tables.get(position).setSelected(true);
                showButtons();
                break;
            case R.id.imgOrder:
                Long tableStatus = tables.get(tablePosition).getStatus();
                FragmentTransaction menusTran = fragmentManager.beginTransaction();
                MenuFoodFragment menuFragment = new MenuFoodFragment();

                if (tableStatus == 0) {
                    Bundle args = new Bundle();
                    args.putLong(ValueConstants.TABLE_ID, tableId);
                    menuFragment.setArguments(args);
                    menusTran.replace(R.id.content, menuFragment).addToBackStack("tableFood");
                    menusTran.commit();
                }
                else {
                    getBillByTableId(tableId, menusTran, menuFragment);
                }
                break;
            case R.id.imgPayment:
                Intent paymentIntent = new Intent(context, PaymentActivity.class);
                paymentIntent.putExtra(ValueConstants.TABLE_ID, tableId);
                paymentIntent.putExtra(ValueConstants.TABLE_NAME, tables.get(tablePosition).getTableName());
                context.startActivity(paymentIntent);
                break;
            default:
                break;
        }
    }
}
