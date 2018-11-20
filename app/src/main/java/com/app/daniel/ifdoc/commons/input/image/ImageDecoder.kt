package com.app.daniel.ifdoc.commons.input.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory


class ImageDecoder {

    fun decodeSampleBitmapFromUri(path: String, reqWidth: Int, reqHeight: Int): Bitmap? {
        var bitmap: Bitmap?
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        bitmap = BitmapFactory.decodeFile(path, options)
        return bitmap
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            inSampleSize = if (width > height) {
                Math.round(height.toFloat() / reqHeight.toFloat())
            } else {
                Math.round(width.toFloat() / reqWidth.toFloat())
            }
        }
        return inSampleSize
    }
}