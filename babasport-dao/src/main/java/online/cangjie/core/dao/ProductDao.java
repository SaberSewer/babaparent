package online.cangjie.core.dao;

import online.cangjie.bean.Product;
import online.cangjie.bean.ProductQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    public List<Product> queryList(ProductQuery productQuery);

    public Product queryOne(Integer id);

    public Integer queryCount(Product product);

    public Integer insertOne(Product product);

    public Integer updateIsShow(@Param("ids") List<Integer> ids, @Param("type") Integer type);

    public Integer deleteProductByIds(List<Long> ids);

    public Product queryProductNameByProductSkuIds(Long skuId);
}
