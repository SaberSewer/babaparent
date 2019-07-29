package online.cangjie.core.service;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.Sku;
import online.cangjie.bean.SkuQuery;
import online.cangjie.core.dao.SkuDao;
import online.cangjie.core.service.product.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuDao skuDao;

    @Override
    public Pagination query(Sku sku, Integer pageNo) {
        SkuQuery skuQuery = new SkuQuery();
        StringBuilder builder = new StringBuilder();
        builder.append("productId=" + sku.getProductId());
        skuQuery.setPageNo(Pagination.cpn(pageNo));
        skuQuery.setProductId(sku.getProductId());
        System.out.println("Service服务器"+skuDao.queryAll(skuQuery));
        Pagination pagination = new Pagination(skuQuery.getPageNo(), skuQuery.getPageSize(), skuDao.queryCount(sku));
        pagination.setList(skuDao.queryAll(skuQuery));
        pagination.pageView("sku_jumpSkuList.do", builder.toString());
        return pagination;
    }

    @Override
    public Integer updateMPSUD(Sku sku) {
        return skuDao.update(sku);
    }
}
