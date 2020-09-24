
package com.example.pluginflutter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.customactivity.CustomActivity;


public class SecondActivity extends AppCompatActivity implements CustomActivity.OnSelectedListener {
	ImageView btnBackFlutterView;
	String deviceInfo = "";
	String model = "";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);

		Intent intent = getIntent();
		model = intent.getStringExtra("type");

		btnBackFlutterView = findViewById(R.id.btnOnBackFlutterView);
		handleClick();
		FragmentManager fragmentManager = getSupportFragmentManager();
		CustomActivity newDesign = (CustomActivity) fragmentManager.findFragmentById(R.id.fragment);
		newDesign.setModel(model);
	}

	@Override
	public void onAttachFragment(@NonNull Fragment fragment) {
		super.onAttachFragment(fragment);
		if (fragment instanceof CustomActivity) {
			CustomActivity headlinesFragment = (CustomActivity) fragment;
			headlinesFragment.setOnHeadlineSelectedListener(this);
		}
	}

	public void handleClick() {
		btnBackFlutterView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Intent data = new Intent();
				data.putExtra("deviceInfo", deviceInfo);
				setResult(Activity.RESULT_OK, data);
				finish();
			}
		});
	}

	@Override
	public void onGetValue(String value) {
		deviceInfo = value;
	}
}