package zv2.com.cn.entity;

import javax.persistence.*;

/**
 * 电台
 * @author lb
 * @date 2019/5/10
 */
@Entity
@Table(name = "pub_radio", schema = "hibernate_practice1", catalog = "")
public class Radio {
    private String id;
    private String name;
    private String title;
    private String tag;
    private Double hz;
    private String owner;
    private String address;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "tag", nullable = true, length = 255)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "hz", nullable = true, precision = 0)
    public Double getHz() {
        return hz;
    }

    public void setHz(Double hz) {
        this.hz = hz;
    }

    @Basic
    @Column(name = "owner", nullable = true, length = 255)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Radio radio = (Radio) o;

        if (id != null ? !id.equals(radio.id) : radio.id != null) return false;
        if (name != null ? !name.equals(radio.name) : radio.name != null) return false;
        if (title != null ? !title.equals(radio.title) : radio.title != null) return false;
        if (tag != null ? !tag.equals(radio.tag) : radio.tag != null) return false;
        if (hz != null ? !hz.equals(radio.hz) : radio.hz != null) return false;
        if (owner != null ? !owner.equals(radio.owner) : radio.owner != null) return false;
        if (address != null ? !address.equals(radio.address) : radio.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (hz != null ? hz.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
