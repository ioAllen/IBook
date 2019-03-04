## IBook
本项目是为了学习插件化方案，仅限学习交流。

lib_common 是基础库 包涵 全部的第三方库依赖以及基础的基类

module_bookmall 书城 组件

module_bookshelf 书架 组件

module_common 通用 组件

在gradle.properties 文件中修改 isBuildModule 改变依赖方式
isBuildModule=true 表示module以 库的形式进行依赖
isBuildModule=false 表示module以 应用程序的形式可以单独编译
