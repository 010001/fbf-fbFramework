#### 项目创建

- fbf-config
  - idea 创建maven项目，选择quick-start,快速创建pom项目
  - fbf-config 管理版本号和nexus仓库地址
- fbf-parent
  - 顶级父工程
  - 版本号管理
- fbf-log
  - 日志管理　增删改查　以及用户行为分析
    - 操作日志　记录操作详情
    - 审计日志　记录资源变化
  - 各模块日志收集
- fbf-report
  - 报表管理
- fbf-task
  - 任务管理（定时任务，异步任务）
- fbf-ticket
  - 角色管理
  - 权限校验
- fbf-user
  - 用户管理
- fbf-resource
  - 资源管理
- fbf-utils
  - 公共组件
- fbf-system
  - 系统组件
  - license管理
  - 系统版本号信息
  - 系统logo
  - 系统名称
  - 系统字典管理，便于OEM 换名字



#### 基础环境安装

- 安装mariadb数据库
  - 参考https://www.cnblogs.com/fb010001/p/12037655.html
- 安装redis
  - 参考　https://www.cnblogs.com/fb010001/p/12037792.html





### 技术问题

- 注册中心
- 配置中心　ｃｏｎｆｉｇ
- 路由　熔断　降级
- rabbitmq消息中间件
- springboot 注解
- springboot切面日志
- springboot 切面认证
- 认证
  - shrio认证
- feigin 调用
- http https
- resttemplate调用http https
- session共享
- 用搜搜引擎降低查询压力
- redis缓存
- redis锁
- mybatis
- mariadb集群
- zookeeper集群　zookeeper密码　认证
- rabbitmq 镜像　用户　密码
- k8s　服务容器化