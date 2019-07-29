package online.cangjie.core.service.cms;

import online.cangjie.bean.Product;
import online.cangjie.bean.Sku;

import java.util.List;
import java.util.Map;

public interface CmsService {
    public Product queryProductByProductId(Long id);

    public List<Map<String, String>> querySkuByProductId(Long id);

    public List<Sku> querySkuSizeByProductIdAndColorId(Long productId, Long colorId);

    public Float querySkuPriceByProductIdAndColorIdAndSize(Long productId, Long colorId, String size);
}
