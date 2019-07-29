package online.cangjie.core.service.product;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.Brand;

import java.util.List;

public interface BrandService {
    public Pagination query(Brand brand, Integer pageNo);

    public Brand queryOne(Long id);

    public Integer insertOne(Brand brand);

    public Integer updateOne(Brand brand);

    public Integer delete(List<Long> list);
}
