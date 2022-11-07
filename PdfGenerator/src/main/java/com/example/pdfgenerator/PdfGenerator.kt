package com.example.pdfgenerator

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object PdfGenerator {
    fun createPDF(
        context: Context,

        profession: String,
        summary: String,
        designation: String,
        experience: String,

        primarySkill: String,
        secondarySkill: String,
        primaryDomain: String,
        secondaryDomain: String,

        language: String
        database: String,
        vcs: String,
        cloud: String,
        operatingSystem: String,

        project1: String,
        role1: String,
        duration1: String,
        Description1: String,
        project2: String,
        role2: String,
        duration2: String,
        Description2: String,

        qualification: String,
        passing: String,
        institution: String,
        platform: String,
        achievement: String
    ) {
        val myPdfDocument = PdfDocument()
        val myPaint = Paint()

        val mypageInfo = PdfDocument.PageInfo.Builder(400, 600, 3).create()
        val mypage = myPdfDocument.startPage(mypageInfo)
        val canvas = mypage.canvas
        canvas.drawText(profession, 40F, 50F, myPaint)
        canvas.drawText(summary, 40F, 70F, myPaint)
        canvas.drawText(designation, 40F, 90F, myPaint)
        canvas.drawText(experience, 40F, 110F, myPaint)
        canvas.drawText(primarySkill, 40F, 130F, myPaint)
        canvas.drawText(secondarySkill, 40F, 150F, myPaint)
        canvas.drawText(primaryDomain, 40F, 170F, myPaint)
        canvas.drawText(secondaryDomain, 40F, 190F, myPaint)
        myPdfDocument.finishPage(mypage)

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