package com.curieo;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.curieo.shapes.TextSpeechCore;

import java.util.Locale;

public class TextSpeech implements TextSpeechCore {
    private TextToSpeech tts;
    private Context appContext;

    public TextSpeech(Context appContext){
        this.appContext = appContext;
        tts=new TextToSpeech(appContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                    tts.setSpeechRate((float) 0.75);
                }
            }
        });
    }


    @Override
    public void speak(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
