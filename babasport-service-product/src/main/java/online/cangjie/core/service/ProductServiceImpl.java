package online.cangjie.core.service;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.*;
import online.cangjie.core.dao.BrandDao;
import online.cangjie.core.dao.ColorDao;
import online.cangjie.core.dao.ProductDao;
import online.cangjie.core.dao.SkuDao;
import online.cangjie.core.service.product.ProductService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private Jedis jedis;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public Pagination queryListAndPage(Product product, Integer pageNo) {
        ProductQuery productQuery = new ProductQuery();
        StringBuilder build = new StringBuilder();
        productQuery.setPageNo(Pagination.cpn(pageNo));
        productQuery.setPageSize(8);
        if (null != product.getName()) {
            build.append("name=" + product.getName());
            productQuery.setName(product.getName());

        }
        if (null != product.getIsShow()) {
            build.append("&isShow=" + product.getIsShow());
            productQuery.setIsShow(product.getIsShow());
        } else {
            build.append("&isShow=");
        }
        List<Product> list = productDao.queryList(productQuery);
        Pagination pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), productDao.queryCount(product));
        pagination.setList(list);
        pagination.pageView("/product/product_list.do", build.toString());
        return pagination;
    }

    @Override
    public List<Brand> queryAllBrand() {
        return brandDao.query(new BrandQuery());
    }

    @Override
    public List<Color> queryAllColor() {
        return colorDao.queryAll(new ColorQuery());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Integer insertOneProdcut(Product product) throws Exception {
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
        Long product_id = Long.parseLong(String.valueOf(this.jedis.incr("product_id")));
        product.setId(product_id);
        String[] colors = product.getColors().split(",");
        String[] size = product.getSizes().split(",");
        List<Sku> skuList = new ArrayList<>(colors.length * size.length);
        System.out.println(Arrays.toString(size));
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < size.length; j++) {
                Sku sku = new Sku();
                sku.setColorId(Long.parseLong(colors[i]));
                sku.setProductId(product_id);
                sku.setSize(size[j]);
                System.out.println(sku);
                skuList.add(sku);
            }
        }
        System.out.println(skuList);
        productDao.insertOne(product);
        return skuDao.insert(skuList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateProductIsShow(List<Integer> ids, Integer type) throws Exception {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createMapMessage();
                message.setObjectProperty("ids", ids);
                message.setObjectProperty("type", type);
                return message;
            }
        });
        return productDao.updateIsShow(ids, type);
    }

    @Override
    @Transactional
    public Integer deleteProductByIds(List<Long> ids) throws Exception {
        skuDao.deleteByProductId(ids);
        productDao.deleteProductByIds(ids);
        Iterator<Long> iterator = ids.iterator();
        while (iterator.hasNext()) {
            solrServer.deleteById(String.valueOf(iterator.next()));
            solrServer.commit();
        }
        return ids.size();
    }
}
