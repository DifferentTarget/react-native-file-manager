# exist(path, opts, callback)

### Description

list is a function which takes in a path and some options and uses them to list all files/folders at that directory.

returns: \<Promise>

### Parameters

\<String> path (required) [```undefined```]
> This is the file path that you want to check.

\<object> opts (optional) [```object```]

* \<string> storage (optional) [```'important'```]
> which storage method that will be checked in

* \<boolean> files (optional) [```true```]
> Should files be included in the list

* \<boolean> folders (optional) [```false```]
> Should folders be included in the list

* \<boolean> walk (optional) [```false```]
> Should the list dig into folders or stay on the surface level

> Opts contains all of the options for the check

\<function> callback (optional) [```function```(err, files)]

* \<Error> err
> This is the error that will be given to the callback or in the catch of the promise

* \<String[]> files
> files is a array of strings that are file paths

> callback is called after the check has been compleated
