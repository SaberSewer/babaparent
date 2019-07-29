package online.cangjie.core.dao;

import online.cangjie.bean.Brand;
import online.cangjie.bean.BrandQuery;

import java.util.List;
import java.util.Map;

public interface BrandDao {
    public List<Brand> query(BrandQuery brandQuery);

    public Brand queryOne(Long id);

    public Integer insertOne(Brand brand);

    public Integer insertList(List<Brand> list);

    public Integer updateOne(Brand brand);

    public Integer queryCount(Brand brand);

    public Integer delete(List<Long> list);

    public List<Map> queryByProduct(List<Integer> ids);
}
