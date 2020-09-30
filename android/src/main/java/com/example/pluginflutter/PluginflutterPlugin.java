package com.example.pluginflutter;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

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
public class PluginflutterPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware, CustomModelPlugin.OnCustomStateListener {
	private static final String channelName = "flutterplugin";
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
		if (call.method.equals("startActivityplugin")) {
			this.pendingResult = result;
			String type = call.argument("type");
			if (type == null || (type != null && type.isEmpty())) {
				result.error("ERROR", "type can not null", null);
			} else {
				Intent intent = new Intent(activity, StartActivity.class);
				intent.putExtra("value", type);
				activity.startActivity(intent);
			}
		} else {
			result.notImplemented();
		}
	}

	@Override
	public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
		activity = binding.getActivity();
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
	public void onDataChange() {
		Map<String, String> data = CustomModelPlugin.getInstance().getData();
		JSONObject json = new JSONObject();
		try {
			json.put("value", data.get("value"));
			json.put("event", data.get("event"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		pendingResult.success(json);
	}
}
