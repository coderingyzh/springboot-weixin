# weixin-springboot-
用springboot+mybatis框架写的微信公众号的基础开发(消息回复，获取openid等)

spring 项目下mvnw和mvnw.cmd的作用
作用:
mvnw是一个maven wrapper script,它可以让你在没有安装maven或者maven版本不兼容的条件下运行maven的命令.
注意，需要与mvnw软件区分开，spring项目中的mvnw仅仅是个脚本，并非软件。
原理:
1. 首先寻找maven在你电脑环境变量path中的路径
2. 如果没有找到这个路径它就会自动下载maven到一个默认的路径下,之后你就可以运行maven命令
3. 如果碰到一些项目的peoject和你本地的maven不兼容,它会帮你下载合适的maven版本,然后运行
