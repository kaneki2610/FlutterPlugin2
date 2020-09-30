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

	public void changeData(String value) {
		if(mListener != null) {
			onData = value;
			notifyDataChange();
		}
	}

	public String getData() {
		return onData;
	}

	private void notifyDataChange() {
		mListener.onDataChange();
	}
}
