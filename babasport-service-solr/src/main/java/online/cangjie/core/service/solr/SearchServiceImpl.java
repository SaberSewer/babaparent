package online.cangjie.core.service.solr;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.Product;
import online.cangjie.bean.ProductQuery;
import online.cangjie.core.dao.ProductDao;
import online.cangjie.core.dao.SkuDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service("searchService")
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private Jedis jedis;
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private ProductDao productDao;


    @Override
    public Map<String, Object> searchProduct(String keyWorld, Integer pageNo, Long brandId, String price) {
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
        //最终返回Map
        Map<String, Object> hashMap = new HashMap<>();
        //保存Product
        List<Map<String, Object>> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        StringBuilder solrBuilder = new StringBuilder();
        ProductQuery productQuery = new ProductQuery();
        productQuery.setPageNo(Pagination.cpn(pageNo));
        productQuery.setPageSize(12);
        builder.append("keyWorld=").append(keyWorld);
        solrBuilder.append("name_ik:" + keyWorld);
        Long numFound = null;
        try {
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.set("q", solrBuilder.toString());
            //条件
            if (price != null && !price.equals("")) {
                String[] prices = null;
                prices = price.split("-");
                builder.append("&price=" + price);
                solrQuery.addFilterQuery("price:[" + prices[0] + "TO" + prices[1] + "]");
            }
            if (brandId != null) {
                builder.append("&brandId=" + brandId);
                builder.append("and brandId = " + brandId);
                solrQuery.addFilterQuery("brandId:" + brandId);
            }
            // 分页
            solrQuery.setStart(productQuery.getStartRow());
            solrQuery.setRows(productQuery.getPageSize());
            //高亮
            solrQuery.setHighlight(true);
            solrQuery.addHighlightField("name_ik");
            solrQuery.setHighlightSimplePre("<span style='color:red;'>");
            solrQuery.setHighlightSimplePost("</span>");
            QueryResponse queryResponse = solrServer.query(solrQuery);
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            SolrDocumentList results = queryResponse.getResults();
            numFound = results.getNumFound();
            for (SolrDocument doc : results) {
                Map<String, Object> map = new HashMap<>();
                //商品信息
                map.put("id", doc.get("id"));
                map.put("name", highlighting.get(map.get("id")).get("name_ik").get(0));
                map.put("price", doc.get("price"));
                map.put("brandId", doc.get("brandId"));
                map.put("imgUrl", doc.get("url"));
                list.add(map);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        //分页返回
        Pagination pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), Integer.parseInt(String.valueOf(numFound)), list);
        pagination.pageView("list?", builder.toString());
        hashMap.put("pagination", pagination);
        return hashMap;
    }

    public void insertProductToSolr(List<Integer> ids, Integer type) throws Exception {
        Iterator<Integer> iterator = ids.iterator();
        while (iterator.hasNext()) {
            if (type == 1) {
                Integer i = iterator.next();
                Product product = productDao.queryOne(i);
                SolrInputDocument solrInputDocument = new SolrInputDocument();
                //设置Solr的各种属性
                solrInputDocument.addField("id", i);
                solrInputDocument.addField("name_ik", product.getName());
                solrInputDocument.addField("url", product.getImgUrl()[0]);
                solrInputDocument.addField("price", skuDao.queryPriceByProductId(Long.valueOf(i)));
                solrInputDocument.addField("brandId", product.getBrandId());
                solrServer.add(solrInputDocument);
                solrServer.commit();
            } else if (type == 0) {
                solrServer.deleteById(String.valueOf(iterator.next()));
            }
        }
    }

    public List<Map> queryBrandsFromRedis(){
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
        List<Map> list = new ArrayList<>();
        Map<String, String> brand = jedis.hgetAll("brand");
        System.out.println(brand);
        Set<Map.Entry<String, String>> entries = brand.entrySet();
        for (Map.Entry<String, String> entry  : entries) {
            Map<String, String> map = new HashMap<>();
            map.put("name", entry.getValue());
            map.put("id", entry.getKey());
            list.add(map);
        }
        return list;
    }
}
