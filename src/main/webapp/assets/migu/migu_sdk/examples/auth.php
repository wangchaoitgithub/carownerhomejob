<?php
require_once __DIR__ . '/../src/autoload.php';

use Migu\Auth;

// 用于鉴权验证. http://xxxxxxx

// 初始化签权对象。
$auth = new Auth();
$result = $auth->authorization();
var_dump($result);