
package com.reactfs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
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
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    String folder = path.substring(0, path.lastIndexOf("/"));
    File path = new File(root + "/" + folder);

    Boolean recursive = opts.hasKey("recursive")? opts.getString("storage") : true;

    try {
      if(recursive){
        path.mkdirs();
      }
      else{
        path.mkdir();
      }
      callback.invoke(null);
    }
    catch(Exception err) {
      callback.invoke(err);
    }
  }

  @ReactMethod
  public void writeFile(String path, String data, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    try {
      Boolean append = opt.hasKey("append")? opts.getBoolean("append") : false;

      String encoding = opts.hasKey("encoding")? opts.getString("encoding") : "utf-8";
      Charset characterSet = null;
      switch(encoding){
        case "ISO8859-1":
          characterSet = StandardCharsets.ISO_8859_1;
          break;
        case "ascii":
        characterSet = StandardCharsets.US_ASCII;
          break;
        case "utf-16":
        characterSet = StandardCharsets.UTF_16;
          break;
        case "utf-8":
        default:
          characterSet = StandardCharsets.UTF_8;
      }

      if(append){
        Files.write(file, data, StandardOpenOption.APPEND, characterSet);
      }
      else{
        Files.write(file, data, characterSet);
      }
      callback.invoke(null);
    }
    catch(Exception err){
      callback.invoke(err);
    }
  }

  //TODO: error handle
  @ReactMethod
  public void readFile(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    Scanner scanner = new Scanner(file);
    scanner.useDelimiter("\\Z");

    Error err = null;
    callback.invoke(err, scanner.next());
  }

  //TODO: error handle
  @ReactMethod
  public void unlink(String path, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    Files.delete(file);

    Error err = null;
    callback.invoke(err);
  }

  //TODO: error handle
  @ReactMethod
  public void exist(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    Boolean exists = file.exists()

    Error err = null;
    callback.invoke(err, exists);
  }

  //TODO: error handle
  //TODO: files
  //TODO: folders
  //TODO: walk
  @ReactMethod
  public void list(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File folder = new File(root + "/" + path);

    File[] folders = folder.listFiles();

    Error err = null;
    callback.invoke(err, folders);
  }
}
