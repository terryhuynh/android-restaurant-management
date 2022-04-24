package com.tdtu.project2.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.tdtu.project2.R;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireActivity(), this, year, month, date);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText txtBirthday = requireActivity().findViewById(R.id.txtBirthday);
        String birthday = year + "-" + (month + 1) + "-" + dayOfMonth;

        txtBirthday.setText(birthday);
    }
}
