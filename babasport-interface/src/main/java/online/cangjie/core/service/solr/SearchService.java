package online.cangjie.core.service.solr;

import java.util.List;
import java.util.Map;

public interface SearchService {
    public Map<String, Object> searchProduct(String keyWorld, Integer pageNo, Long brandId, String price);

    public void insertProductToSolr(List<Integer> ids, Integer type) throws Exception;

    public List<Map> queryBrandsFromRedis();
}
