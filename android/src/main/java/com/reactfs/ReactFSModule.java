
package com.reactfs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNFsModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNFsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "fs";
  }

  @ReactMethod
  public void mkdir(String path, ReadableMap opts, Callback callback){

  }

  @ReactMethod
  public void writeFile(String path, String data, ReadableMap opts, Callback callback){

  }

  @ReactMethod
  public void readFile(String path, ReadableMap opts, Callback callback){

  }

  @ReactMethod
  public void unlink(String path, Callback callback){

  }

  @ReactMethod
  public void exist(String path, ReadableMap opts, Callback callback){

  }
}
