package online.cangjie.bean;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    /**
     * ID或商品编号
     */
    private Long id;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 重量 单位:克
     */
    private Float weight;

    /**
     * 是否新品:0:旧品,1:新品
     */
    private Integer isNew;

    /**
     * 是否热销:0,否 1:是
     */
    private Integer isHot;

    /**
     * 推荐 1推荐 0 不推荐
     */
    private Integer isCommend;

    /**
     * 上下架:0否 1是
     */
    private Integer isShow;

    /**
     * 商品图片集
     */
    private String imgUrl;

    /**
     * 是否删除:0删除,1,没删除
     */
    private Integer isDel;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 包装清单
     */
    private String packageList;

    /**
     * 颜色集
     */
    private String colors;

    /**
     * 尺寸集
     */
    private String sizes;

    /**
     * 添加时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsCommend() {
        return isCommend;
    }

    public void setIsCommend(Integer isCommend) {
        this.isCommend = isCommend;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String[] getImgUrl() {
        return imgUrl.split(",");
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageList() {
        return packageList;
    }

    public void setPackageList(String packageList) {
        this.packageList = packageList;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", isCommend=" + isCommend +
                ", isShow=" + isShow +
                ", imgUrl='" + imgUrl + '\'' +
                ", isDel=" + isDel +
                ", description='" + description + '\'' +
                ", packageList='" + packageList + '\'' +
                ", colors='" + colors + '\'' +
                ", sizes='" + sizes + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}