package online.cangjie.core.service.solr;

import online.cangjie.bean.BuyerCart;
import online.cangjie.bean.CartItem;
import online.cangjie.bean.Color;
import online.cangjie.bean.Sku;
import online.cangjie.core.dao.ColorDao;
import online.cangjie.core.dao.ProductDao;
import online.cangjie.core.dao.SkuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("buyerCartService")
public class BuyerCartServiceImpl implements BuyerCartService {
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ColorDao colorDao;

    @Override
    public BuyerCart queryProdctBySkuId(Long[] skuIds, Integer[] counts) {
        List<CartItem> cartItems = new ArrayList<>();
        List<Sku> skuList = skuDao.querySkuBySkuIds(skuIds);
        Map<Long, Integer> map = new HashMap<>();
        BuyerCart buyerCart = new BuyerCart();
        for (int i = 0; i < skuIds.length; i++){
            map.put(skuIds[i], counts[i]);
        }
        for (Sku sku : skuList) {
            CartItem cartItem = new CartItem();
            cartItem.setSku(sku);
            if(sku.getStock() <= 0){
                cartItem.setHave(false);
            }
            cartItem.setProduct(productDao.queryProductNameByProductSkuIds(sku.getId()));
            cartItem.setConut(map.get(sku.getId()));
            cartItem.setColor(colorDao.queryColorNameBySkuId(sku.getColorId()));
            cartItems.add(cartItem);
        }
        buyerCart.setItems(cartItems);
        return buyerCart;
    }
}
