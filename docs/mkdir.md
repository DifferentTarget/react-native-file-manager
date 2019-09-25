# mkdir(path, opts, callback)

### Description

mkdir is a function which takes in a path and some options and uses them to create a folder that is the same as the path.

returns: \<Promise>

### Parameters

\<String> path (required) [```undefined```]
> This is the file path that you want to create. The target folder will be seen as all data before the last backslash.

\<object> opts (optional) [```object```]

* \<string> storage (optional) [```'important'```]
> which storage method should the file be stored with

* \<boolean> recursive (optional) [```true```]
> if the parent directory is undefined will it be created

> Opts contains all of the options for the directory creation

\<function> callback (optional) [```function```(err)]

* \<Error> err
> This is the error that will be given to the callback or in the catch of the promise

> callback is called after the file creation has completed
