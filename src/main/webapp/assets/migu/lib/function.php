<?php
/* 
 *常用的功能函数
 *function.php
 * @author xubenhai@cmvideo.cn
 */
//php curl GET 方法
function http_get($url)
{ // 模拟获取内容函数
    $ua = "migucloud://v1.0.0(Linux;v0.01;zh_cn;)-miguweb";
    $curl = curl_init(); // 启动一个CURL会话      
    curl_setopt($curl, CURLOPT_URL, $url); // 要访问的地址                      
    curl_setopt($curl, CURLOPT_USERAGENT, $ua);// 模拟用户使用的浏览器
    curl_setopt($curl, CURLOPT_HTTPGET, 1); // 发送一个常规的GET请求        
    curl_setopt($curl, CURLOPT_TIMEOUT, 10); // 最多10秒 超过10超时      
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1); // 获取的信息以文件流的形式返回
    $output = curl_exec($curl); // 执行操作      
    if (curl_errno($curl)) {
        data_format_json(-100, 'Errno' . curl_error($curl));
    }
    curl_close($curl); // 关闭CURL会话      
    return $output; // 返回数据      
}

function http_post($url, $data = '')
{
    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
    curl_setopt($ch, CURLOPT_TIMEOUT, 10); // 最多10秒 超过10超时     
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(
            'Content-Type: application/json',
            'Content-Length: ' . strlen($data))
    );
    $result = curl_exec($ch);
    curl_close($ch);
    return $result;
}

//json结果返回
function data_format_json($error_code="0",$content=array()){
  $data = array();
  $data['ret'] = $error_code;
  $data['msg'] = $content;
  echo json_encode($data);
  exit;
}

function data_format_categories($data=array(),$level=""){
    if(empty($data)){
      data_format_json(-2,'数据为空');
    }
    
  $data_return = array();
  $data_return['id'] = $data['catalogId'];
  $data_return['parent'] = ($data['catalogId'] == 0) ? '#' : $data['catalogId'];
  $data_return['text'] = $level.$data['name'];
  $data_return['data'] = isset($data['mov_sum']) ? $data['mov_sum'] : 0;
   
   $data_final_return = array();
   $data_final_return[]  = $data_return;
   if(isset($data['child'])){
     $res = get_clild_data($data['child'],$level);
     $data_final_return = array_merge($data_final_return, $res);
   }
    return json_encode($data_final_return);
   
    
    
  }
  
  function get_clild_data($clild=array(),$level=""){
    $total = count($clild);
    $array = array();
    for ($i = 0; $i < $total; $i++) {
      $array[$i]['id'] = $clild[$i]['catalogId'];
      $array[$i]['parent'] = ($clild[$i]['catalogId'] == 0) ? '#' : $clild[$i]['catalogId'];
       $array[$i]['text'] = $level.$clild[$i]['name'];
      $array[$i]['data'] = isset($clild[$i]['mov_sum']) ? $clild[$i]['mov_sum'] : 0;
      if(isset($clild[$i]['child'])){//递归
        $res = get_clild_data($clild[$i]['child'], $level . "");
        $childcount = count($clild[$i]['child']);
         if (($childcount < 6 && $childcount > 0) || $clild[$i]['parent_id'] == "0") {
           $tmp_catalog['id'] = 9998;
           $tmp_catalog['parent'] = $clild[$i]['catalogId'];
           $tmp_catalog['text'] = 'cteate new catalogs';
            $tmp_catalog['data'] = 0;
            $res [] = $tmp_catalog;
         }
         
          $array = array_merge($array, $res);
      }
      
    }
     return $array;
    
  }

