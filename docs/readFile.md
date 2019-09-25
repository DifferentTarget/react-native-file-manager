# readFile(path, opts, callback)

### Description

readFile is a function which takes in a path and some options and uses them to check the contents of a file.

returns: \<Promise>

### Parameters

\<String> path (required) [```undefined```]
> This is the path for the file that you want to read.

\<object> opts (optional) [```object```]

* \<string> storage (optional) [```'important'```]
> which storage method is the file be stored with.

> Opts contains all of the options for the file read

\<function> callback (optional) [```function```(err, data)]

* \<Error> err
> This is the error that will be given to the callback or in the catch of the promise

* \<String> data
> This is the data that was read from the file

> callback is called after the file is read
