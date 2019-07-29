package online.cangjie.core.dao;

import online.cangjie.bean.Buyer;

public interface BuyerDao {
    public Buyer queryUserByUsernameAndPassword(Buyer buyer);
}
