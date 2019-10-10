# writeFile(path, data, opts, callback)

### Description

writeFile is a function which takes in a path, data and some options and uses them to write to a file.

returns: \<Promise>

### Parameters

\<String> path (required) [```undefined```]
> This is the path for the file that you want to write to.

\<String> data (required) [```undefined```]
> This is the data that you want to write to the file

\<object> opts (optional) [```object```]

* \<string> storage (optional) [```'important'```]
> which storage method is the file is stored with.

* \<string> encoding (optional) [```'utf8'```]
> this is the file encoding that the file is using

* \<boolean> append (optional) [```false```]
> This sets the function to append mode which only adds to the end of the file

> Opts contains all of the options for the file write

\<function> callback (optional) [```function```(err)]

* \<Error> err
> This is the error that will be given to the callback or in the catch of the promise

> callback is called after the file is written to
