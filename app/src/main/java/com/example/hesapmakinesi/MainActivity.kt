package com.example.hesapmakinesi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var button0:Button
    private lateinit var button1:Button
    private lateinit var button2:Button
    private lateinit var button3:Button
    private lateinit var button4:Button
    private lateinit var button5:Button
    private lateinit var button6:Button
    private lateinit var button7:Button
    private lateinit var button8:Button
    private lateinit var button9:Button
    private lateinit var sonuc:TextView
    private lateinit var buttonCarp:Button
    private lateinit var buttonBol:Button
    private lateinit var buttonTopla:Button
    private lateinit var buttonCikart:Button
    private lateinit var buttonHesapla:Button
    private lateinit var buttonSil : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(findViewById<Button>(R.id.sifir)) { button0 = this }
        with(findViewById<Button>(R.id.bir)) { button1 = this }
        with(findViewById<Button>(R.id.iki)) { button2 = this }
        with(findViewById<Button>(R.id.uc)) { button3 = this }
        with(findViewById<Button>(R.id.dort)) { button4 = this }
        with(findViewById<Button>(R.id.bes)) { button5 = this }
        with(findViewById<Button>(R.id.alti)) { button6 = this }
        with(findViewById<Button>(R.id.yedi)) { button7 = this }
        with(findViewById<Button>(R.id.sekiz)) { button8 = this }
        with(findViewById<Button>(R.id.dokuz)) { button9 = this }
        with(findViewById<TextView>(R.id.sonuc)) {sonuc = this}
        with(findViewById<Button>(R.id.carpma)) { buttonCarp = this }
        with(findViewById<Button>(R.id.bolme)) { buttonBol = this }
        with(findViewById<Button>(R.id.toplama)) { buttonTopla= this }
        with(findViewById<Button>(R.id.cikartma)) { buttonCikart= this }
        with(findViewById<Button>(R.id.hesapla)) { buttonHesapla= this }
        with(findViewById<Button>(R.id.btnSil)) {buttonSil=this}

        button0.setOnClickListener { sonuc.append("0") }
        button1.setOnClickListener { sonuc.append("1") }
        button2.setOnClickListener { sonuc.append("2") }
        button3.setOnClickListener { sonuc.append("3") }
        button4.setOnClickListener { sonuc.append("4") }
        button5.setOnClickListener { sonuc.append("5") }
        button6.setOnClickListener { sonuc.append("6") }
        button7.setOnClickListener { sonuc.append("7") }
        button8.setOnClickListener { sonuc.append("8") }
        button9.setOnClickListener { sonuc.append("9") }
        buttonCarp.setOnClickListener { sonuc.append("*") }
        buttonBol.setOnClickListener { sonuc.append("/") }
        buttonTopla.setOnClickListener { sonuc.append("+") }
        buttonCikart.setOnClickListener { sonuc.append("-") }
        buttonHesapla.setOnClickListener { hesaplamaIslemi() }
        buttonSil.setOnClickListener{sil()}

    }
    fun hesaplamaIslemi(){
        var denklem = sonuc.text.toString()
        var i = 0
        var j = 1
        var left = 0
        var right = 0
        var temp = ""
        var denklemLeft = ""
        var denklemRight = ""
        var operatorSayisi = operatorHesapla()
        var operatorSayisi2 = operatorHesapla2()
        if (denklem.contains("*") || denklem.contains("/")) {
            while (operatorSayisi != 0) { // 6+84-35*2+5/7
                if (denklem[i] == '*' || denklem[i] == '/') {
                    // Sol tarafı bul
                    operatorSayisi--
                    j = 1
                    while (i - j >= 0 && (denklem[i - j].isDigit() || denklem[i - j] == '.')) {
                        temp = denklem[i - j] + temp
                        j++
                    }
                    left = temp.toIntOrNull() ?: 0
                    denklemLeft = denklem.substring(0, i - temp.length)
                    temp = ""

                    // Sağ tarafı bul
                    j = 1
                    while (i + j < denklem.length && (denklem[i + j].isDigit() || denklem[i + j] == '.')) {
                        temp += denklem[i + j]
                        j++
                    }
                    right = temp.toIntOrNull() ?: 0
                    denklemRight = denklem.substring(i + temp.length + 1)
                    temp = ""

                    // İşlemi yap ve denklemi güncelle
                    if (denklem[i] == '*') {
                        denklem = denklemLeft + (left * right).toString() + denklemRight
                    } else {
                        if(right != 0) {
                            denklem = denklemLeft + (left / right).toString() + denklemRight
                        }else{
                            Toast.makeText(this, "Sayı 0'a Bölünemez", Toast.LENGTH_SHORT).show()
                        }
                    }
                    i=0
                }

                i++
            }
        }else {
            if (denklem.contains("+") || denklem.contains("-")) {
                while (operatorSayisi2 != 0) {
                    if (denklem[i] == '+' || denklem[i] == '-') {
                        // Sol tarafı bul
                        operatorSayisi2--
                        j = 1
                        while (i - j >= 0 && (denklem[i - j].isDigit() || denklem[i - j] == '.')) {
                            temp = denklem[i - j] + temp
                            j++
                        }
                        left = temp.toIntOrNull() ?: 0
                        denklemLeft = denklem.substring(0, i - temp.length)
                        temp = ""

                        // Sağ tarafı bul
                        j = 1
                        while (i + j < denklem.length && (denklem[i + j].isDigit() || denklem[i + j] == '.')) {
                            temp += denklem[i + j]
                            j++
                        }
                        right = temp.toIntOrNull() ?: 0
                        denklemRight = denklem.substring(i + temp.length + 1)
                        temp = ""

                        // İşlemi yap ve denklemi güncelle
                        if (denklem[i] == '+') {
                            denklem = denklemLeft + (left + right).toString() + denklemRight
                        } else {
                            denklem = denklemLeft + (left - right).toString() + denklemRight
                        }
                        i = 0
                    }
                }
            }
        }
        sonuc.text = denklem
    }
    fun operatorHesapla(): Int{
        var operators = setOf('*','/')
        var count = 0
        for(char in sonuc.text){
            if(char in operators){
                count++
            }
        }
        return count
    }
    fun operatorHesapla2(): Int{
        var operators = setOf('+','-')
        var count = 0
        for(char in sonuc.text){
            if(char in operators){
                count++
            }
        }
        return count
    }
    fun sil(){
        if(sonuc.text.isNotEmpty()) {
            sonuc.text = sonuc.text.subSequence(0, sonuc.text.length - 1)
        }
    }


}