masr - More than Automatic Speech Recognition
===============================================

build:
```
gradlew clean build
```

run:
```
java -jar ./build/libs/masr-xxx.jar
```

deploy:
```
https://www.tianmaying.com/tutorial/deploy-spring-boot-application
```

Open powershell with administrator 
```
set-ExecutionPolicy RemoteSigned
```or 
```
set-ExecutionPolicy Unrestricted 
```
then
```
deploy.ps1
```

https://unix.stackexchange.com/questions/27054/bin-bash-no-such-file-or-directory

If met the unrecognize command error, try the solution:
```
vi <your_file>
:set ff=unix
:set nobomb
:wq
```