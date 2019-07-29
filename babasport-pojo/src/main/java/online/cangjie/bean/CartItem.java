package online.cangjie.bean;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Sku sku;
    private Boolean isHave = true;
    private Integer conut = 1;
    private Product product;
    private String color;

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public Boolean getHave() {
        return isHave;
    }

    public void setHave(Boolean have) {
        isHave = have;
    }

    public Integer getConut() {
        return conut;
    }

    public void setConut(Integer conut) {
        this.conut = conut;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "sku=" + sku +
                ", isHave=" + isHave +
                ", conut=" + conut +
                ", product=" + product +
                '}';
    }
}
