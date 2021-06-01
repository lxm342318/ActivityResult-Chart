package com.lxm.test.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.lxm.test.ui.SecondActivity

class ActivityContract : ActivityResultContract<String, String>() {

    override fun createIntent(context: Context, input: String?): Intent {
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra("data", input.toString())
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        val  data  = intent?.getStringExtra("data")
        return if (resultCode == Activity.RESULT_OK && data != null)
            data
        else ""
    }
}
