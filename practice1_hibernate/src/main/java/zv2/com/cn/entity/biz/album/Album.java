package zv2.com.cn.entity.biz.album;

import java.math.BigDecimal;

/**
 * @author liubao
 * @date 2019/4/25 21:11
 */
public class Album {
    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
