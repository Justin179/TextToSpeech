package tw.com.nec.justin_chen.texttospeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    int result;
    EditText editText;
    String text;


    public void speak(View view){

        if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){

            Toast.makeText(getApplicationContext(),"Feature not Supported in your Device", Toast.LENGTH_SHORT).show();

        } else {

            text = editText.getText().toString();

            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);

        }
    }

    public void stop(View view){

        if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){

            Toast.makeText(getApplicationContext(),"Feature not Supported in your Device", Toast.LENGTH_SHORT).show();

        } else {

            if(textToSpeech!=null){
                textToSpeech.stop();
            }

        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status==TextToSpeech.SUCCESS){

                    // set the language to the UK accent
                    result = textToSpeech.setLanguage(Locale.ENGLISH);

                } else {

                    Toast.makeText(getApplicationContext(),"Feature not Supported in your Device", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
