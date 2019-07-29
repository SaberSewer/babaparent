package online.cangjie.core.service.solr;

import online.cangjie.bean.BuyerCart;

public interface BuyerCartService {
    public BuyerCart queryProdctBySkuId(Long[] skuIds, Integer[] counts);

}
