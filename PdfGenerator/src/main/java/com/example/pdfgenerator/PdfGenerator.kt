package com.example.pdfgenerator

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object PdfGenerator {
    fun createPDF(
        context: Context,
        fullName:String,
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

        experienceList : ArrayList<ExperienceData>,

        /*project1: String,
        role1: String,
        duration1: String,
        description1: String,
        project2: String,
        role2: String,
        duration2: String,
        description2: String,*/

        qualification: String,
        passing: String,
        specialization:String,
        institution: String,
        platform: String,
        achievement: String
    ) {
        val myPdfDocument = PdfDocument()
        val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.ic_coditas_name_logo)
        val scaledBitmap = Bitmap.createScaledBitmap(bmp, 80, 25, false)

        val textPaint = TextPaint()
        textPaint.textSize = 10F

        val header1 = Paint()
        header1.textSize = 20F

        val header2 = Paint()
        header2.textSize = 10F
        header2.style = Paint.Style.FILL_AND_STROKE

        val header3 = Paint()
        header3.textSize = 7F

        val sectionNamePaint = TextPaint()
        sectionNamePaint.textSize = 13F
        sectionNamePaint.color = Color.parseColor("#2C74D6")

        val professionPaint = TextPaint()
        professionPaint.textSize = 13F

        val myPaint = Paint()

        val mypageInfo = PdfDocument.PageInfo.Builder(400, 600, 1).create()
        val mypage = myPdfDocument.startPage(mypageInfo)
        val canvas = mypage.canvas

        canvas.drawBitmap(scaledBitmap, 55F, 70F, myPaint)
        canvas.drawText(fullName, 60F, 140F, header1)
        canvas.drawText(profession, 60F, 160F, professionPaint)
        var mTextLayout =
            StaticLayout(
                "$fullName is $summary with $experience of experience in the industry. $fullName's educational background is $qualification and works as $designation at Coditas.",
                textPaint,
                canvas.width - 100,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                true
            )
        canvas.save()
        var textX = 60F
        var textY = 180F
        canvas.translate(textX, textY)
        mTextLayout.draw(canvas)
        canvas.restore()

        mTextLayout =
            StaticLayout(
                "$fullName has a passion for $primarySkill and a $secondarySkill enthusiast, having gained experience in both $primaryDomain and $secondaryDomain over a span of ${experience}. He/She has pursued $qualification in $specialization and is currently working as $designation",
                textPaint,
                canvas.width - 100,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                true
            )
        canvas.save()
        textX = 60F
        textY = 230F
        canvas.translate(textX, textY)
        mTextLayout.draw(canvas)
        canvas.restore()

        canvas.drawText("Key Skills", 60F, 340F, sectionNamePaint)

        canvas.drawText("Programming Languages/Frameworks", 155F, 330F, header2)
        canvas.drawText(language, 155F, 345.5F, textPaint)

        canvas.drawText("Database", 155F, 370F, header2)
        canvas.drawText(database, 155F, 385.5F, textPaint)

        canvas.drawText("Version Control Systems", 155F, 410F, header2)
        canvas.drawText(vcs, 155F, 425.5F, textPaint)

        canvas.drawText("Cloud Technologies", 155F, 450F, header2)
        canvas.drawText(cloud, 155F, 465.5F, textPaint)

        canvas.drawText("Operating systems", 155F, 490F, header2)
        canvas.drawText(operatingSystem, 155F, 505.5F, textPaint)

        myPdfDocument.finishPage(mypage)

        //PAGE 2

        val mypageInfo2 = PdfDocument.PageInfo.Builder(400, 600, 2).create()
        val mypage2 = myPdfDocument.startPage(mypageInfo2)
        val canvas2 = mypage2.canvas

        canvas2.drawText("Experience", 60F, 100F, sectionNamePaint)

        var x = 155F
        var y = 90F

        for(i in 1..experienceList.size){

            canvas2.drawText(experienceList[i].projectName, 155F, 90F, header2)
            y += 15
            canvas2.drawText("${experienceList[i].role} | ${experienceList[i].duration}", 155F, 105F, header3)

            mTextLayout =
                StaticLayout(
                    experienceList[i].description,
                    textPaint,
                    canvas2.width - 200,
                    Layout.Alignment.ALIGN_NORMAL,
                    1.0f,
                    0.0f,
                    true
                )
            canvas2.save()
            textX = 155F
            textY = 115F
            canvas2.translate(textX, textY)
            mTextLayout.draw(canvas2)
            canvas2.restore()
            mTextLayout.height

            y = 120F + mTextLayout.height + 20F
        }

       /* canvas2.drawText(project1, 155F, 90F, header2)
        canvas2.drawText("$role1 | $duration1", 155F, 105F, header3)

        mTextLayout =
            StaticLayout(
                description1,
                textPaint,
                canvas2.width - 200,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                true
            )
        canvas2.save()
        textX = 155F
        textY = 115F
        canvas2.translate(textX, textY)
        mTextLayout.draw(canvas2)
        canvas2.restore()
        mTextLayout.height

        val height1 = 120F + mTextLayout.height + 20F

        canvas2.drawText(project2, 155F, height1, header2)

        val height2 = height1 + 15F
        canvas2.drawText("$role2 | $duration2", 155F, height2, header3)

        mTextLayout =
            StaticLayout(
                description2,
                textPaint,
                canvas2.width - 200,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                true
            )
        canvas2.save()
        textX = 155F
        textY = height2 + 10F
        canvas2.translate(textX, textY)
        mTextLayout.draw(canvas2)
        canvas2.restore()*/

        val height3 = y + 15F + mTextLayout.height + 30F

        canvas2.drawText("Education", 60F, height3 + 10, sectionNamePaint)
        canvas2.drawText(qualification, 155F, height3, header2)
        canvas2.drawText(
            "$passing | $institution",
            155F,
            height3 + 15.5F,
            textPaint
        )

        val height4 = height3 + 45.5F

        canvas2.drawText("Achievements", 60F, height4 + 10F, sectionNamePaint)
        canvas2.drawText(platform, 155F, height4, header2)
        canvas2.drawText(achievement, 155F, height4 + 15.5F, textPaint)


        myPdfDocument.finishPage(mypage2)


        val file = File(context.getExternalFilesDir("/"), "$profession.pdf")

        try {
            myPdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "PDF file not generated.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
        myPdfDocument.close()
    }

    data class ExperienceData(val projectName: String, val role: String, val duration: String, val description: String)
}