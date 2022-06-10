package ru.app.pr12;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewWeakReference;
    private final ImageView imageView;
    private int data = 0;

    public BitmapWorkerTask(ImageView imageView) {
        this.imageViewWeakReference = new WeakReference<>(imageView);
        this.imageView = imageView;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(imageViewWeakReference != null & bitmap != null){
            final ImageView imageView = imageViewWeakReference.get();
            if (imageView == null)
                imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected Bitmap doInBackground(Integer... integers) {
        data = integers[0];
        Resources resources = imageView.getContext().getResources();
        return Util.decodeSampledBitmapFromResource(resources, data, 150, 150);
    }
}
