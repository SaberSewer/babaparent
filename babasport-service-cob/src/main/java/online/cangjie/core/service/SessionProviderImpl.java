package online.cangjie.core.service;

import online.cangjie.core.service.cob.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

public class SessionProviderImpl implements SessionProvider {
    @Autowired
    private Jedis jedis;
    private Integer exp = 30;


    public void setExp(Integer exp) {
        this.exp = exp;

    }

    @Override
    public void setAttributeForUserName(String name, String value) {
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
        jedis.set(name + ":USER_NAME", value);
        jedis.expire(name + ":USER_NAME", 60 * exp);
    }

    @Override
    public void setAttributeForCode(String name, String value) {
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
    }

    @Override
    public String getAttibuteForUserName(String name) {
        System.out.println("要查询的CJSESSION"  + name);
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
        String value = jedis.get( name + ":USER_NAME");
        if(null != value){
            jedis.expire(name + ":USER_NAME", 60 * exp);
        }
        return value;
    }

    @Override
    public String getAttibuteForCode(String name) {
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
        return null;
    }
}
