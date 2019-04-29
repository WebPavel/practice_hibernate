package zv2.com.cn.entity.usr.visitor;

import zv2.com.cn.entity.pub.room.Room;

import java.util.Date;

/**
 * 游客
 * @author liubao
 * @date 2019/4/28 3:31
 */
public class Visitor {
    private Integer id;
    private String name;
    private String ip;
    private Integer vipRank;
    private Date gmtCreate;
    private Date gmtModified;
    private Room room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getVipRank() {
        return vipRank;
    }

    public void setVipRank(Integer vipRank) {
        this.vipRank = vipRank;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
