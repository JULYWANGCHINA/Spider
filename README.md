# Spider Examples

Selenium  简介、使用的各种示例，以最简单、最实用为标准

 什么是selenium
-----
这个最早是基于firfox的自动化测试软件，现在也支持其他的浏览器，比如ie，chrome 等，同时提供了多语言的接口，如python，java 等，其功能是完全模仿浏览器行为，对于爬虫而言是最后的利器。

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
