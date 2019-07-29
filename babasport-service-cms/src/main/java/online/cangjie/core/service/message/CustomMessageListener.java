package online.cangjie.core.service.message;

import online.cangjie.bean.Product;
import online.cangjie.core.service.cms.CmsService;
import online.cangjie.core.service.staticpage.StaticPageService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.*;

public class CustomMessageListener implements MessageListener {
    @Autowired
    private StaticPageService staticPageService;
    @Autowired
    private CmsService cmsService;

    @Override
    public void onMessage(Message message) {
        try {
            List<Integer> list = (List<Integer>) message.getObjectProperty("ids");
            Iterator<Integer> iterator = list.iterator();

            while (iterator.hasNext()) {
                HashMap<String, Object> hashMap = new HashMap();
                Integer i = iterator.next();
                Product product = cmsService.queryProductByProductId(Long.parseLong("" + i));
                List<Map<String, String>> maps = cmsService.querySkuByProductId(Long.parseLong("" + i));
                hashMap.put("product", product);
                hashMap.put("sku", maps);
                hashMap.put("imgUrl", product.getImgUrl());
                staticPageService.productStaticPage(hashMap, String.valueOf(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
