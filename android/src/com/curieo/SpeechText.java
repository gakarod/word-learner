package com.curieo;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.curieo.shapes.SpeechTextCore;
import com.curieo.shapes.come;

import static android.os.Binder.getCallingUid;


public class SpeechText extends Activity implements SpeechTextCore {
    public static final int REQUEST_OK = 1;

    SpeechRecognizer speechRecognizer;
    Handler uiThread;
    Context appContext;
    come gdx;

    public SpeechText(Context appContext) {
        uiThread = new Handler();
        this.appContext = appContext;
    }

    @Override
    public void showToast(final CharSequence toastMessage, final int toastDuration) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, toastMessage, toastDuration).show();
            }
        });
    }

    @Override
    public void checkRecord() {

        uiThread.post(new Runnable() {
            @Override
            public void run() {
                String permission = "android.permission.RECORD_AUDIO";
                boolean perm = (PackageManager.PERMISSION_GRANTED == appContext.getPackageManager().checkPermission(permission, appContext.getPackageManager().getNameForUid(getCallingUid())));
                if(!perm){
                    showMessageOKCancel();
                }
            }
        });


    }

    @Override
    public void promptSpeechInput() {
        uiThread.post(new Runnable() {
            @Override
            public void run() {



                    speechRecognizer = SpeechRecognizer.createSpeechRecognizer(appContext);
                    speechRecognizer.setRecognitionListener(new SpeechListener(gdx, appContext));

                    Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                    i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
                    speechRecognizer.startListening(i);


            }
        });
    }

     public void showMessageOKCancel() {

        new AlertDialog.Builder(appContext)
                .setMessage("Please Grant permision to use this feature")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", appContext.getPackageName(), null);
                            intent.setData(uri);
                            appContext.startActivity(intent);

                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
    public void setGdx(come gdx) {
        this.gdx = gdx;
    }

}
