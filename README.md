##### 已完善功能：单表增删改查 。功能作者： 罗瑶光
##### 已完善功能：操作权限
##### 已完善功能：超时 (token的早期不可逆加密方式思路来自10年前的中科大的筛子加密论文，后本人修改为2次组合加密，并采用mine decode 方式解码，在这感谢中科大) 具体例子：https://blog.csdn.net/weizhiiceboy3/article/details/51955026 等， 感谢csdn
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
##### 已完善功能：select功能的表join 函数
##### 已完善功能：select功能的表aggregation limit 函数
##### 已完善功能：semi join功能的表 relation 函数

##### 正在完善功能：plsql changeDBPath/ setRoot函数；

###### 未完善功能：db plsql query设计 update
###### 未完善功能：db plsql query设计 delete
###### 未完善功能：db plsql query设计 insert
###### 未完善功能：基于sort key 前序treeMap 之后排序功能设计
###### 未完善功能：基于sort key 后序treeMap
###### 未完善功能：引擎算法优化
###### 未完善功能：序列化堆2分搜索加速
###### 未完善功能：jvmsets bit优化
###### 未完善功能：selet where in 函数
###### 未完善功能：
###### 未完善功能：

### 德塔 PL/SQL 数据分析语言 说明文档。



##### setRoot:[path];
##### baseName:[baseName];
##### tableName:[tableName]:[operation];
##### getCulumns:[difinition1]:[difinition2]:[difinition3]:[difinition4]:[difinition5]:......;
##### culumnName:[culumnName]:[dataType];
##### changeCulumnName:[newCulumnName]:[oldCulumnName];
##### culumnValue:[culumnName]:[culumnValue];
##### condition:[operation]:[difinition1]:[difinition2]:[difinition3]:...;
##### join:[tableName];
##### relation[operation]:[difinition1]:[difinition2]:[difinition3]:...;
##### aggregate[operation]:[difinition1]:[difinition2]:[difinition3]:...;



#### 1 select 真实例子
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

##### culumnValue:date:19850525;

##### culumnValue:date1:19850526;

##### condition:or:testCulumn1|<|20:testCulumn2|==|fire;

##### condition:and:testCulumn1|>|100:testCulumn2|==|fire;



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

##### changeCulumnName:newCulumnName:oldCulumnName;

