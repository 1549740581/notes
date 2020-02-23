## in关键字限制搜索

-   `xxx in:name`：项目名中包含xxx
-   `xxx in:description`：项目描述中包含xxx
-   `xxx in:readme`：项目readme文件中包含xxx

-   组合使用示例：`seckill in:name, readme`



## stars和forks数查询

-   `xxx stars:>=num`：star数大于等于num的项目，例如springboot stars:>=500

-   `xxx forks:>num`：fork数大于num的项目，例如springcloud:>500

-   区间查找：例如点赞数在100到200之间的seckill项目：`seckill stars:100..200`

-   组合使用：查找SpringBoot项目，fork数在100到200之间，star数在500到1000之间：

    `springboot forks:100..200 stars:500..1000`



## awesome加强搜索

awesome系列一般是用来收集学习、工具、书籍类的项目，比较适合新手学习：`awesome redis`



## 高亮显示某一行（块）代码

-   在URL地址后面加上**#Lnum**可以直接将代码中第num行进行高亮显示，例如高亮显示第10行代码：`foo/bar/#L10`
-   在URL地址后面加上**#Ln1-#Ln2**可以直接将代码中n1-n2行的块代码进行高亮显示，例如高亮显示第10行到第25行代码：`foo/bar/#L10-#L25`



## github常用快捷键

-   shift+/，即?能够查看所有快捷键
-   t：Activates the file finder，以列表形式查看所有的文件，url路径变成：..foo/bar/find/master

具体文档：https://help.github.com/en/github/getting-started-with-github/keyboard-shortcuts



## 搜索指定区域活跃用户

例如搜索地区为北京，语言为Java的用户：`location:beijing language:java`