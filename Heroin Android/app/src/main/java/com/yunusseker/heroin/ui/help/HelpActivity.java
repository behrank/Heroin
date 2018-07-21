package com.yunusseker.heroin.ui.help;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yunusseker.heroin.R;
import com.yunusseker.heroin.base.BaseActivity;
import com.yunusseker.heroin.databinding.ActHelpBinding;
import com.yunusseker.heroin.ui.rating_after_helping.RatingAfterHelpingActivity;

public class HelpActivity extends BaseActivity<ActHelpBinding> {
    @Override
    public int getLayoutRes() {
        return R.layout.act_help;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding.item1.setOnClickListener(v -> startActivityWithBackstack(RatingAfterHelpingActivity.class));
        dataBinding.item2.setOnClickListener(v -> startActivityWithBackstack(RatingAfterHelpingActivity.class));
        dataBinding.item3.setOnClickListener(v -> startActivityWithBackstack(RatingAfterHelpingActivity.class));
    }
}
