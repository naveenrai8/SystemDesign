package naveen.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.List;

public class RedisRunner {
    private static final Logger logger = LoggerFactory.getLogger(RedisRunner.class);

    public static void main(String[] args) {
        String channelName = "person-name-channel";
        try (JedisPool jedisPool = new JedisPool("localhost", 6379)) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.set("name", "naveen");
                logger.info(jedis.get("name"));
                for (String val : jedis.zrangeByScore("name-ss", 1, 20)) {
                    logger.info("val: {}", val);
                }
                // Get all elements with scores between 1 and 2, along with their scores
                List<Tuple> elements = jedis.zrangeByScoreWithScores("name-ss", 1, 20);

                // Print the elements
                for (Tuple element : elements) {
                    System.out.println(element.getElement() + ": " + element.getScore());
                }

            }
        }
    }
}
