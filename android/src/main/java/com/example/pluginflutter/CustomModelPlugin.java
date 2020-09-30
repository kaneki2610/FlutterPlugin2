package com.example.pluginflutter;

import java.util.HashMap;
import java.util.Map;

public class CustomModelPlugin {
	public interface OnCustomStateListener {
		void onDataChange();
	}

	private static CustomModelPlugin mInstance;
	private OnCustomStateListener mListener;
	private String onData = "";
	private String onEvent = "";

	private CustomModelPlugin() {}

	public static CustomModelPlugin getInstance() {
		if(mInstance == null) {
			mInstance = new CustomModelPlugin();
		}
		return mInstance;
	}

	public void setListener(OnCustomStateListener listener) {
		mListener = listener;
	}

	public void changeData(String value, String event) {
		if(mListener != null) {
			onData = value;
			onEvent = event;
			notifyDataChange();
		}
	}

	public Map<String, String> getData() {
		Map<String, String > map = new HashMap<>();
		map.put("value", onData);
		map.put("event", onEvent);
		return map;
	}

	private void notifyDataChange() {
		mListener.onDataChange();
	}
}
