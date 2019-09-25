# react-native-filesystem

Simple file system access on iOS &amp; Android.

## Setup
> Currently there is no easy setup with npm. It will be coming in the future when this project is in a working state.

## Usage

For a full list of available methods, see the [API Reference](docs/reference.md).

```javascript
import fs from 'react-native-filesystem';

//Create a folder
fs.mkdir('path'[, options, (err) => {}]); return Promise
//Create File
fs.writeFile('path', data[, {options}, (err) => {}]); return Promise
//Read file
fs.readFile('path'[, {options, (err, data) => {}}]); return Promise
//Delete File/Path
fs.unlink('path'[, (err) => {}]); return Promise
//Check if file exist
fs.exist(path[, options, (err, exist) => {}]); return Promise
//List files in folder
fs.list(path[options, (err, files) => {}]); return Promise
```

### Selecting a storage class

Data stored can be in one of may different classes. These classes defined how the device will handel the data when space gets low.

[iOS Data Storage Guidelines](https://developer.apple.com/icloud/documentation/data-storage/index.html),


Files need to be read from the same storage class they're saved to, and two files can have the same
name if they're located in different classes. The options are:

| Class | Description |
|---------------|-------------|
| `backedUp` | These files are automatically backed up on supported devices
| `important` | Excluded from backup, but still kept around in low-storage situations
| `auxiliary` | Files that the app can function without. Can be deleted by the system in low-storage situations.
| `temporary` | For temporary files and caches. Can be deleted by the system any time.
