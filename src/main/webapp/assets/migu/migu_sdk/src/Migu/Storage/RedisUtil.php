<?php

/* 
 * Redis.php
 * redis类
 * @author benhaixu.
 */
namespace Migu\Storage;
class RedisUtil
{
	private static $default_store_time = 600;
	private static $redis = null;


	public function getRedis() {
		if (!RedisUtil::$redis) {
			RedisUtil::$redis = new \Redis();
			RedisUtil::$redis->connect(getConfig("REDIS")['REDIS_URL'], getConfig("REDIS")['REDIS_PORT']);
			//RedisUtil::$redis->auth(REDIS_AUTH);
		}
		return RedisUtil::$redis;
	}

}
