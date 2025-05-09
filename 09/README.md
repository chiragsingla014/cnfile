To run this, you need to have:

1. `.so` or `.dll` in the native folder
2. `.jar` in the lib folder

[Download JNetPcap](https://sourceforge.net/projects/jnetpcap/files/jnetpcap/Latest/)



### Commands to run:

```bash
javac -cp lib/jnetpcap.jar -d bin PacketCounter.java
java -cp lib/jnetpcap.jar:bin -Djava.library.path=native PacketCounter
```

-cp set the classpath for downloaded jar  
:bin also look in bin directory for java classes  
-d bin output directory bin/  
-Djava.library.path=native JVM option to locate path of native files(so in linux and dll in windows)  
 

### [Optional] To avoid red squiggly lines, add the following to 09/.vscode/settings.json in VSCode:

```json
"java.project.referencedLibraries": [
  "/home/chiragsingla014/Documents/COLLEGE/cnfile/09/lib/jnetpcap.jar"
]
```
### [Optional] To run via the play button, create a VSCode workspace and add this snippet to .vscode/launch.json:

```json
{
  "type": "java",
  "name": "PacketCounter",
  "request": "launch",
  "mainClass": "PacketCounter",  // Replace with your main class
  "vmArgs": "-Djava.library.path=${workspaceFolder}/09/native",  // Point to the folder with the .so file
  "classPaths": [
    "${workspaceFolder}/09/lib/jnetpcap.jar",  // Point to your JNetPcap jar file
    "${workspaceFolder}/09/bin"
  ],
  "cwd": "${workspaceFolder}/09/",
  "projectName": "cnfile_f2dba22b"
}
```