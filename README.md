## image_pdf
基于jdk8 fx功能开店的图片转pdf窗体，核心是itext。

## 功能记录
* pdf
	* 单图片或多图片生成pdf

* 图片
	* tif格式转jpg
	
* 文本
	* 文本过滤，支持文本过滤和替换，过滤和替换可以仅支持首个。

* web
	* 请求测试，支持get和post

* excel
	* 选择excel文件，选择分栏和列，列逗号分隔，列顺序为输出文件，输出文件为源文件名后加.txt


## 核心结束
* 架构设计用到集成和封装
* 注解
* 反射 

## 操作记录
2019-01-02：改用新结构设计、实现功能 
2019-09-29：* 优化文本处理，增加过滤只过滤第一个对象，调整整个布局。
			* 增加web功能，post请求参数输入。
			* 导入run jar报错：java.lang.ClassNotFoundException: org.bouncycastle.jce.provider.BouncyCastleProvider，导出普通jar，指定main方法无此问题。
