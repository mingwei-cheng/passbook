# 一款简易的在线密码记事本
文件位置
-----
数据库文件在src/resources/sql/文件夹下

网页文件在src/resourcs/static/文件夹下

yml配置文件的数据库配置需自行修改

项目难点
-----
整个项目唯一的难点在于，DES加解密的兼容问题，在windows与linux中的底层算法不同，不同环境的字符编码也不同（.getBytes()方法使用默认的编码返回，Win和linux默认编码不一样，Win返回utf-8，Linux返回GBK），都需指定成确定的类型。
