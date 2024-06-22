package naveen.redis;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/*
Documentation
https://learn.microsoft.com/en-us/azure/azure-cache-for-redis/cache-java-get-started?tabs=bash
 */
public class AzureRedis {
    private String hostName = System.getenv("REDISCACHEHOSTNAME");
    private String primaryKey = System.getenv("REDISCACHEKEY");
    public AzureRedis(){
        System.out.println(hostName);
        System.out.println(primaryKey);
        JedisPool pool = new JedisPool(new HostAndPort(hostName, 6380),
                DefaultJedisClientConfig.builder()
                        .password(primaryKey)
                        .ssl(true)
                        .build());
        Jedis resource = pool.getResource();
        resource.set("name", "naveen");
        System.out.println(resource.get("name"));
    }
}
