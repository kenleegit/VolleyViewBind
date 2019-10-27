package net.agnusvox.volleyviewbind

import com.google.gson.annotations.SerializedName

class LiveInfo {
    @SerializedName("schedulerTime")
    var schedulerTime: String? = null

    @SerializedName("currentShow")
    var currentShow: Array<Show>? = null
}

class Show {
    @SerializedName("start_timestamp")
    var start_timestamp: String? = null
    @SerializedName("end_timestamp")
    var end_timestamp: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("image_path")
    var image_path: String? = null
}