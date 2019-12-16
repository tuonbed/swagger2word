20191216更新
当存在未在controller设置@Api，但是在各个具体接口里@ApiOperation(tags =的情况。此类情况以前的类似这些接口导不出来，现在修改为取得具体接口时会再去判断tags是否存在，如果不存在，将创建tags。避免接口丢失。

在原有https://github.com/JMCuixy/swagger2word的基础上，实现以下功能。
1. 展示和下载，可同时将多个应用整合在一起。swagger.urls 多个应用地址以;隔开，同原版一样，可以使用配置或参数形式均可。
2. 添加swagger.name，或者参数传进来充当标题或者下载的word文件名。
3. 带有序号更好区分。根据应用一级标题，接口标题二级标题，具体接口三级标题设置文档的序号。如 1.1.1 ，1.1.2等。
4. tags合并。如果某个接口在多个tags合并的话更方便，及定义某controller类接口名称为A，在其他controller里某些方法tags指定为A，B，那么
在A和B的tags里，都会将该接口展示出来。并且根据tags进行排序，避免tags自定义此类情况，同一tags不在一起的情况。
5. 增加每个应用的内容，标题，说明，版本，作者。


### 使用步骤（Google Chrome）
1. 
    - 修改 application.yml 文件的<strong> swagger.url </strong>为Swagger Json资源的url地址。
    - 1.4.1 版本后，json 资源的地址可以通过 url 传递，例如：http://127.0.0.1:8080/toWord?url=https://petstore.swagger.io/v2/swagger.json
    - 如果工程内和 url 都配置了资源地址，以 url 上的方案为准。   
2. 服务启动后：访问 http://host(主机):port(端口)/toWord，etc：http://127.0.0.1:8080/toWord  
3. 可以看到网页上生成的类似 word 文档的页面，右键另存为 xxx.doc 文件即可。
4. 1.4.2 版本后，使用 http://host:port/downloadWord?url=url 可一键下载doc文件，例如: http://127.0.0.1:8080/downloadWord?url=https://petstore.swagger.io/v2/swagger.json

#### 版本： SwaggerToWord 1.0 （2018-01-18）
1. 一个Swagger API 文档转 Word 文档的工具项目 
2. 项目想法和说明可以参考：[http://www.cnblogs.com/jmcui/p/8298823.html](http://www.cnblogs.com/jmcui/p/8298823.html)

#### 版本：SwaggerToWord 1.1 (2018-02-11)
1. 替换 HttpClient 工具类以适配更多的Restful服务。
2. 把 json 示例文件替换成官方的示例文件。
3. 更改写死的模板。让生成的 word 的内容都从 Swagger api 中来。

#### 版本：SwaggerToWord 1.2 (2018-06-21)
1. 引入了 Spring 的 RestTemplate 取代 HttpClients 以支持更多的 Restful 请求。
2. 命名规范以及增加异常处理，对于无法处理的HTTP请求返回空字符串。
3. 修改之前导入data.josn的方式，变成 restTemplate.getForObject("SwaggerJson的url地址",Map.class) 的动态获取方式。

#### 版本：SwaggerToWord 1.3 (2019-06-12)
1. Spring 框架向 SpringBoot 升级。
2. thymeleaf 取代 jsp模板。

#### 版本：SwaggerToWord 1.4 (2019-08-02)
1. 取消 HttpClient 的请求方式去获得返回值，改由从 Swagger Json 文件中直接读取  
2. 针对 application/json 请求方式的入参做渲染     
3. 对于文字过多导致 HTML table 变形做适配   
4. 真诚感谢 [fpzhan](https://github.com/fpzhan)  的代码贡献。

##### 版本: SwaggerToWord 1.4.1 (2019-09-25)
1. 修复当请求参数为@RequestBody 时，参数类型显示不正确问题。
2. 新增直接从请求路径中获取 Swagger JSON,多项目下API文档生成。
3. 解决中文乱码问题。
4. 真诚感谢 [NealLemon](https://github.com/NealLemon) 的代码贡献。


##### 版本: SwaggerToWord 1.4.2 (2019-10-11)
1. 增加一键下载doc文件文件的方式。
2. 真诚感谢 [benwudan](https://github.com/benwudan) 的想法和代码贡献。
