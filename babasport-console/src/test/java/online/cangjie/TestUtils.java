package online.cangjie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.UUID;


public class TestUtils {
    @Test
    public void testSpring(){
        Jedis jedis = new Jedis("cangjie.online", 6379);
        jedis.auth("9c28a14ab841494f99a5bde1d0b4a9bb");
        System.out.println(jedis.incr("product_id"));
    }
}
