package online.cangjie.core.service;

import online.cangjie.bean.Product;
import online.cangjie.bean.Sku;
import online.cangjie.core.dao.ProductDao;
import online.cangjie.core.dao.SkuDao;
import online.cangjie.core.service.cms.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 评论、晒单、广告、静态化
 */
@Service("cmsService")
public class CmsServiceImpl implements CmsService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private SkuDao skuDao;

    @Override
    public Product queryProductByProductId(Long id) {
        return productDao.queryOne(Integer.valueOf(String.valueOf(id)));
    }

    @Override
    public List<Map<String, String>> querySkuByProductId(Long id) {

        return skuDao.querySkuByProductId(id);
    }

    @Override
    public List<Sku> querySkuSizeByProductIdAndColorId(Long productId, Long colorId){
        return  skuDao.querySkuSizeByProductIdAndColorId(productId, colorId);
    }

    @Override
    public Float querySkuPriceByProductIdAndColorIdAndSize(Long productId, Long colorId, String size) {
        return skuDao.querySkuPriceByProductIdAndColorIdAndSize(productId, colorId, size);
    }
}
