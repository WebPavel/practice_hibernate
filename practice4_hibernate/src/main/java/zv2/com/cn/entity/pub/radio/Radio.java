package zv2.com.cn.entity.pub.radio;

import zv2.com.cn.entity.biz.host.Host;

/**
 * 电台
 * @author lb
 * @date 2019/5/8
 */
public class Radio {
    private String id;
    private String name;
    private String title;
    private String tag;
    private Double hz;
    private String owner;
    private String address;
    private Host host;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Double getHz() {
        return hz;
    }

    public void setHz(Double hz) {
        this.hz = hz;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
