package online.cangjie.bean;

import java.io.Serializable;
import java.util.List;

public class BuyerCart implements Serializable {
    private List<CartItem> items;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BuyerCart{" +
                "items=" + items +
                '}';
    }
}
