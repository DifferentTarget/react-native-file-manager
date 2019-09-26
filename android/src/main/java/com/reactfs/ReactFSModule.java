
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

  @ReactMethod
  public void readFile(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    try {
      Scanner scanner = new Scanner(file);
      scanner.useDelimiter("\\Z");
      callback.invoke(null, scanner.next());
    }
    catch (Exception err){
      callback.invoke(err, "");
    }
  }

  @ReactMethod
  public void unlink(String path, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    try {
      Files.delete(file);
      callback.invoke(null);
    }
    catch(Exception err){
      callback.invoke(err);
    }
  }

  @ReactMethod
  public void exist(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    try {
      Boolean exists = file.exists()
      callback.invoke(null, exists);
    }
    catch(Exception err){
      callback.invoke(err, false);
    }
  }


  public File[] list(File path, ReadableMap opts){
    Boolean files = opts.hasKey("files")? opts.getBoolean("files") : true;
    Boolean folders = opts.hasKey("folders")? opts.getBoolean("folders") : true;
    Boolean walk = opts.hasKey("walk")? opts.getBoolean("walk") : false;

    File[] found = path.listFiles();

    if(!files || !folders || walk){
      for(int i = found.length - 1; i >= 0; i--){
        if(found[i].isFile() && !files){
          found.remove(i);
        }
        else {
          if(walk){
            found = ArrayUtils.addAll(found, list(found, opts));
          }
          if(!folders){
            found.remove(i);
          }
        }
      }
    }
    return found;
  }

  @ReactMethod
  public void list(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File folder = new File(root + "/" + path);

    try {
      File[] found = list(folder, opts);
      callback.invoke(null, found);
    }
    catch(Exception err){
      callback.invoke(err, null);
    }
  }
}
