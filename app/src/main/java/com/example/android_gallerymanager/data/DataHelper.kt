package com.example.android_gallerymanager.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.android_gallerymanager.model.Album
import com.example.android_gallerymanager.model.Image
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlin.collections.plus
import kotlin.collections.set
import kotlin.collections.toList

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

        fun findImagesInAlbum(contentResolver: ContentResolver, albumId: String): List<Image> {

            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projections = arrayOf(
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.BUCKET_ID,
                    MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.DATA
            )

            val selection = "${MediaStore.Images.ImageColumns.BUCKET_ID} == ?"
            val selectionArgs = arrayOf(
                    albumId
            )

            val findImageInAlbum = HashMap<String, Image>()

            contentResolver.query(contentUri, projections, selection, selectionArgs, "${MediaStore.Images.ImageColumns.DATE_TAKEN} ASC")?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val bucketIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
                    val bucketNameIndex =
                            cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)
                    val imageUriIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)

                    do {
                        val bucketId = cursor.getString(bucketIdIndex)

                        val image = findImageInAlbum[bucketId] ?: let {
                            val bucketName = cursor.getString(bucketNameIndex)
                            val link = Uri.parse(cursor.getString(imageUriIndex))
                            val image = Image(
                                    bucketId,
                                    bucketName,
                                    link,
                                    0
                            )

                            findImageInAlbum[bucketId] = image

                            image
                        }
                    } while (cursor.moveToNext())
                }
            }
            return findImageInAlbum.values.toList()
        }

        fun findAllImages(contentResolver: ContentResolver): List<Image> {

            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projections = arrayOf(
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.BUCKET_ID,
                    MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.DATA,
                    MediaStore.Images.ImageColumns.DATE_TAKEN,
            )

            val findImageInAlbum = HashMap<String, Image>()

            contentResolver.query(contentUri, projections, null, null, "${MediaStore.Images.ImageColumns.DATE_TAKEN} ASC")?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val bucketIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
                    val bucketNameIndex =
                            cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)
                    val imageUriIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)
                    val imageDate = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN)

                    do {
                        val bucketId = cursor.getString(bucketIdIndex)

                        val image = findImageInAlbum[bucketId] ?: let {
                            val time = cursor.getLong(imageDate)

                            val bucketName = cursor.getString(bucketNameIndex)
                            val link = Uri.parse(cursor.getString(imageUriIndex))
                            val image = Image(
                                    bucketId,
                                    bucketName,
                                    link,
                                    time,
                            )

                            findImageInAlbum[bucketId] = image

                            image
                        }
                    } while (cursor.moveToNext())
                }
            }
            return findImageInAlbum.values.toList()
        }
    }
}