上传demo 注意事项：
1.该demo只是实现的一种，为了界面美观 增加了很多dom操作 核心的js文件为:js/upload/file_upload.js
为了实现断点续传，采用了cookie。

2.另外 因为上传 需要选择 所属的分类和转码类型 需要调用接口，在js/upload/page_init.js 和 js/upload/catalog.js中已经封装了调用的方法
。
接口分别为：
1.获取分类接口
http://www.test.com/catalog.php?uid=264&token=61e31705b2acad53cba84a36579f0ed4160fd5d7f091422ad43472a489a7da69dafc0cccc8066efb
2.获取转码类型接口
http://www.test.com/catalog.php?uid=264&token=61e31705b2acad53cba84a36579f0ed4160fd5d7f091422ad43472a489a7da69dafc0cccc8066efb&action=trans_query_version




