# 已完善功能：单表增删改查
# 已完善功能：操作权限
# 已完善功能：超时
# 已完善功能：hash存储
# 已完善功能：并发
# 已完善功能：2级缓存
# 已完善功能：采用VPC/VPCS（作者罗瑶光）架构
# 已完善功能：采用纯静态类接口
# 已完善功能：采用deta HTTP 服务器做 ADMIN
# 已完善功能：REST JSON 做第三方 request 扩展。
# 已完善功能：增加过滤，同源，限制机制

## 未完善功能：表join
## 未完善功能：db query设计
## 未完善功能：
## 未完善功能：
## 未完善功能：
## 未完善功能：

### 德塔 PL/SQL 数据分析语言 说明文档。

##### baseName:[baseName];
##### tableName:[tableName]:[operation];
##### culumnName:[culumnName]:[dataType];
##### changeCulumnName:[newCulumnName]:[oldCulumnName];
##### culumnValue:[culumnName]:[culumnValue];
##### condition:[operation]:[difinition1]:[difinition2]:[difinition3]:...;
##### join:[tableName];
##### relation[operation]:[difinition1]:[difinition2]:[difinition3]:...;

#### 1 select 例子
##### tableName:test:select;
##### condition:or:testCulumn1<20:testCulumn2==fire;
##### condition:and:testCulumn1>100:testCulumn2==fire;

#### 2 select join 例子
##### tableName:utest:select;
##### condition:or:testCulumn1<20:testCulumn2==fire;
##### condition:and:testCulumn1>100:testCulumn2==fire;
##### join:stest;
##### condition:or:uid==sid:ussd==sssd;
##### condition:and:utoken=!stoken:umap==smap;

#### 2.1 select join 复杂例子
##### tableName:utest:select;
##### condition:or:utestCulumn1<20:utestCulumn2==fire;
##### condition:and:utestCulumn1>100:utestCulumn2==fire;
##### join:stest;
##### condition:and:stestCulumn1>100:stestCulumn2==fire;
##### relation:or:uid==sid:ussd==sssd;
##### relation:and:utoken=!stoken:umap==smap;
##### asggregate:limit:2:10;


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
##### condition:or:testCulumn1<20:testCulumn2==fire;
##### condition:and:testCulumn1>100:testCulumn2==fire;

#### 4.1 update 复杂例子
##### tableName:test:update;
##### culumnValue:date:19850525;
##### culumnValue:date1:19850526;
##### condition:or:testCulumn1<20:testCulumn2==fire;
##### condition:and:testCulumn1>100:testCulumn2==fire;
##### tableName:utest:nest;
##### condition:and:uCulumn3<20;
##### relation:and:testCulumn1==uCulumn1:testCulumn2!=uCulumn2;

#### 5 delete 例子
##### tableName:test:delete;
##### condition:or:testCulumn1<20:testCulumn2==fire;
##### condition:and:testCulumn1>100:testCulumn2==fire;

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




