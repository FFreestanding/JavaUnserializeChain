## 配套小结链接
[配套小结](https://github.com/FFreestanding/JavaUnserializeChain/blob/main/1_%E5%8F%8D%E5%BA%8F%E5%88%97%E5%8C%96%E6%BC%8F%E6%B4%9E%E5%B0%8F%E7%BB%93.md)
## 了解CC组件的链子

Commons-Collections组件主要有两大版本：cc3和cc4
cc3可用的链有：1、3、5、6、7
cc4可用的链有：2、4

## CC3依赖

```xml
<dependency>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
    <version>3.1</version>
</dependency>
```

JDK8u71之前CC1、CC3，之后CC6

## CC2依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-collections4</artifactId>
        <version>4.0</version>
    </dependency>

    <dependency>
        <groupId>org.javassist</groupId>
        <artifactId>javassist</artifactId>
        <version>3.22.0-GA</version>
    </dependency>
</dependencies>
```

[下表原链接](https://www.cnblogs.com/colo/p/15418938.html)

|Chains| Version       |JDK Version|Note|
|---|---|---|---|
|CC1|CC3.1 - 3.2.1|1.7（8u71之后已修复不可利用）||
|CC2|CC4.0|暂无限制|javassist|
|CC3|CC3.1 - 3.2.1|1.7 （8u71之后已修复不可利用）|javassist|
|CC4|CC4.0|暂无限制|javassist|
|CC5|CC3.1 - 3.2.1|1.8 8u76（实测8u181也可）||
|CC6|CC3.1 - 3.2.1|暂无限制||
|CC7|CC3.1|||
|CB1||jdk8 不受版本影响||

