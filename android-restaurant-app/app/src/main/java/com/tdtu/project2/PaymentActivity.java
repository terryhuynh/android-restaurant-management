package com.tdtu.project2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.tdtu.project2.adapters.PaymentDetailAdapter;
import com.tdtu.project2.constants.ResponseCode;
import com.tdtu.project2.constants.ValueConstants;
import com.tdtu.project2.models.Bill;
import com.tdtu.project2.models.PaymentDetail;
import com.tdtu.project2.retrofit.RetrofitClient;
import com.tdtu.project2.retrofit.services.BillService;
import com.tdtu.project2.retrofit.services.PaymentDetailService;
import com.tdtu.project2.retrofit.services.TableFoodService;
import com.tdtu.project2.utils.FormatUtils;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements OnClickListener {

    private GridView grvBillDetail;
    private TextView txtTotalPrice;
    private TextView txtDiscount;
    private TextView txtTotalAmount;
    private EditText txtDiscountPercent;

    private Long tableId;
    private double discountAmount;
    private int discountPercent;
    private double totalPrice;
    private double totalAmount;
    private String billId;
    private PaymentDetailService paymentService;
    private BillService billService;
    private TableFoodService tableService;
    private List<PaymentDetail> listPaymentDetail;
    private PaymentDetailAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment);

        TextView txtTableName = findViewById(R.id.txtTableName);
        grvBillDetail = findViewById(R.id.grvBillDetail);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        txtDiscountPercent = findViewById(R.id.txtDiscountPercent);
        Button btnDoPayment = findViewById(R.id.btnDoPayment);
        Button btnExit = findViewById(R.id.btnExit);

        btnDoPayment.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        paymentService = RetrofitClient.getInstance().create(PaymentDetailService.class);
        billService = RetrofitClient.getInstance().create(BillService.class);
        tableService = RetrofitClient.getInstance().create(TableFoodService.class);
        listPaymentDetail = new ArrayList<>();

        Intent intent = getIntent();
        tableId = intent.getLongExtra(ValueConstants.TABLE_ID, 0);
        String tableName = intent.getStringExtra(ValueConstants.TABLE_NAME);

        txtTableName.setText(tableName);
        discountAmount = 0;
        discountPercent = 0;
        txtDiscount.setText(FormatUtils.formatCurrency(discountAmount));
        txtDiscountPercent.setText(String.valueOf(discountPercent));

        getBillByTableId(tableId);
    }

    public void updateTableStatus(Long tableId, Long status) {
        tableService.updateTableStatus(tableId, status).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == ResponseCode.RESPONSE_CODE_OK) {
                        Toast.makeText(PaymentActivity.this, "Cập nhật bàn thành công!",
                            Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    private void getBillByTableId(Long tableId) {
        billService.getBillByTableIdAndStatus(tableId, 0).enqueue(new Callback<Bill>() {
            @Override
            public void onResponse(Call<Bill> call, Response<Bill> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        billId = response.body().getId();
                        getPaymentDetailByBillId(billId);
                    }
                }
            }

            @Override
            public void onFailure(Call<Bill> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Lỗi khi lấy bill", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshData() {
        grvBillDetail.setAdapter(null);
        txtDiscountPercent.setText(String.valueOf(0));
        txtDiscount.setText(FormatUtils.formatCurrency(0));
        txtTotalPrice.setText(FormatUtils.formatCurrency(0));
        txtTotalAmount.setText(FormatUtils.formatCurrency(0));
    }

    private void doPayment() {
        billService.updateBill(this.billId, this.discountAmount, this.totalAmount, 1).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == ResponseCode.RESPONSE_CODE_OK) {
                        Toast.makeText(PaymentActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        updateTableStatus(tableId, 0L);
                        refreshData();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleChangeDiscountPercentage(double totalPrice) {
        txtDiscountPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                discountPercent = Integer.parseInt(txtDiscountPercent.getText().toString());
                discountAmount = getDiscountAmount(totalPrice, discountPercent);
                totalAmount = getTotalAmount(totalPrice, discountAmount);
                txtDiscount.setText(FormatUtils.formatCurrency(discountAmount));
                txtTotalAmount.setText(FormatUtils.formatCurrency(totalAmount));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void getPaymentDetailByBillId(String billId) {
        paymentService.getPaymentDetailByBillId(billId).enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listPaymentDetail = convertToListPayments(response.body());
                        totalPrice = getTotalPrice(listPaymentDetail);
                        totalAmount = getTotalAmount(totalPrice, discountAmount);
                        txtTotalPrice.setText(FormatUtils.formatCurrency(totalPrice));
                        txtTotalAmount.setText(FormatUtils.formatCurrency(totalAmount));
                        handleChangeDiscountPercentage(totalPrice);

                        adapter = new PaymentDetailAdapter(PaymentActivity.this, R.layout.layout_custom_payment_detail,
                            listPaymentDetail);
                        grvBillDetail.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Lỗi khi lấy danh sách", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double getTotalPrice(List<PaymentDetail> list) {
        double total = 0;

        for (PaymentDetail paymentDetail : list) {
            total += paymentDetail.getTotalPrice();
        }

        return total;
    }

    private double getTotalAmount(double totalPrice, double discountAmount) {
        return totalPrice - discountAmount;
    }

    private double getDiscountAmount(double totalPrice, int percentage) {
        double percent = (percentage * 1.0) / 100;

        return totalPrice * percent;
    }


    private List<PaymentDetail> convertToListPayments(List<Object[]> list) {
        List<PaymentDetail> paymentDetails = new ArrayList<>();

        for (Object[] object : list) {
            PaymentDetail paymentDetail = new PaymentDetail();
            paymentDetail.setFoodName(String.valueOf(object[0]));
            paymentDetail.setQuantity((Double) object[1]);
            paymentDetail.setTotalPrice((Double) object[2]);

            paymentDetails.add(paymentDetail);
        }

        return paymentDetails;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnDoPayment) {
            doPayment();
        }
    }
}
