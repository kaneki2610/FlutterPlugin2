import 'dart:async';

import 'package:flutter/services.dart';

class Pluginflutter {
  static const MethodChannel _channel =
      const MethodChannel('pluginflutter');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
