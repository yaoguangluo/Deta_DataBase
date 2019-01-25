# 德塔数据库源码，该数据的亮点为：一次执行多项复杂任务， 简易命令行 PLSQL 编程语言模型，打破现在所有传统关系数据库数据的单任务执行模式。基于双向队列的日志分析功能和命令语句执行方式和量子碎片文件存储思想，永远告别死锁。同时提供rest数据库操作接口和plsql命令行执行接口2种需求，满足各种场合的数据库操作应用。该数据库admin系统建立在deta VPCS HTTP服务器中， 整个系统启动时间 50毫秒。有效和运维交互。

##### 已完善功能：单表增删改查 。功能作者： 罗瑶光
##### 已完善功能：操作权限
##### 已完善功能：超时 (token的早期不可逆加密方式思路来自10年前的中科大的筛子加密论文，后本人修改为2次组合加密，并采用mine decode 方式解码，在这感谢中科大) 具体例子：https://blog.csdn.net/weizhiiceboy3/article/details/51955026 等， 感谢csdn 如果需要更高级别的需求，可以将该方法进行客户端2次加密， 形成广义非对称加密模型。

##### 已完善功能：hash存储。功能作者： 罗瑶光
##### 已完善功能：并发。功能作者： 罗瑶光
##### 已完善功能：数据库2级缓存。功能作者： 罗瑶光
##### 已完善功能：采用VPC/VPCS（作者罗瑶光）架构
##### 已完善功能：采用纯静态类接口。功能作者： 罗瑶光
##### 已完善功能：采用deta HTTP 服务器做 ADMIN。功能作者： 罗瑶光
##### 已完善功能：REST JSON 做第三方 request 扩展。。功能作者： 罗瑶光
##### 已完善功能：增加过滤，同源，限制机制。功能作者： 罗瑶光
##### 已完善功能：数据库查询映射区间。功能作者： 罗瑶光
##### 已完善功能：db plsql query设计 select。功能作者： 罗瑶光
##### 已完善功能：读写分离， 读操作全部3级缓存。。功能作者： 罗瑶光
##### 已完善功能：plsql 量子数据库语言去死锁机制。。功能作者： 罗瑶光
##### 已完善功能：德塔 PL/SQL 数据分析语言 定义规范。。功能作者： 罗瑶光
##### 已完善功能：前端angular html页面， 感谢webadmin 开源项目组，本人采用了bootstrap原生js组件。
##### 已完善功能：select功能的表join 函数功能作者： 罗瑶光
##### 已完善功能：select功能的表aggregation limit 函数功能作者： 罗瑶光
##### 已完善功能：semi join功能的表 relation 函数功能作者： 罗瑶光
##### 已完善功能：plsql changeDBPath/ setRoot函数；功能作者： 罗瑶光
##### 已完善功能：plsql create table函数；功能作者： 罗瑶光
##### 已完善功能：db plsql query设计 update 功能作者： 罗瑶光2019-01-22
##### 已完善功能：db plsql query设计 insert  功能作者： 罗瑶光2019-01-23 备注：没有检查，
##### 已完善功能：db plsql query设计 delete  功能作者： 罗瑶光2019-01-24 备注：已经检查，
##### 已完善功能：1 update rest接口涉及的缓存更新 功能作者：罗瑶光2019-01-24
##### 已完善功能：2 delete rest接口涉及的缓存更新 功能作者：罗瑶光2019-01-24
##### 已完善功能：3 insert rest接口涉及的缓存更新 功能作者：罗瑶光2019-01-24

##### 预先搁置功能：table函数 的primary key， unique key函数管理系统设计。；（属于2级优先，先搁置）
###### 准备完善功能：之前因为没有内存映射区间，所以操作都是硬盘方式，现在有了3， 4级缓存，所有读走2,3,4级buffer，所以之前导致的硬盘修改写操作都要进行缓存更新，我需要完成这个功能，同时我还做了网页cache，这些相关的地方也要及时更新。
###### 准备完善功能4 rest get file cache refresh

###### 未完善功能：基于sort key 前序treeMap 之后排序功能设计
###### 未完善功能：基于sort key 后序treeMap
###### 未完善功能：update 的 nest join 函数
###### 未完善功能：引擎算法优化
###### 未完善功能：序列化堆2分搜索加速
###### 未完善功能：jvmsets bit优化
###### 未完善功能：selet where in 函数
###### 未完善功能：写错误的rollback 函数
###### 未完善功能：binlog 函数 数据恢复
###### 未完善功能：变量名称编程规范整体整理。
###### 未完善功能：消息队列进行区域链接热备恢复。
###### 未完善功能：研发更多实用的函数计划。
###### 未完善功能：所有错误日志属性分级格式化记录。
###### 未完善功能：企业级或者商业版进行sonar认证

## 德塔 PL/SQL 数据分析语言 说明文档。


### Deta plsql 语法:
##### setRoot:[path];
##### baseName:[baseName];
##### tableName:[tableName]'[operation];
##### getCulumns:[difinition1]:[difinition2]:[difinition3]:[difinition4]:[difinition5]:......;
##### culumnName:[culumnName]:[dataType];
##### changeCulumnName:[newCulumnName]:[oldCulumnName];
##### culumnValue:[culumnName]:[culumnValue];
##### condition:[operation]:[difinition1]:[difinition2]:[difinition3]:...;
##### join:[tableName];
##### relation[operation]:[difinition1]:[difinition2]:[difinition3]:...;
##### aggregate[operation]:[difinition1]:[difinition2]:[difinition3]:...;



#### 1 select with semi join 的一个Deta PLSQL 真实例子 
###### setRoot:C:/DetaDB;
###### baseName:backend;
###### tableName:usr:select;
###### condition:or:u_id|<=|3:u_id|>|7;
###### condition:and:u_email|!equal|321:u_name|!equal|123;
###### getCulumns:u_id|as|detaId:u_email|as|detaEmail;
###### join:backend:usrToken;
###### condition:and:u_level|equal|low;
###### getCulumns:u_id|as|sId:u_level:u_password|as|SSID;
###### relation:and:detaId|==|sId;
###### aggregation:limit:0|~|1;

#### 1 select 例子
##### tableName:test:select;
##### condition:or:testCulumn1|<|20:testCulumn2|==|fire;
##### condition:and:testCulumn1|>|100:testCulumn2|==|fire;



#### 2 select join 例子
##### tableName:utest:select;
##### condition:or:testCulumn1|<|20:testCulumn2|==|fire;
##### condition:and:testCulumn1|>|100:testCulumn2|==|fire;
##### join:stest;
##### relation:or:uid|==|sid:ussd|==|sssd;
##### relation:and:utoken|=!|stoken:umap|==|smap;



#### 2.1 select join 复杂例子

##### tableName:utest:select;
##### condition:or:utestCulumn1|<|20:utestCulumn2|==|fire;
##### condition:and:utestCulumn1|>|100:utestCulumn2|==|fire;
##### getCulumns:utestCulumn1|as|uid::utestCulumn2|as|ussd:utoken:umap;
##### join:backend:stest;
##### condition:and:stestCulumn1|>|100:stestCulumn2|==|fire;
##### getCulumns:stestCulumn1|as|sid|:stestCulumn2|as|sssd:stoken:smap;
##### relation:or:uid|==|sid:ussd|==|sssd;
##### relation:and:utoken|=!|stoken:umap|==|smap;
##### aggregation:limit:2|~|10;



#### 3 insert 例子
##### tableName:test:insert;
##### culumnValue:date:19850525;
##### culumnValue:date1:19850526;
##### culumnValue:date2:19850527;
##### culumnValue:date3:19850528;
##### culumnValue:date4:19850529;



#### 4 update 例子

##### tableName:test:update;
##### condition:or:testCulumn1|<|20:testCulumn2|==|fire;
##### condition:and:testCulumn1|>|100:testCulumn2|==|fire;
##### culumnValue:date:19850525;
##### culumnValue:date1:19850526;



#### 4.1 update 复杂例子
##### tableName:test:update;
##### culumnValue:date:19850525;
##### culumnValue:date1:19850526;
##### condition:or:testCulumn1|<|20:testCulumn2|==|fire;
##### condition:and:testCulumn1|>|100:testCulumn2|==|fire;
##### tableName:utest:nest;
##### condition:and:uCulumn3|<|20;
##### relation:and:testCulumn1|==|uCulumn1:testCulumn2|!=|uCulumn2;




#### 5 delete 例子
##### tableName:test:delete;
##### condition:or:testCulumn1|<|20:testCulumn2|==|fire;
##### condition:and:testCulumn1|>|100:testCulumn2|==|fire;



#### 6 create 例子
##### tableName:test:create;
##### culumnName:pk:culumn1:string;
##### culumnName:uk:culumn1:long;
##### culumnName:uk:culumn1:obj;
##### culumnName:nk:culumn1:double;



#### 7 drop 例子
##### tableName:test:drop;



#### 8 change 例子
##### tableName:test:change;
##### changeCulumnName:oldCulumnName:newCulumnName;

