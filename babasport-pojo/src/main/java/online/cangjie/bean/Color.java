package online.cangjie.bean;

import java.io.Serializable;

public class Color implements Serializable {
    private Long id;
    private String name;
    private Long parentId;
    private String imgUrl;

    public Color() {
    }

    public Color(Long id, String name, Long parentId, String imgUrl) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Color{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
