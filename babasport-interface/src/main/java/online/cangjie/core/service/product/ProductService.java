package online.cangjie.core.service.product;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.Brand;
import online.cangjie.bean.Color;
import online.cangjie.bean.Product;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public Pagination queryListAndPage(Product product, Integer pageNo);

    public List<Brand> queryAllBrand();

    public List<Color> queryAllColor();

    public Integer insertOneProdcut(Product product) throws Exception;

    public Integer updateProductIsShow(List<Integer> ids, Integer type) throws IOException, SolrServerException, Exception;

    public Integer deleteProductByIds(List<Long> ids) throws Exception;
}
