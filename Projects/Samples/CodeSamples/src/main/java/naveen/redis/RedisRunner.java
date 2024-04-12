package naveen.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisRunner {
    private static final Logger logger = LoggerFactory.getLogger(RedisRunner.class);
    public static void main(String[] args) {
        try (JedisPool jedisPool = new JedisPool("localhost", 6379)) {

            try (Jedis jedis = jedisPool.getResource()) {
                jedis.set("name", "naveen");
                logger.info(jedis.get("name"));
            }
        }

    }
}
