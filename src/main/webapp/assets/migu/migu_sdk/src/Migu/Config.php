<?php
return array(
    'SDK_VER'=>'1.0.0',//版本号
    'BLOCK_SIZE'=>2*1024*1024,//分片大小
    'MIGU_HOST'=>'http://www.migucloud.com',//源站地址
    'USER_INFO'=>array(//第三方账号信息
        'uname'=>'15800766747',
        'passwd'=>'Xbh27122!@#'
    ),
    'REDIS'=>array(
        'REDIS_URL'=>'127.0.0.1',
        'REDIS_PORT'=>6379,
        'EXPIRES'=>6*3600 //6个小时
    ),
    'FORMAT_VERIFY'=>false,
);
