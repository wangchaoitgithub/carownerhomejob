<?php

namespace Migu;

use Migu\Http\Client;
use Migu\Http\Error;
use Migu\Storage\RedisUtil;

final class Auth {

  private $authKey = "MG_PHP_SDK_AUTH_INFO"; //记录鉴权信息的key
  private $getAccessInfo = array();

  public function __construct() {
    
  }

  private function getTokenInfo() {

    //从redis中取鉴权信息
    $class_redis = new RedisUtil();
    $instance_redis = $class_redis->getRedis();
    $authInfo = $instance_redis->get($this->authKey);

    //如果缓存里面存在鉴权信息 直接从缓存里面取
    if (!empty($authInfo)) {
      $redis_result = json_decode($authInfo, TRUE);

      //缓存刷新 当缓存的atoken快过期时 刷新atoken 并且刷新本地缓存
      $flush_flag = time() - $redis_result['result']['timestamp'] >= $redis_result['result']['expired_time'] - 100;
      if ($flush_flag) {
        $flush_result =  $this->flush_token($redis_result, $instance_redis);
		if(verify_return_data($flush_result)){
			  return $flush_result;
		}
      } else {
        return $redis_result;
      }
    }

    //如果缓存里面没有鉴权信息 直接调用咪咕鉴权接口
    $result = $this->getVerifyInfo();
    if ($result['ret'] == 0) {
      $instance_redis->setex($this->authKey, getConfig('REDIS')['EXPIRES'], json_encode($result));
    }
    return $result;
  }

  private function getVerifyInfo() {
    //获取请求的url 和 数据
    $this->getAccessInfo = getConfig('USER_INFO');
    $url = getConfig('MIGU_HOST') . "/a0/user/auth";
    $ret = Client::post($url, json_encode($this->getAccessInfo), array('Content-type' => 'application/json'));

    //处理鉴权结果
    if (!$ret->ok()) {
      $error_class = new Error($url, $ret);
      return array(
          'status' => $error_class->code(),
          'msg' => $error_class->message()
      );
      return new Error($url, $ret);
    }
    $result = $ret->json();
    if (verify_return_data($result)) {//校验通过
      return $result;
    } else {
      return array(
          "ret" => $result['ret'],
          "msg" => $result['msg']
      );
    }
  }

  //刷新当前的atoken 防止atoken过期造成的服务中断
  private function flush_token($auth_info, $instance_redis) {
    $data = array(
        'uid' => $auth_info['result']['user_info']['uid'],
        'ftoken' => $auth_info['result']['ftoken']
    );
    //请求刷新访问令牌接口
    $url = getConfig('MIGU_HOST') . "/a0/user/atoken/flush?";
    $url .= http_build_query($data);
    $ret = Client::get($url);
    //处理刷新访问令牌结果
    if (!$ret->ok()) {
      $error_class = new Error($url, $ret);
      return array(
          'status' => $error_class->code(),
          'msg' => $error_class->message()
      );
    }

    $result = $ret->json();

    if (verify_return_data($result)) {//校验通过
      $auth_info['result']['atoken'] = $result['result']['atoken'];
      $auth_info['result']['ftoken'] = $result['result']['ftoken'];
      $auth_info['result']['expired_time'] = $result['result']['expired_time'];
      $auth_info['result']['timestamp'] = $result['result']['timestamp'];
      $instance_redis->setex($this->authKey, getConfig('REDIS')['EXPIRES'], json_encode($auth_info)); //刷新后存储
      return $auth_info;
    } else {
      return array(
          "ret" => $result['ret'],
          "msg" => $result['msg']
      );
    }
  }

  function authorization($with_ftoken = false) {
    $result = $this->getTokenInfo();
    if ($result['ret'] != 0) {
      return $result;
    }

    $data_output = array(
        'uid' => $result['result']['user_info']['uid'],
        'token' => $result['result']['atoken']
    );
    if ($with_ftoken) {
      $data_output['ftoken'] = $result['result']['ftoken'];
    }
    return http_build_query($data_output);
  }
}
  