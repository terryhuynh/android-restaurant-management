package com.tdtu.project2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.tdtu.project2.constants.ResponseCode;
import com.tdtu.project2.constants.ValueConstants;
import com.tdtu.project2.models.Bill;
import com.tdtu.project2.models.BillDetail;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.BillDetailService;
import com.tdtu.project2.retrofit.services.BillService;
import com.tdtu.project2.retrofit.services.TableFoodService;
import com.tdtu.project2.utils.FormatUtils;
import com.tdtu.project2.utils.TimeUtils;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailActivity extends AppCompatActivity implements OnClickListener, OnCheckedChangeListener {

    private TextView txtDetailFoodPrice;
    private RadioGroup rdSize;
    private TextView txtQuantity;

    private double smallPrice;
    private double mediumPrice;
    private double largePrice;
    private String billId;
    private Long tableId;
    private int quantity;
    private Long foodId;
    private double price;

    private BillService billService;
    private BillDetailService billDetailService;
    private TableFoodService tableService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_food_detail);

        txtDetailFoodPrice = findViewById(R.id.txtDetailFoodPrice);
        TextView txtDetailFoodName = findViewById(R.id.txtDetailFoodName);
        rdSize = findViewById(R.id.rdSize);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnPlus = findViewById(R.id.btnPlus);
        txtQuantity = findViewById(R.id.txtQuantity);
        Button btnAdd = findViewById(R.id.btnAddNew);
        Button btnExit = findViewById(R.id.btnExit);

        billService = RetrofitClient.getInstance().create(BillService.class);
        billDetailService = RetrofitClient.getInstance().create(BillDetailService.class);
        tableService = RetrofitClient.getInstance().create(TableFoodService.class);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra(ValueConstants.FOOD_NAME);
        smallPrice = intent.getDoubleExtra(ValueConstants.SMALL_PRICE, 0);
        mediumPrice = intent.getDoubleExtra(ValueConstants.MEDIUM_PRICE, 0);
        largePrice = intent.getDoubleExtra(ValueConstants.LARGE_PRICE, 0);
        billId = intent.getStringExtra(ValueConstants.BILL_ID);
        tableId = intent.getLongExtra(ValueConstants.TABLE_ID, 0);
        foodId = intent.getLongExtra(ValueConstants.FOOD_ID, 0);
        int billStatus = intent.getIntExtra(ValueConstants.BILL_STATUS, 0);

        txtDetailFoodName.setText(foodName);
        txtDetailFoodPrice.setText(FormatUtils.formatCurrency(smallPrice));
        txtQuantity.setText(String.valueOf(0));

        // set event listener for view
        btnAdd.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        rdSize.setOnCheckedChangeListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        this.quantity = Integer.parseInt(txtQuantity.getText().toString());

        switch (id) {
            case R.id.btnMinus:
                this.quantity = this.quantity - 1;
                txtQuantity.setText(String.valueOf(this.quantity));
                break;
            case R.id.btnPlus:
                this.quantity = this.quantity + 1;
                txtQuantity.setText(String.valueOf(this.quantity));
                break;
            case R.id.btnAddNew:
                doHandleBillDetail();
                break;
            default:
                break;
        }
    }

    private void doHandleBillDetail() {
        if (this.billId == null) {
            this.billId = createBillId();
            createNewBill();
            updateTableStatus(tableId, 1L);

            BillDetail billDetail = createBillDetailObject(this.billId, this.foodId, this.quantity, this.price);
            createBillDetail(billDetail);
        }
        else {
            //getBillDetailIdByBill(this.billId);
            BillDetail billDetail = createBillDetailObject(this.billId, this.foodId, this.quantity, this.price);
            createBillDetail(billDetail);
        }

    }

    private String createBillId() {
        Date currentDate = new Date();
        return "bill-" + currentDate.getTime();
    }

    private String createBillDetailId() {
        Date currentDate = new Date();
        return "bill-detail" + currentDate.getTime();
    }

    private BillDetail createBillDetailObject(String billId, Long foodId, int quantity, double price) {
        double total = this.quantity * this.price;
        BillDetail billDetail = new BillDetail();
        billDetail.setId(createBillDetailId());
        billDetail.setBillId(billId);
        billDetail.setFoodId(foodId);
        billDetail.setQuantity(quantity);
        billDetail.setPrice(price);
        billDetail.setTotal(total);

        return billDetail;
    }

    private Bill createBillObject(Long tableId) {
        Bill bill = new Bill();
        bill.setId(this.billId);
        bill.setBillDate(TimeUtils.getCurrentDate());
        bill.setBillTime(TimeUtils.getCurrentTime());
        bill.setTableId(tableId);
        bill.setBillDiscount(0);
        bill.setBillTotalAmount(0);
        bill.setBillStatus(0);

        return bill;
    }

    public void createNewBill() {
        Bill bill = createBillObject(this.tableId);
        billService.createNewBill(bill).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == ResponseCode.RESPONSE_CODE_OK) {
                        Toast.makeText(FoodDetailActivity.this, "Thêm bill thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(FoodDetailActivity.this, "Lỗi khi tạo bill mới", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createBillDetail(BillDetail billDetail) {
        billDetailService.createBillDetail(billDetail).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == ResponseCode.RESPONSE_CODE_OK) {
                        Toast.makeText(FoodDetailActivity.this, "Thêm chi tiết hóa đơn thành công!", Toast.LENGTH_SHORT)
                            .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(FoodDetailActivity.this, "Lỗi khi tạo chi tiết hóa đơn", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateTableStatus(Long tableId, Long status) {
        tableService.updateTableStatus(tableId, status).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == ResponseCode.RESPONSE_CODE_OK) {
                        Toast.makeText(FoodDetailActivity.this, "Cập nhật bàn thành công!",
                            Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void getBillDetailIdByBill(String billId) {
        Long foodId = this.foodId;
        int quantity = this.quantity;
        double price = this.price;

        billDetailService.getBillDetailIdByBill(billId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String billDetailId = response.body();
                    updateBillDetail(foodId, quantity, price, billDetailId);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void updateBillDetail(Long foodId, int quantity, double price, String billDetailId) {
        billDetailService.updateBillDetail(foodId, quantity, price, billDetailId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == ResponseCode.RESPONSE_CODE_OK) {
                        Toast.makeText(FoodDetailActivity.this, "Cập nhật chi tiết hóa đơn thành công!",
                            Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (rdSize.getCheckedRadioButtonId()) {
            case R.id.rdSmall:
                this.price = smallPrice;
                txtDetailFoodPrice.setText(FormatUtils.formatCurrency(smallPrice));
                break;
            case R.id.rdMedium:
                this.price = mediumPrice;
                txtDetailFoodPrice.setText(FormatUtils.formatCurrency(mediumPrice));
                break;
            case R.id.rdLarge:
                this.price = largePrice;
                txtDetailFoodPrice.setText(FormatUtils.formatCurrency(largePrice));
                break;
        }
    }
}
