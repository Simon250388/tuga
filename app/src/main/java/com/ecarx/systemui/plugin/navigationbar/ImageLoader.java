package com.ecarx.systemui.plugin.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ecarx.systemui.plugin.App;

/* loaded from: classes.dex */
public class ImageLoader {
    private static final String TAG = ImageLoader.class.getSimpleName();
    private static volatile ImageLoader mImageLoader;
    private Context mContext;
    private SimpleTarget<Bitmap> mSimpleTarget = new SimpleTarget<Bitmap>() { // from class: com.ecarx.systemui.plugin.navigationbar.ImageLoader.1
//        @Override // com.bumptech.glide.request.target.Target
//        public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
//            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
//        }

        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
            Log.w("ImageLoader", "[JRHvac][onResourceReady], resBitmap: " + bitmap + ", resWidth: " + bitmap.getWidth() + ", resHeight: " + bitmap.getHeight());
        }
    };

    private ImageLoader(Context context) {
        this.mContext = context;
    }

    public static ImageLoader getInstance() {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = new ImageLoader(App.getApp().getApplicationContext());
                }
            }
        }
        return mImageLoader;
    }

    public void asyncImageLoader(Context context, String str) {
        Glide.with(context).asBitmap().load(str).into(this.mSimpleTarget);
    }
}
