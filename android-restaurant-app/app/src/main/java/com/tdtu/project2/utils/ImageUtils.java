package com.tdtu.project2.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class ImageUtils {

    public static Bitmap convertByteToBitMap(String imageBaseCode) {
        byte[] byteArray = Base64.decode(imageBaseCode, 0);
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
