package zv2.com.cn.test;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zv2.com.cn.entity.pub.room.Room;
import zv2.com.cn.entity.usr.visitor.Visitor;
import zv2.com.cn.utils.HibernateUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 多表联合查询
 * @author lb
 * @date 2019/5/8
 */
public class RoomVisitorTest2 {
    private static final Logger LOG = LoggerFactory.getLogger(RoomVisitorTest2.class);
    /**
     * 内连接
     */
    @Test
    public void test1() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list = session.createQuery("from Room r inner join r.visitors").list();
        for (Object[] os : list) {
            System.out.println(Arrays.toString(os));
        }
        // 迫切内连接
        List<Room> rooms = session.createQuery("select distinct r from Room r inner join fetch r.visitors").list();
        for (Room room : rooms) {
            System.out.println("6-"+room.getName()+","+room.getOwner()+","+room.getAddress()+","+room.getVisitors());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 聚集函数
     */
    @Test
    public void test2() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Long count = (Long) session.createQuery("select count(*) from Room r").uniqueResult();
        System.out.println(count);
        transaction.commit();
        session.close();
    }

    /**
     * 命名查询
     */
    @Test
    public void test3() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = session.getNamedQuery("findAll").list();
        for (Room room : rooms) {
            System.out.println("7-"+room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }
    /**
     * 离线条件查询(*****)
     */
    @Test
    public void test4() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Room.class);
        detachedCriteria.add(Restrictions.eq("owner", "冯提莫"));
        detachedCriteria.add(Restrictions.eq("rank", 1));
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = detachedCriteria.getExecutableCriteria(session).list();
        for (Room room : rooms) {
            System.out.println("8-"+room.getName()+","+room.getOwner()+","+room.getAddress());
        }
        transaction.commit();
        session.close();
    }
    /**
     * 抓取策略
     */
    @Test
    public void test5() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        // 延迟检索
        Room room = (Room) session.load(Room.class, 1); // 代理对象
//        System.out.println("9-"+room.getName()+","+room.getOwner()+","+room.getAddress());
        // 初始化代理对象
        if (!Hibernate.isInitialized(room)) {
            LOG.info("hasn't initialized");
            Hibernate.initialize(room);
        }
        transaction.commit();
        session.close();
    }
    /**
     * fetch="join"发送SQL迫切左外连接查询两表
     * fetch="join"时lazy失效
     */
    @Test
    public void test6() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = (Room) session.get(Room.class, 1);
        System.out.println(room.getName()+","+room.getOwner()+","+room.getAddress());
        transaction.commit();
        session.close();
    }
    /**
     * 默认值
     * fetch="select",lazy="true"
     */
    @Test
    public void test7() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = (Room) session.get(Room.class, 1); // send SQL1
        System.out.println(room.getVisitors().size()); // send SQL2
        for (Visitor visitor : room.getVisitors()) {
            System.out.println(visitor.getName()+","+visitor.getVipLevel()+","+visitor.getRoom());
        } // send SQL3
        transaction.commit();
        session.close();
    }
    /**
     * 子查询
     * 应用在query接口上
     * fetch="subselect",lazy="true"
     */
    @Test
    public void test8() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = session.createQuery("from Room r").list();
        for (Room room : rooms) {
            System.out.println(room.getVisitors().size());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 一对多多的一方抓取策略，默认值
     * fetch="select" lazy="proxy"
     */
    @Test
    public void test9() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Visitor visitor = (Visitor) session.get(Visitor.class, 1);
        System.out.println(visitor.getName()+","+visitor.getVipLevel());
        System.out.println(",Room[name]="+visitor.getRoom().getName());
        transaction.commit();
        session.close();
    }

    /**
     * 批量抓取
     * 一的一方批量抓取多的一方
     * 在一的一方<set batch-size="3"></set>配置
     */
    @Test
    public void test10() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Room> rooms = session.createQuery("from Room r").list();
        for (Room room : rooms) {
            for (Visitor visitor : room.getVisitors()) {
                System.out.println(visitor.getVipLevel());
            }
        }
        transaction.commit();
        session.close();
    }
    /**
     * 多的一方批量抓取一的一方
     * 在一的一方<class batch-size="3"></class>配置
     */
    @Test
    public void test11() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<Visitor> visitors = session.createQuery("from Visitor v").list();
        for (Visitor visitor : visitors) {
            System.out.println(visitor.getRoom().getAddress());
        }
        transaction.commit();
        session.close();
    }

    /**
     * 丢失更新的产生
     */
    @Test
    public void testTransaction() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = (Room) session.get(Room.class, 3);
        room.setName("菲儿的房间");
        transaction.commit();
        session.close();
    }
    @Test
    public void testTransaction1() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = (Room) session.get(Room.class, 3);
        room.setOwner("辣个女人");
        transaction.commit();
        session.close();
    }

    /**
     * 使用悲观锁解决丢失更新问题
     */
    @Test
    public void testTransaction2() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = (Room) session.get(Room.class, 3, LockMode.UPGRADE);
        room.setName("L菲儿的房间");
        transaction.commit();
        session.close();
    }
    @Test
    public void testTransaction3() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = (Room) session.get(Room.class, 3, LockMode.UPGRADE);
        room.setOwner("辣个女人");
        transaction.commit();
        session.close();
    }
    /**
     * 使用乐观锁解决丢失更新问题
     */
    @Test
    public void testTransaction4() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = (Room) session.get(Room.class, 3);
        room.setName("苏菲儿的房间");
        transaction.commit();
        session.close();
    }
    @Test
    public void testTransaction5() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Room room = (Room) session.get(Room.class, 3);
        room.setOwner("李文娟");
        transaction.commit();
        session.close();
    }

    /**
     * ThreadLocal绑定的session,这个session不允许手动关闭，否则报错
     */
    @Test
    public void testTransaction6() {
        Session session1 = HibernateUtils.openSession();
        Session session2 = HibernateUtils.openSession();
        System.out.println(session1 == session2);
        Session session3 = HibernateUtils.getCurrentSession();
        Session session4 = HibernateUtils.getCurrentSession();
        System.out.println(session3 == session4);
    }
    @Test
    public void testTransaction7() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Room room = new Room();
        room.setAddress("http://www.douyu.com/g_lol/12323");
        room.setRank(1);
        session.save(room);
        transaction.commit();
    }
    /**
     * 事务的特性：
     * 1.原子性
     *  一组操作要么全部成功，要么全部失败
     * 2.一致性
     *  事务执行前后，保持数据的完整性
     * 3.隔离性
     *  3读2写：
     *  3读---
     *      脏读：读取到另一事务未提交数据
     *      不可重复读：读取到另一事务已提交的数据（update），导致数据查询结果不一致
     *      虚读：读取到另一事务已提交的数据（insert），导致数据查询结果不一致
     *  事务隔离级别
     *      未提交读：
     *      已提交读：避免脏读
     *      重复读：避免脏读、不可重复读
     *      串行读：避免读所有问题
     *  2写---
     *      解决丢失更新问题：
     *      悲观锁 select * from 表 where 条件 for update(数据库称之为排它锁)
     *      乐观锁 新增字段version
     * 4.持久性
     */
}
