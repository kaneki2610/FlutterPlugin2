#import "PluginflutterPlugin.h"
#if __has_include(<pluginflutter/pluginflutter-Swift.h>)
#import <pluginflutter/pluginflutter-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "pluginflutter-Swift.h"
#endif

@implementation PluginflutterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPluginflutterPlugin registerWithRegistrar:registrar];
}
@end
