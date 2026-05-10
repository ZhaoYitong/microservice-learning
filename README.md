# Microservice Learning - Java 21 Spring Cloud

这是一个基于 Java 21 和 Spring Cloud 的微服务学习项目。

## 项目结构

```
microservice-learning/
├── common/                 # 公共模块
├── service-registry/       # 服务注册中心 (Eureka)
├── service-gateway/        # API 网关
├── service-user/           # 用户服务
├── service-order/          # 订单服务
└── service-product/        # 商品服务
```

## 技术栈

- **Java**: 21
- **Spring Boot**: 3.2.0
- **Spring Cloud**: 2023.0.0
- **构建工具**: Maven 3.8+

## 服务端口

| 服务 | 端口 | 说明 |
|------|------|------|
| service-registry | 8761 | Eureka 服务注册中心 |
| service-gateway | 8888 | API 网关 |
| service-user | 8081 | 用户服务 |
| service-order | 8082 | 订单服务 |
| service-product | 8083 | 商品服务 |

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/ZhaoYitong/microservice-learning.git
cd microservice-learning
```

### 2. 构建项目

```bash
mvn clean install
```

### 3. 启动服务（按顺序）

```bash
# 终端 1 - 启动服务注册中心
cd service-registry
mvn spring-boot:run

# 终端 2 - 启动 API 网关
cd service-gateway
mvn spring-boot:run

# 终端 3 - 启动用户服务
cd service-user
mvn spring-boot:run

# 终端 4 - 启动订单服务
cd service-order
mvn spring-boot:run

# 终端 5 - 启动商品服务
cd service-product
mvn spring-boot:run
```

## 服务访问

### Eureka 控制面板

访问 http://localhost:8761/ 查看已注册的服务

### API 网关路由

```bash
# 用户服务
curl http://localhost:8888/user/api/users

# 订单服务
curl http://localhost:8888/order/api/orders

# 商品服务
curl http://localhost:8888/product/api/products
```

## 学习路线

1. ✅ 理解多模块 Maven 项目结构
2. ✅ 学习 Spring Cloud 微服务架构
3. ✅ 服务注册与发现（Eureka）
4. ✅ API 网关（Spring Cloud Gateway）
5. 📌 服务间通信（Feign）
6. 📌 负载均衡（Ribbon）
7. 📌 服务容错（Hystrix）
8. 📌 链路追踪（Sleuth + Zipkin）
9. 📌 配置中心（Config Server）
10. 📌 消息队列集成（RabbitMQ）

## 常见问题

### Q: 如何添加新的微服务？

A: 复制 `service-user` 目录，修改模块名称和配置即可。

### Q: 如何在服务间进行通信？

A: 使用 Feign 客户端或 RestTemplate。

### Q: 如何实现服务容错？

A: 使用 Spring Cloud Hystrix 实现断路器模式。

## 贡献

欢迎提交 Issue 和 Pull Request！

## License

MIT