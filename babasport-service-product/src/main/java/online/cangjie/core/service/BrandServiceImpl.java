package online.cangjie.core.service;

import cn.itcast.common.page.Pagination;
import online.cangjie.bean.Brand;
import online.cangjie.bean.BrandQuery;
import online.cangjie.core.dao.BrandDao;
import online.cangjie.core.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;

@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private Jedis jedis;
    @Override
    public Pagination query(Brand brand, Integer pageNo) {
        BrandQuery brandQuery = new BrandQuery();
        StringBuilder build = new StringBuilder();
        brandQuery.setPageNo(Pagination.cpn(pageNo));
        brandQuery.setPageSize(10);
        if (null != brand.getName()) {
            build.append("name=" + brand.getName());
            brandQuery.setName(brand.getName());

        }
        if (null != brand.getIsDisplay()) {
            build.append("&isDisplay=" + brand.getIsDisplay());
            brandQuery.setIsDisplay(brand.getIsDisplay());
        } else {
            build.append("&isDisplay=" + 1);
            brandQuery.setIsDisplay(1);
        }
        Pagination pagination = new Pagination(brandQuery.getPageNo(), brandQuery.getPageSize(),
                brandDao.queryCount(brand));
        System.out.println(brandDao.query(brandQuery));
        pagination.setList(brandDao.query(brandQuery));
        pagination.pageView("/brand/brand_list.do", build.toString());
        return pagination;
    }

    @Override
    public Brand queryOne(Long id) {
        return brandDao.queryOne(id);
    }

    @Transactional
    @Override
    public Integer insertOne(Brand brand) {
        brandDao.insertOne(brand);
        System.out.println(brand);
        Long i = null;
        if(brand.getIsDisplay() == 1){
            jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
            i = jedis.hset("brand", String.valueOf(brand.getId()), brand.getName());
        }
        return Integer.parseInt(String.valueOf(i));
    }

    @Override
    public Integer updateOne(Brand brand){
        brandDao.updateOne(brand);
        Long i = null;
        if(brand.getIsDisplay() == 1){
            jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
             i = jedis.hset("brand", String.valueOf(brand.getId()), brand.getName());
        } else {
            jedis.hset("brand", String.valueOf(brand.getId()), brand.getName());
        }
        return Integer.parseInt(String.valueOf(i));
    }

    @Override
    @Transactional
    public Integer delete(List<Long> list) {
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
        int i = brandDao.delete(list);
        Iterator<Long> iterator = list.iterator();
        while (iterator.hasNext()){
            jedis.hdel("brand", String.valueOf(iterator.next()));
        }
        return i;
    }


}
