package com.example.countershockkotlin

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageStorer(var context:Context) {
    val preferences:SharedPreferences
    val editor:SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences(ShockUtils.SHOCK_SHARED_PREFS, Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    fun storeImages(images:List<ImageModel>){
        val key = context.getString(R.string.key_stored_images)
        val gson = Gson()
        editor.putString(key, gson.toJson(images))
        editor.commit()
    }

    fun addImage(image:ImageModel){
        val images = getStoredImages() as ArrayList<ImageModel>
        images.add(image)
        storeImages(images)
    }

    private fun getStoredImages():List<ImageModel>{
        val imagesAsJson = preferences.getString(context.getString(R.string.key_stored_images), null)
        if(imagesAsJson == null || imagesAsJson.length == 0){
            return ArrayList()
        }

        val gson = Gson()
        val type = object : TypeToken<List<ImageModel>>(){}.type
        return gson.fromJson(imagesAsJson, type)
    }

    fun getAllImages():List<ImageModel>{
        val assetImages = ArrayList<ImageModel>()
        assetImages.add(ImageModel(0, "bust_2", true))
        assetImages.add(ImageModel(1, "lama", true))
        assetImages.add(ImageModel(2, "man_1", true))

        assetImages.addAll(getStoredImages())
        return assetImages
    }

    fun getSelectedImage():ImageModel{
        val images = getAllImages()

        val defaultImage = images.get(0)

        val imageId = preferences.getInt(context.getString(R.string.key_photo_id), 0)
        for(image in images){
            if(image.id == imageId){
                return image
            }
        }

        // Fall back on defaults
        editor.putInt(context.getString(R.string.key_photo_id), 0)
        editor.commit()

        return defaultImage
    }

}