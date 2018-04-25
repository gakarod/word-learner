package com.curieo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.curieo.shapes.come;

public class AndroidLauncher extends AndroidApplication {
	private static final int PERMISSION_REQUEST_CODE = 1;
	TextSpeech textToSpeech;
	SpeechText speechToText;
	ImageLoader imageLoader;

	AndroidApplicationConfiguration config;
	come here ;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textToSpeech = new TextSpeech(this);
		speechToText = new SpeechText(this);
		imageLoader = new ImageLoader(this);
		config = new AndroidApplicationConfiguration();
		RetroHandler urlHandler = new RetroHandler();
requestPermission();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		here = new come(textToSpeech,speechToText,imageLoader,urlHandler);
		speechToText.setGdx(here);
		initialize(here, config);
	}
	private void requestPermission() {

		if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
				Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
		}
		}else {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO} , PERMISSION_REQUEST_CODE);
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case PERMISSION_REQUEST_CODE:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Log.e("value", "Permission Granted, Now you can use local drive .");
				} else {
					Log.e("value", "Permission Denied, You cannot use local drive .");
				}
				break;
		}
	}
	@Override
	public void onBackPressed() {

	}
}
