package online.cangjie.core.service;

import online.cangjie.bean.Buyer;
import online.cangjie.core.dao.BuyerDao;
import online.cangjie.core.service.cob.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("buyerService")
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private BuyerDao buyerDao;

    @Override
    public Buyer queryUserByUsernameAndId(Buyer buyer) {
        return buyerDao.queryUserByUsernameAndPassword(buyer);
    }

}
