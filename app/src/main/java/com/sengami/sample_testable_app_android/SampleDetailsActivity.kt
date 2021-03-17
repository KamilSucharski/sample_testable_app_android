package com.sengami.sample_testable_app_android

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sengami.sample_testable_app_android.databinding.ActivitySampleDetailsBinding

class SampleDetailsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySampleDetailsBinding>(
            this,
            R.layout.activity_sample_details
        )
        intent
            ?.extras
            ?.getString(Consts.EXTRA_SAMPLE)
            ?.run(binding.sampleNameTextView::setText)
    }
}
