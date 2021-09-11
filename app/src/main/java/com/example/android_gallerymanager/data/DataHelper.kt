package com.example.android_gallerymanager.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import com.example.android_gallerymanager.model.Album

class DataHelper {
    companion object {
        fun findAlbums(contentResolver: ContentResolver): List<Album> {

            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projections = arrayOf(
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.BUCKET_ID,
                    MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.DATA
            )

            val orderBy = "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"

            val findAlbums = HashMap<String, Album>()

            contentResolver.query(contentUri, projections, null, null, orderBy)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val bucketIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_ID)
                    val bucketNameIndex =
                            cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)
                    val imageUriIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)

                    do {
                        val bucketId = cursor.getString(bucketIdIndex)

                        val album = findAlbums[bucketId] ?: let {
                            val bucketName = cursor.getString(bucketNameIndex)
                            val lastImageUri = Uri.parse(cursor.getString(imageUriIndex))
                            val album = Album(
                                    bucketId,
                                    bucketName,
                                    0,
                                    lastImageUri
                            )
                            findAlbums[bucketId] = album

                            album
                        }

                        album.count++;

                    } while (cursor.moveToNext())
                }
            }
            return findAlbums.values.toList()
        }
    }
}