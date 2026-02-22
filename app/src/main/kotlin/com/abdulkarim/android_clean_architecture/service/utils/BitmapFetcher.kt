package com.abdulkarim.android_clean_architecture.service.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

/**
 * [BitmapFetcher] is a lightweight utility class for asynchronously downloading
 * and decoding bitmap images from remote URLs, typically used in push notifications
 * or in-app image previews.
 *
 * Responsibilities:
 * - Executes HTTP GET requests using [OkHttpClient] to retrieve image data.
 * - Efficiently decodes the image stream into an Android [Bitmap] object.
 * - Handles failures gracefully using Kotlin's `runCatching` and returns `null` on error.
 */

class BitmapFetcher @Inject constructor() {
    private val client = OkHttpClient()

    suspend fun fetch(url: String): Bitmap? = withContext(Dispatchers.IO) {
        runCatching {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                response.body.byteStream().use(BitmapFactory::decodeStream)
            }
        }.getOrNull()
    }
}
