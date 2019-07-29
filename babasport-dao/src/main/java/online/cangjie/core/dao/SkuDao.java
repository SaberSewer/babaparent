package online.cangjie.core.dao;

import online.cangjie.bean.Sku;
import online.cangjie.bean.SkuQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SkuDao {
    public List<Map<String, String>> queryAll(SkuQuery skuQuery);

    public Float queryPriceByProductId(Long id);

    public Integer queryCount(Sku sku);

    public Integer insert(List<Sku> list);

    public Integer update(Sku sku);

    public Integer deleteByProductId(List<Long> ids);

    public List<Map<String, String>> querySkuByProductId(Long id);

    public List<Sku> querySkuSizeByProductIdAndColorId(@Param("productId") Long productId, @Param("colorId")Long colorId);

    public Float querySkuPriceByProductIdAndColorIdAndSize(@Param("productId") Long productId, @Param("colorId")Long colorId, @Param("size")String size);

    public List<Sku> querySkuBySkuIds(Long[] skuIds);
}
