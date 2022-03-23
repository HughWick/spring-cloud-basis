# 统一查询接口说明文档

## 参数
|参数名|必选|含义|说明|缺省值|
|:----		|:---|:----- 	|-----   | :----- |
|page 		|是  |页数 	|页数  				 				|1|
|size 		|是  |条数 	| 条数  				 				|20|
|order		|否  |升序or降序 | 可选值：desc-降序、asc-升序   		|DESC|
|sort 		|否  | 排序字段 | 可选值：查询结果的所有key			   |ID|
|startDate 	|否 	|开始日期|  格式:yyyy-MM-DD HH:mm:ss | 无|
|endDate 	|否 	|结束日期|  格式:yyyy-MM-DD HH:mm:ss | 无|


##### 查询参数说明

- 所有返回字段均可作为查询条件

##### 例：
| key  | value| 说明  |
| ------------ |------------------| ------------ |
| serialNo_like  | 123 | 模糊查询所有serialNo中包含123的数据  |
| createBy_name_or  | 张三 |返回createBy或name中的值包含“张三”的数据 |
| serialNo_in  | a,b,c |返回serialNo中的值等于a、b、c的三条数据|


