# storage(storage, callback)

### Description

storage is a function which takes in a storage method and tells you where in memory it is located.

returns: \<Promise>

### Parameters

\<String> storage (required) [```undefined```]
> This is the storage method that you want to check.

\<function> callback (optional) [```function```(err, dir)]

* \<Error> err
> This is the error that will be given to the callback or in the catch of the promise

* \<String> dir
> A string representing the directory for the storage

> callback is called after the check has been completed
