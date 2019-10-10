
package com.reactfs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;

import java.util.Arrays;
import java.util.ArrayList;

import java.io.File;
import java.io.FileOutputStream;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

public class ReactFSModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public ReactFSModule(ReactApplicationContext reactContext){
    super(reactContext);
    this.reactContext = reactContext;
  }


  private String baseDirForStorage(String storage) {
    switch (storage) {
      case "backedUp":
        return getReactApplicationContext().getFilesDir().getAbsolutePath();
      case "important":
        return getReactApplicationContext().getFilesDir().getAbsolutePath();
      case "auxiliary":
        return getReactApplicationContext().getFilesDir().getAbsolutePath();
      case "temporary":
        return getReactApplicationContext().getCacheDir().getAbsolutePath();
      case "root":
        return "";
      default:
        throw new RuntimeException("Unrecognized storage: " + storage);
    }
  }

  @Override
  public String getName() {
    return "fs";
  }

  @ReactMethod
  public void storage(String storage, Callback callback){
    try {
      callback.invoke(null, baseDirForStorage(storage));
    }
    catch(Exception err) {
      callback.invoke(err.toString());
    }
  }

  @ReactMethod
  public void mkdir(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File folder = new File(root + "/" + path.substring(0, path.lastIndexOf("/")));

    Boolean recursive = opts.hasKey("recursive")? opts.getBoolean("recursive") : true;

    try {
      if(recursive){
        folder.mkdirs();
      }
      else{
        folder.mkdir();
      }
      callback.invoke();
    }
    catch(Exception err) {
      callback.invoke(err.toString());
    }
  }

  @ReactMethod
  public void writeFile(String path, String data, ReadableMap opts, Callback callback){
    //Get the root directory for the specified storage method
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    //Get the file for the path and its storage method
    File file = new File(root + "/" + path);

    //Get if we are appending the file
    Boolean append = opts.hasKey("append")? opts.getBoolean("append") : false;

    //Get the files encoding
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

    FileOutputStream writer = null;
    try {
      //Create the writer
      writer = new FileOutputStream(file, append);
      //Write to the file
      writer.write(data.getBytes(characterSet));
      //Run the callback with no error
      callback.invoke();
    }
    catch(Exception err){
      //If we had an error run the callback with an error
      callback.invoke(err.toString());
    }
    finally {
      //Close the writer if we had an error or not
      try {
        if(writer != null){
          writer.close();
        }
      }
      catch(Exception ignored){ }
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
      callback.invoke(err.toString());
    }
  }

  @ReactMethod
  public void unlink(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    try {
      file.delete();
      callback.invoke();
    }
    catch(Exception err){
      callback.invoke(err.toString());
    }
  }

  @ReactMethod
  public void exist(String path, ReadableMap opts, Callback callback){
    String root = baseDirForStorage(opts.hasKey("storage")? opts.getString("storage") : "important");
    File file = new File(root + "/" + path);

    try {
      Boolean exists = file.exists();
      callback.invoke(null, exists);
    }
    catch(Exception err){
      callback.invoke(err.toString());
    }
  }

  //TODO: mutithread walk
  //This is a helper function for the ReactMethod list that makes walking easy
  public ArrayList<File> list(File path, Boolean files, Boolean folders, Boolean walk){
    ArrayList<File> found = new ArrayList<File>(Arrays.asList(path.listFiles()));

    if(!files || !folders || walk){
      for(int i = found.size() - 1; i >= 0; i--){
        if(found.get(i).isFile()){
          if(!files){
            found.remove(i);
          }
        }
        else {
          if(walk){
            found.addAll(list(found.get(i), files, folders, walk));
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

    Boolean files = opts.hasKey("files")? opts.getBoolean("files") : true;
    Boolean folders = opts.hasKey("folders")? opts.getBoolean("folders") : true;
    Boolean walk = opts.hasKey("walk")? opts.getBoolean("walk") : false;

    try {
      ArrayList<File> found = list(folder, files, folders, walk);
      //TODO use an actual array and not this string method
      String filesFound = "";
      for(int i = found.size() - 1; i > -1; i--){
        filesFound = filesFound + found.get(i).toString().substring(root.length()) + ",";
      }

      callback.invoke(null, filesFound);
    }
    catch(Exception err){
      // StackTraceElement[] e = err.getStackTrace();
      // String[] tmp = new String[e.length];
      // for(int i = 0; i < e.length; i++){
      //   tmp[i] = e[i].toString();
      // }
      callback.invoke(err.toString());
    }
  }
}
