package com.sengami.sample_testable_app_android

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.sengami.sample_testable_app_android.databinding.ActivitySampleListBinding


class SampleListActivity : Activity() {

    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySampleListBinding>(
            this,
            R.layout.activity_sample_list
        )

        AppiumTestHelperDetector(this)
            .isTestModeEnabled()
            .toString()
            .run(binding.testModeEnabledTextView::setText)

        getSharedPreferences(Consts.SHARED_PREFERENCES_NAME, MODE_PRIVATE).let { prefs ->
            val appLaunches = prefs.getInt(Consts.KEY_NUMBER_OF_LAUNCHES, 0) + 1
            prefs
                .edit()
                .putInt(Consts.KEY_NUMBER_OF_LAUNCHES, appLaunches)
                .commit()
            binding.appLaunchesTextView.text = appLaunches.toString()
        }

        val elements = resources.getStringArray(R.array.elements)
        binding.sampleListView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            elements
        )
        binding.sampleListView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, SampleDetailsActivity::class.java)
            intent.putExtra(Consts.EXTRA_SAMPLE, elements[position])
            startActivity(intent)
        }
    }
}
