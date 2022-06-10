package ru.app.pr12;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Util {
    private static final String TAG = "lol";

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Изначальные ширина и высота картинки
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Рассчитать соотношение высоты и ширины к требуемой высоте и ширине
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Выберите наименьшее соотношение в качестве значения inSampleSize, это гарантирует
            // конечное изображение, оба размера которого больше или равны
            // запрошенная высота и ширина.
            inSampleSize = Math.min(heightRatio, widthRatio);
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // Первое декодирование с inJustDecodeBounds=true для проверки размеров
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // расчет inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Декодировать растровое изображение с установленным inSampleSize
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampledBitmapFromFile(String filename, float reqWidth, float reqHeight) {
        Log.v(TAG, "Recieved " + filename + " with (w,h): (" + reqWidth + ", " + reqHeight + ").");
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, (int)reqWidth, (int)reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap decodedBitmap = BitmapFactory.decodeFile(filename, options);
        Log.v(TAG, "The Bitmap is " + decodedBitmap.toString());
        return decodedBitmap;
    }

}
