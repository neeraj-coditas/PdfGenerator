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

        language: String,
        database: String,
        vcs: String,
        cloud: String,
        operatingSystem: String,

        project1: String,
        role1: String,
        duration1: String,
        description1: String,
        project2: String,
        role2: String,
        duration2: String,
        description2: String,

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

        canvas.drawText(language, 40F, 210F, myPaint)
        canvas.drawText(database, 40F, 230F, myPaint)
        canvas.drawText(vcs, 40F, 250F, myPaint)
        canvas.drawText(cloud, 40F, 270F, myPaint)
        canvas.drawText(operatingSystem, 40F, 290F, myPaint)

        canvas.drawText(project1, 40F, 310F, myPaint)
        canvas.drawText(role1, 40F, 330F, myPaint)
        canvas.drawText(duration1, 40F, 350F, myPaint)
        canvas.drawText(description1, 40F, 370F, myPaint)
        canvas.drawText(project2, 40F, 390F, myPaint)
        canvas.drawText(role2, 40F, 410F, myPaint)
        canvas.drawText(duration2, 40F, 430F, myPaint)
        canvas.drawText(description2, 40F, 450F, myPaint)

        canvas.drawText(qualification, 40F, 470F, myPaint)
        canvas.drawText(passing, 40F, 490F, myPaint)
        canvas.drawText(institution, 40F, 510F, myPaint)
        canvas.drawText(platform, 40F, 530F, myPaint)
        canvas.drawText(achievement, 40F, 550F, myPaint)

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