package uz.vianet.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var et_cipher:EditText
    lateinit var et_k:EditText
    lateinit var tv_cryptedText:TextView
    lateinit var btn_encrypt:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        et_cipher = findViewById(R.id.et_cipher)
        et_k = findViewById(R.id.et_k)
        tv_cryptedText = findViewById(R.id.tv_cryptedText)
        btn_encrypt = findViewById(R.id.btn_encrypt)
        btn_encrypt.setOnClickListener {
            makeCipher()
        }


    }

    private fun makeCipher() {
        val str = et_cipher.text.toString().trim()
        val x = et_k.text.toString().trim()
        var k = 0
        if (x[0].isDigit()){
            if (x.toInt() > 0 && x.toInt() < 26 ){
                k = x.toInt()
            }else{
                Toast.makeText(this,"1 da 25 gacha raqam kiriting",Toast.LENGTH_LONG).show()
            }

        }else{
            k = 26 - (122 - x[0].lowercaseChar().code)
        }

        Log.d("##","K = ${x[0].lowercaseChar().code}")
        Log.d("##","K = $k")
        tv_cryptedText.setText(encrypt(str,k))
    }


    private fun cipher(c:Char,k:Int):Char{
        val range = 97 ..122
        return if (c.code in range){
            var idx = c.code - range.min() + k
            idx = idx % range.count()
            range.elementAt(idx).toChar()
        }else{
            c
        }
    }

    private fun encrypt(str:String, k:Int):String{
        val result = StringBuilder()
        for (c in str ){
            val range = 97 ..122
            if (c.code in range){
                result.append(cipher(c,k))
            }else{
                result.append(cipher(c.lowercaseChar(),k))
            }

        }
        return result.toString()
    }
}