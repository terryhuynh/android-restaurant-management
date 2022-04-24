package com.tdtu.project2.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtils {

    public static String formatCurrency(double amount) {
        Locale vn = new Locale("vi", "VN");
        NumberFormat vnCurrency = NumberFormat.getCurrencyInstance(vn);

        return vnCurrency.format(amount);
    }
}
