
import { NativeModules } from 'react-native';

const { fs } = NativeModules;

function mkdir(path, opts, callback){
  if(typeof opts === 'function'){
    callback = opts;
    opts = {};
  }
  if(typeof callback !== 'function'){
    return new Promise(function(resolve, reject) {
      fs.mkdir(path, opts, (err) => {
        if(err !== undefined){
          return reject(err);
        }
        resolve();
      });
    });
  }
  else{
    fs.mkdir(path, opts, callback);
  }
}

function writeFile(path, data, opts, callback){
  if(typeof opts === 'function'){
    callback = opts;
    opts = {};
  }
  if(typeof callback !== 'function'){
    return new Promise(function(resolve, reject) {
      fs.writeFile(path, data, opts, (err) => {
        if(err !== undefined){
          return reject(err);
        }
        resolve();
      });
    });
  }
  else{
    fs.writeFile(path, data, opts, callback);
  }
}

function readFile(path, opts, callback){
  if(typeof opts === 'function'){
    callback = opts;
    opts = {};
  }
  if(typeof callback !== 'function'){
    return new Promise(function(resolve, reject) {
      fs.readFile(path, opts, (err, data) => {
        if(err !== undefined){
          return reject(err);
        }
        resolve(data);
      });
    });
  }
  else{
    fs.readFile(path, opts, callback);
  }
}

function unlink(path, opts, callback){
  if(typeof opts === 'function'){
    callback = opts;
    opts = {};
  }
  if(typeof callback !== 'function'){
    return new Promise(function(resolve, reject) {
      fs.unlink(path, opts, (err) => {
        if(err !== undefined){
          return reject(err);
        }
        resolve();
      });
    });
  }
  else{
    fs.unlink(path, opts, callback);
  }
}

function exist(path, opts, callback){
  if(typeof opts === 'function'){
    callback = opts;
    opts = {};
  }
  if(typeof callback !== 'function'){
    return new Promise(function(resolve, reject) {
      fs.exist(path, opts, (err, data) => {
        if(err !== undefined){
          return reject(err);
        }
        resolve(data);
      });
    });
  }
  else{
    fs.exist(path, opts, callback);
  }
}

function list(path, opts, callback){
  if(typeof opts === 'function'){
    callback = opts;
    opts = {};
  }
  if(typeof callback !== 'function'){
    return new Promise(function(resolve, reject) {
      fs.list(path, opts, (err, data) => {
        if(err !== undefined){
          return reject(err);
        }
        resolve(data);
      });
    });
  }
  else{
    fs.list(path, opts, callback);
  }
}

export default {
  mkdir: mkdir,
  writeFile: writeFile,
  readFile: readFile,
  unlink: unlink,
  exist: exist,
  list: list,
};
