package ca.sudbury.hojat.retrofitdemo

import com.google.gson.annotations.SerializedName

/**
 * If you want, you can use differetn names for field parameters than the names of serialized objects (but it will just make everything more complex).
 */
data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)