package com.example.pdfgeneratorlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pdfgenerator.FormEditorScreen1Data
import com.example.pdfgenerator.PdfGenerator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val obj1 = FormEditorScreen1Data("coder","haha","associate","0")
        PdfGenerator.createPDF(this, obj1)*/
    }
}