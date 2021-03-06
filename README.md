# Spider 

什么是爬虫
----
通俗理解：爬虫是一个模拟人类请求网站行为的程序。可以自动请求网页、并将数据抓取下来，然后使用一定的规则提取有价值的数据。

专业介绍：[百度百科](https://baike.baidu.com/item/%E7%BD%91%E7%BB%9C%E7%88%AC%E8%99%AB/5162711?fr=aladdin)。

通用爬虫和聚焦爬虫
---
通用爬虫：通用爬虫是搜索引擎抓取系统（百度、谷歌、搜狗等）的重要组成部分。主要是将互联网上的网页下载到本地，形成一个互联网内容的镜像备份。

聚焦爬虫：是面向特定需求的一种网络爬虫程序，他与通用爬虫的区别在于：聚焦爬虫在实施网页抓取的时候会对内容进行筛选和处理，尽量保证只抓取与需求相关的网页信息。

java爬虫 httpclient htmlunit selenium 比较
---
 * httpclient 
    * httpclient 是 HttpClient 是 Apache Jakarta Common 下的子项目 ，支持常用的各种协议，相对比较底层，很多java项目的互联网编程都是依赖于该包 。

 * htmlunit
	- 相当与一个没有ui的浏览器，本身是对httpclient进行封装，加上其有对于js，css等的加载，很多时候这个是非常必要的。
	- 内置Rhinojs浏览器引擎，没有哪一款浏览器使用该内核。
	- 在HtmlUnit的使用上，发现经常性报内存溢出，js/css渲染错误等各种问题。

 * selenium 
	- selenium相对而言则更暴力，他对于每一个浏览器提供不同的插件（htmlunit的也有），可以完全模仿人对于浏览器操作，在使用selenium时，你真的可以看到一个个页面的打开关闭，看到鼠标的移动，文字的键入，所以用selenium ，只要是人能在操作的，他都能做到，缺点就是速度是最慢的，而且你还得在电脑里安装相应的浏览器，要有专门的驱动程序

 * 如何选择
	- 从开发效率来说，应该是selenium>htmlunit>httpclient (htmlunit 对于js的支持不是特别稳定，有时候也会莫名其妙报错)，但是代码运行的速度则是反过来的，尤其js加载和页面的仿真，慢的真的不是一点半点，所以在可以的情况下，尽量使用数度快的工具吧，比如你就是爬一个静态页面，用httpclient就很好，反之如果js很简单就用htmlunit 或者找到相应的js文件，用js引擎去执行，实在没办法了，再考虑selenium吧



#### 支持哪些浏览器

	browser=webdriver.Chrome()
	browser=webdriver.PhantomJS()
	browser=webdriver.Firefox()
	browser=webdriver.Ie()
	browser=webdriver.Android()
	browser=webdriver.BlackBerry()
	browser=webdriver.Opera()
	//selenium 3.0 以上版本支持
	browser=webdriver.Edge()   #  Microsoft Edge
	browser=webdriver.Safari()
  
 > Tip:
 >> selenium3.0 在2.0 的基础上增加了对win10系统的Edge浏览器和Mac系统Safari浏览器的支持,并且在启动Firefox浏览器时也必须使用浏览器驱动geckodriver.去掉了Selenium RC,因此selenium 3.0的学习核心也是WebDriver.

[selenium家族介绍：https://baijiahao.baidu.com/s?id=1621970935187757113](https://baijiahao.baidu.com/s?id=1621970935187757113)



#### 页面、元素 等待

当你觉得你的定位没有问题，但是却直接报了元素不可见，那你就可以考虑是不是因为程序运行太快或者页面加载太慢造成了元素不可见，那就必须要加等待了，等待元素可见再继续运行程序。

1. 强制等待（sleep）
	* 设置等待最简单的方法就是强制等待，其实就是time.sleep()方法，不管它什么情况，让程序暂停运行一定时间，时间过后继续运行。
    - 不智能，设置的时间太短，元素还没有加载出来，那照样会报错；
    * 设置的时间太长，则会浪费时间，不要小瞧每次几秒的时间，case多了，代码量大了，很多个几秒就会影响整体的运行速度了
    
2. 隐性等待（implicitly_wait()）
	* 隐性等待设置了一个时间，在一段时间内网页是否加载完成，如果完成了，就进行下一步；在设置的时间内没有加载完成，则会报超时加载；
	- 缺点也是不智能，因为随着ajax技术的广泛应用，页面的元素往往都可以时间局部加载，也就是在整个页面没有加载完的时候，可能我们需要的元素已经加载完成了，那就么有必要再等待整个页面的加载，执行进行下一步，而隐性等待满足不了这一点；
	* 另外一点，隐性等待的设置时全局性的，在开头设置过之后，整个的程序运行过程中都会有效，都会等待页面加载完成；不需要每次设置一遍；
	- 我们调用了manage（）接口，这个接口下的操作都是用来控制浏览器本身的。
	* driver.manage().timeouts() 下面还有两个参数 
		* setScriptTimeout() 作用是，在设置规定的时间内，等待异步脚本的执行结束，而不是里面抛出错误。这个在执行javascript脚本的时候可能会使用。
		- pageLoadTimeout() 字面意思就网页加载超时，作用就是在设置规定时间内，等待网页完整加载完成，而不是里面抛出错误。我们知道如果网速慢，或者网页元素太多，例如网页做了大面积渲染，肯定会造成网页加载速度变慢。这个接口的作用就是，来处理这类问题的。
	
3. 显性等待（WebDriverWait）
	* WebDriverWait(driver, 20, 0.5).until(expected_conditions.presence_of_element_located(locator))，selenium中的wait模块的WebDriverWait()方法，配合until或者until_not方法，再辅助以一些判断条件，就可以构成这样一个场景：每经过多少秒就查看一次locator的元素是否可见，如果可见就停止等待，如果不可见就继续等待直到超过规定的时间后，报超时异常；当然也可以判断某元素是否在规定时间内不可见等等的各种场景吧，需要根据你自己实际的场景选择判断条件；
