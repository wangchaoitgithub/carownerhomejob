<?php
require_once __DIR__ . '/../src/autoload.php';

use Migu\Auth;
use Migu\Live;


// 用于鉴权验证. http://xxxxxxx

// 初始化签权对象。
$auth = new Auth();
$auth_info = $auth->authorization();
var_dump($auth_info);
$live = new Live($auth_info);
$res = $live->create_channel();

var_dump($res);

