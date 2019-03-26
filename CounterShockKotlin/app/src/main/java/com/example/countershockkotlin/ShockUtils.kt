package com.example.countershockkotlin

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import java.io.File

class ShockUtils {

    companion object {
        val SHOCK_SHARED_PREFS = "shock_shared_prefs"

        val STARTING_ID = 1000

        val MEDIA_UPDATED_ACTION = "MEDIA_UPDATED_ACTION"

        fun getRawUri(context:Context, assetName:String):Uri{
            return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator +
                    File.separator + context.packageName + "/raw/" + assetName)
        }

        fun getDrawableUri(context: Context, assetName: String): Uri{
            return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator +
                    File.separator + context.packageName + "/drawable/" + assetName)
        }

    }

}