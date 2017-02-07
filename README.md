Android course, labs and examples
=============================
# Examples
To compile the examples from the command line:
```
$ android update project -p . - t target_num
```
where target_num is one of the target id listed by the following command:
```
$ android list target
```
Then to compile in debug mode, install (emulator or device), and run:
```
$ ant debug
$ ant install
$ ant run
```
Otherwise, try to import it into your favorite IDE...

