<?php

/*
 * 获取配置文件
 * 
 */

function getConfig($key = '') {
  static $configAll = false;
  if (empty($configAll)) {
    $configAll = require_once __DIR__ . '/Config.php';
  }
  if (isset($configAll[$key])) {
    return $configAll[$key];
  } else {
    return array();
  }
}

function miguJsonDecode($json, $assoc = false, $depth = 512) {
  static $jsonErrors = array(
      JSON_ERROR_DEPTH => 'JSON_ERROR_DEPTH - Maximum stack depth exceeded',
      JSON_ERROR_STATE_MISMATCH => 'JSON_ERROR_STATE_MISMATCH - Underflow or the modes mismatch',
      JSON_ERROR_CTRL_CHAR => 'JSON_ERROR_CTRL_CHAR - Unexpected control character found',
      JSON_ERROR_SYNTAX => 'JSON_ERROR_SYNTAX - Syntax error, malformed JSON',
      JSON_ERROR_UTF8 => 'JSON_ERROR_UTF8 - Malformed UTF-8 characters, possibly incorrectly encoded'
  );

  if (empty($json)) {
    return null;
  }
  $data = json_decode($json, $assoc, $depth);
  if (JSON_ERROR_NONE !== json_last_error()) {
    $last = json_last_error();
    throw new \InvalidArgumentException(
    'Unable to parse JSON data: '
    . (isset($jsonErrors[$last]) ? $jsonErrors[$last] : 'Unknown error')
    );
  }

  return $data;
}
/*
 * 校验返回格式
 * 
 */
function verify_return_data($data){
  if(is_array($data)){
    if(isset($data['ret']) && $data['ret'] == 0){
      return TRUE;
    }else{
      return FALSE;
    }
  }else{
    return FALSE;
  }
} 
