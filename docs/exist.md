# exist(path, opts, callback)

### Description

exist is a function which takes in a path and some options and uses them to test if a folder or file exist.

returns: \<Promise>

### Parameters

\<String> path (required) [```undefined```]
> This is the file path that you want to check.

\<object> opts (optional) [```object```]

* \<string> storage (optional) [```'important'```]
> which storage method that will be checked in

> Opts contains all of the options for the check

\<function> callback (optional) [```function```(err, exist)]

* \<Error> err
> This is the error that will be given to the callback or in the catch of the promise

* \<boolean> exist
> Exist is a boolean value representing if the file exist or not

> callback is called after the check has been completed
