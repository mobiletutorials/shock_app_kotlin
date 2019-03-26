package com.example.countershockkotlin

import java.io.Serializable

class AudioModel: Serializable {

    var id:Int
    var audioFilename:String = ""
    var descriptionMessage:String
    var isAsset:Boolean = false
    var isTTS:Boolean

    constructor(id: Int, audioFilename: String, descriptionMessage: String, isAsset: Boolean) {
        this.id = id
        this.audioFilename = audioFilename
        this.descriptionMessage = descriptionMessage
        this.isAsset = isAsset
        this.isTTS = false
    }

    constructor(id:Int, descriptionMessage: String){
        this.id = id;
        this.descriptionMessage = descriptionMessage
        this.isTTS = true;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioModel

        if (id != other.id) return false
        if (audioFilename != other.audioFilename) return false
        if (descriptionMessage != other.descriptionMessage) return false
        if (isAsset != other.isAsset) return false
        if (isTTS != other.isTTS) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + audioFilename.hashCode()
        result = 31 * result + descriptionMessage.hashCode()
        result = 31 * result + isAsset.hashCode()
        result = 31 * result + isTTS.hashCode()
        return result
    }


}