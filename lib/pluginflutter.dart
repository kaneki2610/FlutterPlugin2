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
  Future<dynamic> getDeviceInfo(Map<String, dynamic> value) async {
    return await _channel.invokeMethod('startActivityplugin', value);
  }
}
