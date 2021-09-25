# 校园网助手

[![](https://img.shields.io/github/last-commit/lzw-723/xyw)](https://github.com/lzw-723/xyw)
![](https://img.shields.io/github/languages/top/lzw-723/xyw)
[![](https://img.shields.io/github/v/release/lzw-723/xyw?include_prereleases)](https://github.com/lzw-723/xyw/releases)
[![](https://img.shields.io/github/downloads/lzw-723/xyw/total)](https://github.com/lzw-723/xyw/releases)

## 简介
**井冈山大学**校园网助手，自动检查校园网账号登录状态、自动重新登录。

## 注意
* **切勿非法使用**
* **不正当使用造成的后果由用户自行承担**

## 所需环境
* `JDK 1.8`及更高
* `maven 3.2+`

## 构建
拉取代码后，进入项目根目录，执行如下命令
```shell
mvn package
```
打包成功后，输出的文件会在项目根目录下的`target\xyw-version.jar`

## 配置
在`xyz.jar`所在目录下编辑（或新建）`config.properties`文件
内容示例：
```txt
#学号
ddddd=2333333666

#密码
upass=326666
```
## 运行
同目录下执行`java -jar xyw-version.jar`命令运行

## 许可证
本项目使用[GPL-v3](./LICENSE)发布。

## 后记
校园网真鸡儿难用。