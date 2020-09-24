import 'dart:async';

import 'package:flutter/services.dart';

class Pluginflutter {
  static const MethodChannel _channel = const MethodChannel('flutterplugin');

  Pluginflutter._privateConstructor();

  static final Pluginflutter _instance = Pluginflutter._privateConstructor();

  factory Pluginflutter() {
    return _instance;
  }

  //MethodChannel
  Future<String> getDeviceInfo(Map<String, dynamic> value) async {
    final String info = await _channel.invokeMethod('startActivityplugin', value);
    return info;
  }
}
