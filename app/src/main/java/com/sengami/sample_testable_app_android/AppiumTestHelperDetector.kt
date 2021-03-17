package com.sengami.sample_testable_app_android

import android.content.Context
import android.content.Intent
import android.net.Uri

class AppiumTestHelperDetector(private val context: Context) {

    companion object {
        const val CONTENT_PROVIDER_PACKAGE_NAME = "com.sengami.appium_test_helper_android"
        const val CONTENT_PROVIDER_URI = "content://com.sengami.appium_test_helper_android.provider/"
        const val KEY_TEST_MODE_ENABLED = "KEY_TEST_MODE_ENABLED"
    }

    fun isTestModeEnabled(): Boolean {
        val uri = Uri.parse(CONTENT_PROVIDER_URI)

        context.grantUriPermission(
            CONTENT_PROVIDER_PACKAGE_NAME,
            uri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )

        context
            .applicationContext
            .contentResolver
            .query(uri, null, null, null, null)
            .use { cursor ->
                if (cursor == null) {
                    return false
                }
                cursor.moveToFirst()
                val testModeEnabledIndex: Int = cursor.getColumnIndex(KEY_TEST_MODE_ENABLED)
                return cursor.getString(testModeEnabledIndex) == true.toString()
            }
    }
}