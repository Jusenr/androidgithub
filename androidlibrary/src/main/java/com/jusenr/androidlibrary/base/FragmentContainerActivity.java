package com.jusenr.androidlibrary.base;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jusenr.androidlibrary.R;

/**
 * Created by riven_chris on 2017/5/31.
 */
public class FragmentContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        String className = getIntent().getStringExtra("className");
        Bundle extras = getIntent().getExtras();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, Fragment.instantiate(this, className, extras), className)
                .commit();
    }

    public static void startActivity(Context context, Class fragment, Bundle bundle) {
        Intent intent = new Intent(context, FragmentContainerActivity.class);
        intent.putExtra("className", fragment.getName());
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class fragment) {
        startActivity(context, fragment, null);
    }
}
