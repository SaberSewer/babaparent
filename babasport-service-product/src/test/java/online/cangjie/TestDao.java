package online.cangjie;

import online.cangjie.bean.ProductQuery;
import online.cangjie.core.dao.ProductDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})*/
public class TestDao {
/*    @Autowired
    private ProductDao productDao;
    @Autowired
    private SolrServer solrServer;

    @Test
    public void testDao() {
        ProductQuery productQuery = new ProductQuery();
        productQuery.setPageNo(1);
        System.out.println(productQuery);
        System.out.println(productDao.queryList(productQuery));
    }

    @Test
    public void testSolr() throws IOException, SolrServerException {
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "4");
        solrInputDocument.addField("name", "仓颉4");
        solrServer.add(solrInputDocument);
        solrServer.commit();
    }*/

    @Test
    public void testSolrQuery() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://cangjie.online:8080/solr");

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "name_ik:22 and id:5");
        QueryResponse queryResponse = solrServer.query(solrQuery);
        SolrDocumentList doc = queryResponse.getResults();
        System.out.println(doc.getNumFound());

    }
}
