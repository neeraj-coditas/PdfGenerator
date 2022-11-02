package com.example.pdfgenerator

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object PdfGenerator {
     fun createPDF(context: Context, profession: String, summary: String, background: String, designation: String, experience: String) {
        val myPdfDocument = PdfDocument()
        val myPaint = Paint()

        val mypageInfo1 = PdfDocument.PageInfo.Builder(400, 600, 1).create()
        val mypage1 = myPdfDocument.startPage(mypageInfo1)
        val canvas = mypage1.canvas
        canvas.drawText("Welcome to Page 1", 40F, 50F, myPaint)
        myPdfDocument.finishPage(mypage1)

        val mypageInfo2 = PdfDocument.PageInfo.Builder(400, 600, 2).create()
        val mypage2 = myPdfDocument.startPage(mypageInfo2)
        val canvas2 = mypage2.canvas
        canvas2.drawText("Welcome to Page 2", 40F, 50F, myPaint)
        myPdfDocument.finishPage(mypage2)

        val mypageInfo3 = PdfDocument.PageInfo.Builder(400,600,3).create()
        val mypage3 = myPdfDocument.startPage(mypageInfo3)
        val canvas3 = mypage3.canvas
        canvas3.drawText(profession, 40F, 50F, myPaint)
        canvas3.drawText(summary, 40F, 60F, myPaint)
        canvas3.drawText(background, 40F, 70F, myPaint)
        canvas3.drawText(designation, 40F, 80F, myPaint)
        canvas3.drawText(experience, 40F, 90F, myPaint)


        val file = File(context.getExternalFilesDir("/"), "FirstPDF.pdf")

        try {
            myPdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "PDF file not generated.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
        myPdfDocument.close()
    }
}