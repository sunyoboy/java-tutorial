# java-tutorial
tutorials on common tools

创建maven项目 命令

mvn archetype:generate -DgroupId=com.lieve -DartifactId=Online -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false -DarchetypeCatalog=local

显示桌面 command + F3 OR Fn + F11
    .   开发者工具：option＋command＋i 
    .   javascript控制台：option＋command＋j 
    .   或者按option＋command＋c也可以打开

    NAVC WXJ5 JGLJ 3CA6

    http://www.cnblogs.com/xunziji/archive/2012/08/23/2652530.html
# slf4j
 * slf4j提供的是一个抽象的接口，实现可以是log4j、logback等，但是，运行时候只能有一个接口实现类。
 * slf4j提供各种抽象接口，日志应该基于slf4j的API进行日志打印，这样无论迁移到那个项目，只需要配一个实现类log4j or logback,都能正常打印日志
 * slf4j的实现类不能有多个，不然冲突
 * 如果项目中有直接引用log4j的，可以加入log4j-over-slf4j，把旧的日志log4j适配到slf4j,这时候，再使用logback就可以了。
 
 [原文]（http://www.cnblogs.com/huayu0815/p/5341712.html） 
