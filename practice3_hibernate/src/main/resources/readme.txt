多表联合查询
1.交叉连接(笛卡尔积)
    select * from A，B
2.内连接（两表交集）
    select * from A inner join B on A.id = B.aId
    2.1隐式内连接
        select * from A,B where A.id = B.aId
3.外连接
    3.1左外连接
        select * from A left outer join B on A.id = B.aId
    3.2右外连接
        select * from A right outer join B on A.id = B.aId



Hibernate提供的多表联合查询方式
1.交叉连接
2.内连接
    2.1隐式内连接
    2.2迫切内连接
3.外连接
    3.1左外连接
    3.2迫切左外连接
    3.3右外连接