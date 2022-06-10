package ru.app.pr12;

import static ru.app.pr12.Util.decodeSampledBitmapFromResource;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LruCache;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MainActivity";
    private LruCache<String, Bitmap> memoryCache;
    private final Object mDiskCacheLock = new Object();

    //Загрузка картинки асинхронно
    public void loadBitmap(int resId, ImageView imageView) {
        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
        task.execute(resId);
    }

    public void printInfoOf(String name, int drawableIdRes) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), drawableIdRes, options);
        int height = options.outHeight;
        int width = options.outWidth;
        String outMimeType = options.outMimeType;
        Log.i(LOG_TAG, name + " -> " + height + "x" + width + ", type=" + outMimeType);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printInfoOf("kot1", R.drawable.kot1);
        printInfoOf("kot2", R.drawable.kot2);
        printInfoOf("kot3", R.drawable.kot3);

        ImageView kot1 = new ImageView(this);
        kot1.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.kot1, 100, 100));

        ImageView kot2 = new ImageView(this);
        kot2.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.kot2, 100, 100));

        ImageView kot3 = new ImageView(this);
        kot3.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.kot3, 100, 100));

        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        int cacheSize = maxMemory / 8;

        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };

        ViewPager viewPager = new ViewPager(this);
        viewPager.addView(kot1);
        viewPager.addView(kot2);
        viewPager.addView(kot3);

        GridView gridView = new GridView(this);
        gridView.addView(kot1);
        gridView.addView(kot2);
        gridView.addView(kot3);

    }

    private int mCacheRefCount = 0;
    private int mDisplayRefCount = 0;
    private boolean mHasBeenDisplayed = false;

    // Notify the drawable that the displayed state has changed.
// Keep a count to determine when the drawable is no longer displayed.
    public void setIsDisplayed(boolean isDisplayed) {
        synchronized (this) {
            if (isDisplayed) {
                mDisplayRefCount++;
                mHasBeenDisplayed = true;
            } else {
                mDisplayRefCount--;
            }
        }
        // Check to see if recycle() can be called.
        checkState();
    }

    // Notify the drawable that the cache state has changed.
// Keep a count to determine when the drawable is no longer being cached.
    public void setIsCached(boolean isCached) {
        synchronized (this) {
            if (isCached) {
                mCacheRefCount++;
            } else {
                mCacheRefCount--;
            }
        }
        // Check to see if recycle() can be called.
        checkState();
    }

    private synchronized void checkState() {
        // If the drawable cache and display ref counts = 0, and this drawable
        // has been displayed, then recycle.
        if (mCacheRefCount <= 0 && mDisplayRefCount <= 0 && mHasBeenDisplayed
                && hasValidBitmap()) {
            getBitmap().recycle();
        }
        
        
    }

    private synchronized boolean hasValidBitmap() {
        Bitmap bitmap = getBitmap();
        return bitmap != null && !bitmap.isRecycled();
    }

    private Bitmap getBitmap() {
        return BitmapFactory.decodeFile("");
    }

    Set<SoftReference<Bitmap>> reusableBitmaps;
    private LruCache<String, BitmapDrawable> memoryCache1;

}
