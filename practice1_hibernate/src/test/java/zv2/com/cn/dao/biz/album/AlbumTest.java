package zv2.com.cn.dao.biz.album;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import zv2.com.cn.entity.biz.album.Album;
import zv2.com.cn.utils.HibernateUtils;

import java.math.BigDecimal;

/**
 * @author liubao
 * @date 2019/4/25 23:41
 */
public class AlbumTest {

    /**
     * 持久态对象拥有自动更新数据库的能力
     * 依赖于hibernate的一级缓存
     */

    /**
     * select-before-update="true"
     * 更新前检查是否改变，即在更新之前先查询
     */
    @Test
    public void updateAfterCheck() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Album album = new Album();
        album.setId(2);
        album.setName("light music");
        album.setAuthor("high");
        album.setPrice(new BigDecimal(250));
        session.update(album);
        transaction.commit();
        session.close();
    }

    /**
     * update当根据id找不到记录时，须设置id的unsaved-value=指定值
     */
    @Test
    public void save() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Album album = new Album();
        album.setId(-1);
        album.setName("light music");
        album.setAuthor("high");
        album.setPrice(new BigDecimal(250));
        session.saveOrUpdate(album);
        transaction.commit();
        session.close();
    }

    public void update(Integer id, Album album) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Album album1 = (Album) session.get(Album.class, id);
        album1.setName(album.getName());
        album1.setAuthor(album.getAuthor());
        album1.setPrice(album.getPrice());
        transaction.commit();
        session.close();
    }

    public void findAfterSave(Album album) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Integer id = (Integer) session.save(album);
        Album album1 = (Album) session.get(Album.class, id);
        System.out.println(album1.getName()+"-"+album1.getAuthor()+"-"+album1.getPrice());
        Album album2 = (Album) session.get(Album.class, 2);
        System.out.println(album2.getName()+"-"+album2.getAuthor()+"-"+album2.getPrice());
        Album album3 = (Album) session.get(Album.class, 2);
        System.out.println(album3.getName()+"-"+album3.getAuthor()+"-"+album3.getPrice());
        transaction.commit();
        session.close();
    }

    /**
     * 一级缓存和快照区
     * session接口持有Map集合，其中
     * key是一级缓存区，value是快照区
     * 事务提交时会比对一级缓存区和快照区数据.
     * ======================================
     * session一级缓存区操作管理
     * clear清空一级缓存区，evict将指定对象从一级缓存区中清除
     * flush刷出一级缓存区，实际上就是指定发送SQL的时间（相较于commit提前）
     * refresh将快照区数据覆盖一级缓存区
     */

    @Test
    public void update() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Album album = (Album) session.get(Album.class, 2);
//        album.setName("light music");
        album.setName("acg music");
        session.setFlushMode(FlushMode.AUTO);
//        session.flush();
        session.refresh(album);
        album.setPrice(new BigDecimal(100));
        transaction.commit();
        session.close();
    }

    @Test
    public void find() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Album album = (Album) session.get(Album.class, 1);
        System.out.println(album.getName()+"-"+album.getAuthor()+"-"+album.getPrice());
        Album album1 = (Album) session.get(Album.class, 2);
        System.out.println(album1.getName()+"-"+album1.getAuthor()+"-"+album1.getPrice());
//        session.clear();
        session.evict(album);
        Album album2 = (Album) session.get(Album.class, 1);
        System.out.println(album2.getName()+"-"+album2.getAuthor()+"-"+album2.getPrice());
        Album album3 = (Album) session.get(Album.class, 2);
        System.out.println(album3.getName()+"-"+album3.getAuthor()+"-"+album3.getPrice());
        transaction.commit();
        session.close();
    }

    @Test
    public void updateAfterFind() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Album album = (Album) session.get(Album.class, 2);
        album.setName("acg music");
        album.setAuthor("初音未来");
        album.setPrice(new BigDecimal(599));
        transaction.commit();
        session.close();
    }

    @Test
    public void testUpdate() {
        Album album = new Album();
        album.setName("pop music");
        album.setAuthor("张靓颖");
        album.setPrice(new BigDecimal(299));
        update(1, album);
    }

    @Test
    public void testFindAfterSave() {
        Album album = new Album();
        album.setName("pop music");
        album.setAuthor("张靓颖");
        album.setPrice(new BigDecimal(199));
        findAfterSave(album);
    }
}
