package online.cangjie.core.service.product;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.Sku;

public interface SkuService {
    public Pagination query(Sku sku, Integer pageNo);

    public Integer updateMPSUD(Sku sku);
}
