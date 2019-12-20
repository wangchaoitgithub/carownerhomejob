<?php

/*
 * 获取目录分类 仅为示例 因为该接口不能跨域调用  所以需要转发
 * catalog.php
 * @author benhaixu
 * 
 */
//查询所需要的接口 catalog_query 分类树查询
header("Content-Type: text/html; charset=UTF-8");
require_once './lib/function.php';
$interface_arr = array(
    'catalog_query',
    'trans_query_version'
);
//定义访问接口路径
$v2_path = 'http://www.migucloud.com/vod2/v2/';
$t2_path = 'http://www.migucloud.com/vod2/t2/';


$action = isset($_GET['action']) ? $_GET['action'] : 'catalog_query';
$uid = isset($_GET['uid']) ? intval($_GET['uid']) : data_format_json(-2, 'param_error_uid'); //收集uid参数
$token = isset($_GET['token']) ? $_GET['token'] : data_format_json(-2, 'param_error_token'); //收集token参数
if (!in_array($action, $interface_arr)) {//限制访问的方法
  data_format_json(-1, 'illegal access!');
}




if($action == 'catalog_query'){
	//分类树查询
	$url_send = $v2_path . $action."?uid=" . $uid . "&token=".$token;
	$result = http_get($url_send);
	$result_arr = json_decode($result, TRUE);
	
	//获取失败
	if (isset($result_arr['ret']) && $result_arr['ret'] != 0) {
	  data_format_json(-3, $result_arr['msg']);
	}

	$result_return = data_format_categories($result_arr['result']);
	echo $result_return;
	die;

}elseif ($action == 'trans_query_version') {
	//转码模板查询
	$url_send = $t2_path . "template/trans/query"."?uid=" . $uid . "&token=".$token;
	$result = http_get($url_send);
	$result_arr = json_decode($result, TRUE);
	
	$trans_version = array();
    if (!empty($result_arr) && $result_arr['ret'] == 0) {
      foreach ($result_arr['result'] as $key=>$val){
        if($val['vtype'] < 100){
            $trans_version[$key] = $val;
            switch($val['vtype']){
              case 0:
                $desc = "流畅";
                break;
              case 1:
                $desc = "标清";
                break;
              case 2:
                $desc = "高清";
                break;
              case 3:
                $desc = "超清";
                break;
              case 4:
                $desc = "原画质";
                break;
              default :
                $desc = "流畅";
                break;
            }
            $trans_version[$key]['desc'] = $desc;
        }
      }
      data_format_json(0,$trans_version);
    }

}else{
	echo 333;
}

