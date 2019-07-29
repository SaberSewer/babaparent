package online.cangjie.bean;

import java.io.Serializable;
import java.math.BigInteger;

public class Brand implements Serializable {
    private BigInteger id;
    private String name;
    private String description;
    private String imgUrl;
    private String webSite;
    private Integer sort;
    private Integer isDisplay;


    public Brand() {
    }

    public Brand(BigInteger id, String name, String description, String img_url, String web_site, Integer sort, Integer isDisplay) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = img_url;
        this.webSite = web_site;
        this.sort = sort;
        this.isDisplay = isDisplay;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return imgUrl;
    }

    public void setImg_url(String img_url) {
        this.imgUrl = img_url;
    }

    public String getWeb_site() {
        return webSite;
    }

    public void setWeb_site(String web_site) {
        this.webSite = web_site;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }


    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img_url='" + imgUrl + '\'' +
                ", web_site='" + webSite + '\'' +
                ", sort=" + sort +
                ", isDisplay=" + isDisplay +
                '}';
    }
}
