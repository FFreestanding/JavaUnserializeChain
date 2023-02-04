Commons-Collections组件主要有两大版本：cc3和cc4
cc3可用的链有：1、3、5、6、7
cc4可用的链有：2、4
---------------------------------------
    <dependency>
        <groupId>commons-collections</groupId>
        <artifactId>commons-collections</artifactId>
        <version>3.1</version>
    </dependency>
JDK8u71之前CC1、CC3，之后CC6
---------------------------------------
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
CC2

---------------------------------------------------------------------------------------------------------------------
CommonsCollections Gadget Chains	CommonsCollection Version	JDK Version			Note
CommonsCollections1			CommonsCollections3.1 - 3.2.1	1.7 （8u71之后已修复不可利用）	无
CommonsCollections2			CommonsCollections4.0		暂无限制			javassist
CommonsCollections3			CommonsCollections3.1 - 3.2.1	1.7 （8u71之后已修复不可利用）	javassist
CommonsCollections4			CommonsCollections4.0		暂无限制			javassist
CommonsCollections5			CommonsCollections3.1 - 3.2.1	1.8 8u76（实测8u181也可）	无
CommonsCollections6			CommonsCollections3.1 - 3.2.1	暂无限制			无
---------------------------------------------------------------------------------------------------------------------


---------------------------------------