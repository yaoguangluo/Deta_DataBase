# 最新见 https://github.com/yaoguangluo/ChromosomeDNA

# 🚀 <<100%(首创)个人著作权开源项目: 2019R11L885247, 软著登字第4317518号>>, Deta quantum-field json PLSQL database integrated in deta VPCS http server. size 30KB / 量子碎片去死锁带binlog动态回滚的PLSQL数据库，启动50毫秒，100,000~4,000,000  QPS 注: http://tinos.qicp.vip/ Deta官方网站已经采用该数据库. 
## 注释：前端的angular，js,css等不属于个人著作权申请范围（vpcs socket流， 德塔plsql语言 和 德塔数据库引擎），详细文档地址如下：
https://github.com/yaoguangluo/Deta_Resource/blob/master/%E5%BE%B7%E5%A1%94Socket%E6%B5%81%E5%8F%AF%E7%BC%96%E7%A8%8B%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AF%AD%E8%A8%80%E5%BC%95%E6%93%8E%E7%B3%BB%E7%BB%9F1.0.0%20%E6%BA%90%E7%A0%81%20final.docx

## 基于Deta官方网站所有实际功能 需要逐步完善 需求函数细节.
##### 作者准备花些时间在gitee上进行中文注解其原理。20190624
说明书地址：
https://github.com/yaoguangluo/Deta_Resource/blob/master/%E5%BE%B7%E5%A1%94Socket%E6%B5%81%E5%8F%AF%E7%BC%96%E7%A8%8B%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AF%AD%E8%A8%80%E5%BC%95%E6%93%8E%E7%B3%BB%E7%BB%9FAPI%201.0.0%20%E8%AF%B4%E6%98%8E%E4%B9%A6%20final.docx

## 正在将 非个人著作权的 3个JAVA文件（zip unzip，中科大非对称加密 和 cacheManager 移动到如下项目中）
https://github.com/yaoguangluo/Data_Processor/tree/master/DP （zip 筛子加密和 cache 包）

## 德塔数据库源码，该数据的亮点为：一次执行多项复杂任务， 极为人性化的简易命令行 PLSQL 编程语言模型，打破现在所有传统关系数据库数据的单任务执行模式。基于双向队列的日志分析功能和命令语句执行方式和量子碎片文件存储思想，永远告别死锁。自动修复数据库，自动rollback异常数据操作，同时提供rest数据库操作接口和plsql命令行执行接口2种需求，满足各种场合的数据库操作应用。该数据库admin系统建立在deta VPCS HTTP服务器中， 整个系统启动时间 50毫秒。有效和运维交互。

##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：单表增删改查 。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：操作权限
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：超时 (token的早期不可逆加密方式思路来自10年前的中科大的筛子加密论文，后本人修改为2次组合加密，并采用mine decode 方式解码，在这感谢中科大) 具体例子：https://blog.csdn.net/weizhiiceboy3/article/details/51955026 等， 感谢csdn 如果需要更高级别的需求，可以将该方法进行客户端2次加密， 形成广义非对称加密模型。

##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：hash存储。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：并发。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：数据库2级缓存。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：采用VPC/VPCS（作者罗瑶光）架构
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：采用纯静态类接口。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：采用deta HTTP 服务器做 ADMIN。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：REST JSON 做第三方 request 扩展。。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：增加过滤，同源，限制机制。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：数据库查询映射区间。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：db plsql query设计 select。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：读写分离， 读操作全部3级缓存。。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：plsql 量子数据库语言去死锁机制。。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：德塔 PL/SQL 数据分析语言 定义规范。。功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：前端angular html页面， 感谢webadmin 开源项目组，本人采用了bootstrap原生js组件。
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：select功能的表join 函数功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：select功能的表aggregation limit 函数功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：semi join功能的表 relation 函数功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：plsql changeDBPath/ setRoot函数；功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：plsql create table函数；功能作者： 罗瑶光
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：db plsql query设计 update 功能作者： 罗瑶光2019-01-22
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：db plsql query设计 insert  功能作者： 罗瑶光2019-01-23 备注：没有检查，
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：db plsql query设计 delete  功能作者： 罗瑶光2019-01-24 备注：已经检查，
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：1 update rest接口涉及的缓存更新 功能作者：罗瑶光2019-01-24
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：2 delete rest接口涉及的缓存更新 功能作者：罗瑶光2019-01-24
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：3 insert rest接口涉及的缓存更新 功能作者：罗瑶光2019-01-24
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：之前因为没有内存映射区间，所以操作都是硬盘方式，现在有了3， 4级缓存，所有读走2,3,4级buffer，所以之前导致的硬盘修改写操作都要进行缓存更新，我需要完成这个功能，同时我还做了网页cache，这些相关的地方也要及时更新。2019-01-25
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：所有数据格式通过restcall的准备全部走gzip，功能已经实现在在另外一个项目中 https://github.com/yaoguangluo/Deta_VPCS_Frontend/blob/master/DetaSrc/org/deta/boot/vpc/vision/RestMapVision.java 到时候我会整合。2019-01-26~27
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：update 的 nest join 函数 功能作者：罗瑶光2019-01-28 采用离散数学的conjunction 地摩根迭代化简进行多join表的选择条件区分。2019-01-28
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：固定变量名称编程规范整体整理。动态集合先搁置。罗瑶光2019-01-29
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：语音媒体文件的发送。 罗瑶光2019-01-29
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：套接字阅读格式规范。 罗瑶光2019-01-29
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：tcp 协议 header格式规范。 罗瑶光2019-01-29
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：select where in 函数。罗瑶光2019-01-29
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：select where !in 函数。罗瑶光2019-01-29
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：日志管理系统的VPCS定义。罗瑶光2019-01-29
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：plsql日志binlog 记录定义。罗瑶光2019-01-30
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：plsql日志binlog 记录压缩简单实现。罗瑶光2019-01-30
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：plsql日志binlog 逐行写记录为优化版实现。罗瑶光2019-01-30
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：plsql编程规范文档1.0版发布。罗瑶光2019-01-30 https://github.com/yaoguangluo/VPCS_Theroy/blob/master/Deta_Database_PLSQL%20_V1.0.pdf
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：plsql 日志读过滤 和 写安全机制。。罗瑶光2019-01-30 
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：写错误的rollback 函数。 罗瑶光2019-01-30 
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：REST CALL ROLLBACK函数。 罗瑶光2019-01-30 
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：测试和真实运行plsql写操作 restcall 函数。方便为后端业务整体rollback功能打下扎实基础。罗瑶光2019-01-30 
//refer https://wenda.so.com/q/1512966734215123
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：binlog 函数 数据压缩热备，解压zip 格式没有检查和测试。 罗瑶光2019-01-31
//refer http://www.blogjava.net/dreamstone/archive/2007/08/09/134986.html
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：socket握手多核模式（该功能因需要硬件支持已去掉）。保证100,000 QPS 吞吐量.罗瑶光2019-02-02
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：rollback写操作并 binlog gzip 压缩文件记录 带时间和操作人。 罗瑶光2019-02-07
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：数据库热备函数已检测，运行成功。 罗瑶光2019-02-08
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：数据库按时间点进行整库恢复已完成，待检测。 罗瑶光2019-02-08

#### 数据库1.0未检测版本已经小节。
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：第二次固定变量名称编程规范整体整理 完成了编辑格式清理。 罗瑶光2019-03-05
##### ![实例](http://progressed.io/bar/100?title=completed)已完善功能：第二次固定变量名称编程规范整体整理 完成了命名格式清理。 罗瑶光2019-03-05


###### 预先搁置功能：table函数 的primary key， unique key函数管理系统设计。；（属于2级优先，先搁置）
######  正在完善功能：基于 VPCS 思想的写操作日志管理系统。
#####  ![实例](http://progressed.io/bar/90?title=completed)正在完善功能：已完成 并 准备检测  （binlog 函数 数据按时间恢复, 热备方式，按时间戳方式 恢复）。

###### 未完善功能：7种join模式。
https://www.codeproject.com/Articles/33052/Visual-Representation-of-SQL-Joins
###### 未完善功能：第二次固定变量名称编程规范整体整理。
###### 未完善功能：binlog 日志 大数据文件分页。
###### 未完善功能：binlog 日志加入追踪系统和5W系统。
###### 未完善功能：日志管理系统的自动触发器修复数据库机制。
###### 未完善功能：第三次固定变量名称编程规范整体整理。
###### 未完善功能：人工智能分析病毒篡改数据库文件的条件。
###### 未完善功能：人工智能分析人为的语法错误修复。
###### 未完善功能：人工智能分析非人为的数据异常操作和损毁的自动完善机制。
###### 未完善功能：基于sort key 前序treeMap 之后排序功能设计
###### 未完善功能：基于sort key 后序treeMap
###### 未完善功能：引擎算法优化
###### 未完善功能：序列化堆2分搜索加速
###### 未完善功能：第四次固定变量名称编程规范整体整理。
###### 未完善功能：jvmsets bit优化
###### 未完善功能：写错误的rollback 函数
###### 未完善功能：消息队列进行区域链接热备恢复。
###### 未完善功能：研发更多实用的函数计划。
###### 未完善功能：所有错误日志属性分级格式化记录。
###### 未完善功能：第五次固定变量名称编程规范整体整理。
###### 未完善功能：企业级或者商业版进行sonar认证

## 德塔 PL/SQL 数据分析语言 说明文档。
https://github.com/yaoguangluo/VPCS_Theroy/blob/master/Deta_Database_PLSQL%20_V1.0.pdf

###### /*
###### * -----------------------------------
###### * 罗瑶光/Yaoguang.Luo
###### * yaoguangluo@outlook.com-313699483@qq.com-2080315360@qq.com
###### * 15116110525-浏阳德塔软件开发有限公司-创始人，法人，总经理-- 非盈利--
###### * 430181198505250014-G24402609-EB0581342
###### * 204925063-389418686-F2406501-0626136
###### * 湖南省 长沙市 浏阳市区 集里街道 神仙坳社区 大塘冲路 一段 208
###### * 阳光家园别墅小区 第10栋别墅 第三层
###### * 
###### * --最近思考家的地址从2009年的北岭美林花苑9号改成石霜路阳光花园9号，
###### * 又石霜路阳光花园10号，又大塘路阳光家园10号，再大塘路一段阳光家园第10栋，
###### * 到现在集里大塘冲路一段208阳光家园别墅小区第10栋，我在想，频繁换名，有问题，
###### * 于是准确介绍下家地址。
###### * 
###### * --湖南省浏阳市集里街道-大塘冲路一段208阳光家园别墅小区第10栋--，位于大塘冲路
###### * 一段208阳光家园小区内的别墅房区入口内左侧，靠近美林花苑入口大门（美林花苑小区
###### * 内见到阳光家园的最前面金属围栏后便是(10号-面朝建筑左户)和（11号-面朝建筑右户）
###### * 2户联体别墅），美林花苑小区位于大塘冲路一段的十字路口，十字路线的以点轴心斜对面
###### * 是--浏阳市 大塘实验(普特融合)小学 建筑--。
###### * 
###### * --身份证上地址2011年后姑姑罗庆华一家入住。中国身份证件标注地址是户籍地址不是
###### * 居民地址，罗瑶光先生2018年后一直居住在阳光家园家的第三层。
###### **/


