# medcost
medcost后端


## 页面及模块命名

https://docs.qq.com/sheet/DUld6U21sd1JEdGZH?tab=BB08J2

## 启动

将application.yml移入main/resources目录下，并将数据库等配置按照自己主机的配置配好。

## application.yml

```
spring:
  datasource:
  #需要改成自己本地数据库配置
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/medcost?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true 
    username: root
    password: 123456
    redis:
      host: localhost # Redis服务器地址
      database: 0 # Redis数据库索引（默认为0）
      port: 6379 # Redis服务器连接端口
      password: # Redis服务器连接密码（默认为空）
      jedis:
        pool:
          max-active: 8 # 连接池最大连接数（使用负值表示没有限制）
          max-wait: -1ms # 连接池最大阻塞等待时间（使用负值表示没有限制）
          max-idle: 8 # 连接池中的最大空闲连接
          min-idle: 0 # 连接池中的最小空闲连接
      timeout: 3000ms # 连接超时时间（毫秒）
jwt:
  tokenHeader: Authorization #JWT存储的请求头
  secret: mall-admin-secret #JWT加解密使用的密钥
  expiration: 604800 #JWT的超期限时间(60*60*24*7)
  tokenHead: 'Bearer '  #JWT负载中拿到开头
# 自定义redis key
redis:
  key:
    prefix:
      authCode: "portal:authCode:"
    expire:
      authCode: 120 # 验证码超期时间
mybatis-plus:
  mapper-locations: classpath*:/mapper/**Mapper.xml
server:
  port: 8081

##shiro-redis:
##  enabled: true
##  redis-manager:
##    host: 127.0.0.1:6379
#markerhub:
#  jwt:
#    # 加密秘钥
#    secret: f4e2e52034348f86b67cde581c0f9eb5
#    # token有效时长，7天，单位秒
#    expire: 604800
#    header: Authorization

#测试

# OSS相关配置信息
aliyun:
  oss:
    endpoint: oss-cn-beijing.aliyuncs.com # oss对外服务的访问域名
    accessKeyId:  # 访问身份验证中用到用户标识，敏感信息不公开(微信群里有)
    accessKeySecret:  # 用户用于加密签名字符串和oss用来验证签名字符串的密钥，敏感信息不公开（微信群里有）
    bucketName: medcost-oss # oss的存储空间
    policy:
      expire: 300 # 签名有效期(S)
    maxSize: 10 # 上传文件大小(M)
    callback: http://localhost:8081/aliyun/oss/callback # 文件上传成功后的回调地址
    dir:
      prefix: medcost/images/ # 上传文件夹路径前缀
```
