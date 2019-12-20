<?php

namespace Migu;

use Migu\Http\Client;
use Migu\Http\Error;
use Migu\Storage\RedisUtil;

final class Live {

  private $auth_info = "";

  public function __construct($auth) {
    $this->auth_info = $auth;
  }
  
  public function create_channel(){
	  //收集参数
	  $iput_param = file_get_contents("php://input");
	  $input_data = json_decode($iput_param,TRUE);
	 
	  //参数校验
	  //XXXXXXX
	  
	  //调用接口
	  $url = getConfig('MIGU_HOST')."/l2/live/create_channel?".$this->auth_info;
	  echo $url;
  }
  
}
  