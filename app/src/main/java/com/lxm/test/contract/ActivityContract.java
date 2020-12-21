package com.lxm.test.contract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.lxm.test.ui.SecondActivity;

public class ActivityContract extends ActivityResultContract<String, String> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, String input) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("data", input);
        return intent;
    }

    @Override
    public String parseResult(int resultCode, @Nullable Intent intent) {

        assert intent != null;
        String data = intent.getStringExtra("data");
        if (resultCode == Activity.RESULT_OK && data != null)
            return data;
        else return null;
    }
}
