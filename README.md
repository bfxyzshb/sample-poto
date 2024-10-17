# Sample Poto
### api（api接入层）
  - 可以叫controller层。
### application（服务编排层）
  - 主要是接受领域事件，编排服务以及调用基础设施层完成业务数据的落地,发送MQ等等
  - 依赖domain和infrastructure
### domain（领域层）
  - 不依赖其他层
  - 定义领域对象，内聚领域逻辑；
  - 定义领域服务（跨领域对象的业务逻辑，业务逻辑放在单独的领域对象中不合适时可以放到领域服务中）
  - 定义infrastructure接口
### infrastructure（基础设施层）
  - 依赖domain
  - 实现domain定义的接口
    - db
    - cache
    - mq
    - rpc
    - ACL 反腐层（二、三方服务）

