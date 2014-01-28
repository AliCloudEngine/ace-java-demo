ACE Demo Application
===================================

Demo应用，演示ACE的功能：

- ACE服务的使用
    - `Cache`服务（缓存）
    - `Store`服务（文件存储）
    - `FetchUrl`服务（Http请求代理）
    - `Log`服务（收集日志在ACE控制台上查看）
- ACE环境配置的读取

代码如何使用，请直接看示例代码实现。

如何编译打包
---------------------------------

工程使用`Maven`来构建。

- [下载](http://maven.apache.org/download.cgi)`Maven`，推荐下载版本`2.2.1`。
- `Maven`的配置。压缩下载的`Maven`，把`bin`目录添加`PATH`上。
- 编译和打包，使用命令`mvn install`。在工程的`target`目录会生成Web应用的`war`包。

更多的`Maven`的信息，参见[Maven官网](http://maven.apache.org/)。

如何上传到`ACE`上运行
---------------------------------

登陆[ACE控制台](http://ace.console.aliyun.com/)，上传`War`包运行。

更多信息，参见[ACE控制台使用文档](http://help.aliyun.com/manual?spm=0.0.0.0.JtUkjp&lastSortId=278)。

> 本Demo的线上运行的应用的URL： http://ace-showcase.aliapp.com/ 。

FAQ
---------------------------------

1. `Maven`编译打包时出现异常`Cannot construct org.apache.maven.plugin.war.util.WebappStructure as it does not have a no-args constructor`  
先执行`Maven` `clean`操作： `mvn clean`。
