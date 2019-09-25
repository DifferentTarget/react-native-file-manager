# unlink(path, opts, callback)

### Description

unlink is a function which takes in a path and some options and uses them to remove a file/folder.

returns: \<Promise>

### Parameters

\<String> path (required) [```undefined```]
> This is the path for the file that you want to remove.

\<object> opts (optional) [```object```]

* \<string> storage (optional) [```'important'```]
> which storage method is the file is stored with.

> Opts contains all of the options for the file read

\<function> callback (optional) [```function```(err)]

* \<Error> err
> This is the error that will be given to the callback or in the catch of the promise

> callback is called after the file is deleted
