<?php
header("Content-type:text/html;charset=utf-8");
function classLoader($class)
{
    $path = str_replace('\\', DIRECTORY_SEPARATOR, $class);
    
    $file = __DIR__ . '/' . $path . '.php';
    if (file_exists($file)) {
        require_once $file;
    }
}

spl_autoload_register('classLoader');
require_once  __DIR__ . '/Migu/functions.php';
