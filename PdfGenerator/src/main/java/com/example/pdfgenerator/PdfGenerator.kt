package com.example.pdfgenerator

import android.content.Context
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object PdfGenerator {

    data class ProjectData(val projectName: String, val role: String, val duration: String, val description: String)
    data class SkillsData(val skillTitle: String, val skillDescription: String)

    fun createPDF(
        context: Context,
        fullName:String,
        profession: String,
        summary: String,
        designation: String,
        experience: String,

        skillString: String,
        experienceString: String,

        qualification: String,
        passing: String,
        institution: String,
        achievement: String
    ) {
        var experienceList = ArrayList<ProjectData>()
        val experienceType = object : TypeToken<ArrayList<ProjectData>>() {}.type
        experienceList = Gson().fromJson(experienceString,experienceType)

        var skillsList = ArrayList<SkillsData>()
        val skillType = object : TypeToken<ArrayList<SkillsData>>() {}.type
        skillsList = Gson().fromJson(skillString,skillType)

        Log.d("checklist",skillsList.toString())
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
        sectionNamePaint.style = Paint.Style.FILL_AND_STROKE

        val professionPaint = TextPaint()
        professionPaint.textSize = 13F

        val hintPaint = TextPaint()
        hintPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
        hintPaint.textSize = 10F

        val myPaint = Paint()

        val mypageInfo = PdfDocument.PageInfo.Builder(400, 600, 1).create()
        val mypage = myPdfDocument.startPage(mypageInfo)
        val canvas = mypage.canvas

        canvas.drawBitmap(scaledBitmap, 55F, 70F, myPaint)
        canvas.drawText(fullName, 60F, 140F, header1)
        canvas.drawText(profession, 60F, 160F, professionPaint)
        var mTextLayout =
            StaticLayout(
                "$summary. $fullName's educational background is $qualification and works as $designation at Coditas.",
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
        mTextLayout.height

        var pageOneHeight = mTextLayout.height + 180F
        var pageOneHeightTwo = pageOneHeight + 50F

        canvas.drawText("Key Skills", 60F, pageOneHeightTwo+10F, sectionNamePaint)

        for(i in skillsList.indices){
            canvas.drawText(skillsList[i].skillTitle, 155F, pageOneHeightTwo,header2)
            canvas.drawText(skillsList[i].skillDescription,155F,15.5F + pageOneHeightTwo,textPaint)

            pageOneHeightTwo += 40F
        }



/*        canvas.drawText("Programming Languages/Frameworks", 155F, pageOneHeightTwo, header2)
        canvas.drawText(language, 155F, 15.5F + pageOneHeightTwo, textPaint)

        canvas.drawText("Database", 155F, pageOneHeightTwo+40F, header2)
        canvas.drawText(database, 155F, 55.5F+ pageOneHeightTwo, textPaint)

        canvas.drawText("Version Control Systems", 155F, pageOneHeightTwo+80F, header2)
        canvas.drawText(vcs, 155F, 95.5F + pageOneHeightTwo, textPaint)

        canvas.drawText("Cloud Technologies", 155F, pageOneHeightTwo+120F, header2)
        canvas.drawText(cloud, 155F, 135.5F + pageOneHeightTwo, textPaint)

        canvas.drawText("Operating systems", 155F, pageOneHeightTwo+160F, header2)
        canvas.drawText(operatingSystem, 155F, 175.5F+ pageOneHeightTwo, textPaint)*/
        myPdfDocument.finishPage(mypage)

        //PAGE 2

        val mypageInfo2 = PdfDocument.PageInfo.Builder(400, 600, 2).create()
        val mypage2 = myPdfDocument.startPage(mypageInfo2)
        val canvas2 = mypage2.canvas

        canvas2.drawText("Experience", 60F, 95F, sectionNamePaint)
        canvas2.drawText("Briefing of projects worked on to showcase experience", 155F, 90F, hintPaint)

        var x = 155F
        var y = 90F

        for(i in experienceList.indices){

            canvas2.drawText(experienceList[i].projectName, 155F, y+15F, header2)
            canvas2.drawText("${experienceList[i].role} | ${experienceList[i].duration}", 155F, y + 27F, header3)

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
            textY = y + 35F
            canvas2.translate(textX, textY)
            mTextLayout.draw(canvas2)
            canvas2.restore()
            mTextLayout.height

            y = 120F + mTextLayout.height + 20F
        }

        val height3 = y + 15F + mTextLayout.height + 30F

        canvas2.drawText("Education", 60F, height3 + 10, sectionNamePaint)
        canvas2.drawText("Details of last educational qualification", 155F, height3 + 5F, hintPaint)
        canvas2.drawText(qualification, 155F, height3 + 20F, header2)
        canvas2.drawText(
            "$passing | $institution",
            155F,
            height3 + 35F,
            textPaint
        )

        val height4 = height3 + 70F

        canvas2.drawText("Achievements", 60F, height4 + 5F, sectionNamePaint)
        canvas2.drawText("A list of achievements that are relevant to the industry",158F, height4, hintPaint)
        canvas2.drawText("Platform Name/ Certifier Name", 158F, height4 + 20F, header2)
        val achievementUpdated = achievement.replace("-","\n -",true)
        //canvas2.drawText(achievementUpdated, 155F, height4 + 15.5F, textPaint)

        mTextLayout =
            StaticLayout(
               achievementUpdated,
                textPaint,
                canvas2.width - 200,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                true
            )
        canvas2.save()
        textX = 155F
        textY = height4 + 20F
        canvas2.translate(textX, textY)
        mTextLayout.draw(canvas2)
        canvas2.restore()
        mTextLayout.height
        myPdfDocument.finishPage(mypage2)


        //Generating PDF
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
}