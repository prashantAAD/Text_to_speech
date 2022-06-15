package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    lateinit var textToSpeech: TextToSpeech
    lateinit var btn:Button
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button)
        editText = findViewById(R.id.et)
        textToSpeech = TextToSpeech(this, this)
        btn.setOnClickListener {
            textToSpeechFunction()
        }

    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val res:Int=textToSpeech.setLanguage(Locale.US)
            if (res==TextToSpeech.LANG_MISSING_DATA||res==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(applicationContext,"language not supported....",Toast.LENGTH_LONG).show()
            }
            else{
                btn.isEnabled=true
                textToSpeechFunction()
            }


        }
        else{
            Toast.makeText(applicationContext,"failed to initialize",Toast.LENGTH_LONG).show()
        }
    }

    private fun textToSpeechFunction(){
        val strText=editText.text.toString()
        textToSpeech.speak(strText,TextToSpeech.QUEUE_FLUSH,null)
        textToSpeech.setSpeechRate(1.0F)
        Toast.makeText(applicationContext,strText,Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        if (textToSpeech!=null){
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}