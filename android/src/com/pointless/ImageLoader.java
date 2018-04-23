package com.pointless;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.support.annotation.Nullable;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

public class ImageLoader implements ImageLoaderCore{
    public Bitmap bitmap;
    public Texture texture;
    private Context appContext;

    public static OnImageLoaded onImageLoaded;

    public ImageLoader(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public void loadImage(String url){

        try {
            bitmap = Glide.with(appContext)
                    .asBitmap()
                    .load(url)
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            CommonObjects.speechToText.showToast("Failed", 3000);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            if (onImageLoaded != null) {
                                onImageLoaded.setTexture();
                            }
                            return false;
                        }
                    })
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void loadImage(String url, int width, int height) {
        try {
            bitmap = Glide.with(appContext)
                    .asBitmap()
                    .load(url)
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            if (onImageLoaded != null) {
                                onImageLoaded.setTexture();
                            }
                            return false;
                        }
                    })
                    .into(width, height)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Texture getImage() {
        if(bitmap!=null){
            texture = new Texture(bitmap.getWidth(), bitmap.getHeight(), Pixmap.Format.RGBA8888);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.getTextureObjectHandle());
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        }
        return texture;
    }

    @Override
    public void setOnImageLoadedListener(OnImageLoaded listener) {
        onImageLoaded = listener;
    }
}
