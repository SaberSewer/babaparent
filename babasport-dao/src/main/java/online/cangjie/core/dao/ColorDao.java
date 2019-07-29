package online.cangjie.core.dao;

import online.cangjie.bean.Color;
import online.cangjie.bean.ColorQuery;

import java.util.List;

public interface ColorDao {
    public List<Color> queryAll(ColorQuery colorQuery);

    public String queryColorNameBySkuId(Long skuId);
}
