package com.example.pluginflutter;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * PluginflutterPlugin
 */
public class PluginflutterPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware, PluginRegistry.ActivityResultListener {
	private static final String channelName = "fluttervnpt2";
	private static final int REQUEST_CODE_FOR_START_ACTIVITY = 2999;
	private static Activity activity;
	private Result pendingResult;
	private MethodChannel channel;

	@Override
	public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
		channel = new MethodChannel(binding.getFlutterEngine().getDartExecutor(), channelName);
		channel.setMethodCallHandler(this);
	}

	public static void registerWith(Registrar registrar) {
		activity = registrar.activity();
		final MethodChannel channel = new MethodChannel(registrar.messenger(), channelName);
		channel.setMethodCallHandler(new PluginflutterPlugin());
	}

	@Override
	public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
		channel.setMethodCallHandler(null);
	}

	@Override
	public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
		if (call.method.equals("startActivity")) {
			this.pendingResult = result;
			String type = call.argument("type");
			if (type == null || (type != null && type.isEmpty())) {
				result.error("ERROR", "type can not null", null);
			} else {
				Intent intent = new Intent(activity, SecondActivity.class);
				intent.putExtra("type", type);
				activity.startActivityForResult(intent, REQUEST_CODE_FOR_START_ACTIVITY);
			}
		} else {
			result.notImplemented();
		}
	}

	@Override
	public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
		activity = binding.getActivity();
		binding.addActivityResultListener(this);
	}

	@Override
	public void onDetachedFromActivityForConfigChanges() {

	}

	@Override
	public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

	}

	@Override
	public void onDetachedFromActivity() {
		activity = null;
	}

	@Override
	public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_FOR_START_ACTIVITY && data != null) {
			if (resultCode == Activity.RESULT_OK) {
				String result = data.getStringExtra("deviceInfo");
				pendingResult.success(result);
			} else {
				pendingResult.success("");
			}
			return true;
		}
		return false;
	}
}
