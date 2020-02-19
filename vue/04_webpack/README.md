# 四、webpack



注意：这一章中所有项目源码的node_modules需要重新生成。



## 4.1 模块化开发

### 问题

JS 最开始时，没有模块化开发概念，随着前端项目越来越复杂，非模块化开发带来两个主要问题：

- 多个 JS 文件中全局变量冲突问题
- 使用匿名函数（闭包）解决全局变量冲突问题的同时，又会导致复用性降低

### 原始解决思路

在闭包中使用一个 Object 对象，并返回该 Object 对象。在别的文件中可以通过返回的 Object 对象引入需要的变量、函数等。

### 相关实现

根据原始解决思路，前端开发中已经有相关的实现产品：CommonJS 和 ES6 模块化：

- CommonJS

```javascript
// 导出foo.js文件
module.exports = {
  flag: true,
  test(a, b) {
    return a + b;
  }
};

// 导入foo.js文件
let { flag, test } = require("foo.js");
```

- ES6：引入了两个关键字：import、export

```javascript
// index.html
<script src="./foo.js" type="mudule"></script>
<script src="./bar.js" type="mudule"></script>

// foo.js
let flag = true;
// 直接导出
export let ping = "pong";
function sum(a, b) {
    return a + b;
}
export {flag, sum}

// bar.js
import {flag, sum} from "./foo.js"

if (flag) {
    console.log(sum(1, 2));
}
```

- `export default`：某些情况下，一个模块包含某个功能，并不希望给这个功能命名，而是让导入者可以自己来命名，这个时候就可以使用 export default。注意一个 mudule 中，只能有一个 export default：

```js
// info.js
export default function() {
  console.log("default function()");
}

// other.js导入function()并重新命名为myFunc
import myFunc from "./info.js";
```

- 统一全部导入：如果希望将导出模块中所有的东西全部导入，可以使用通配符：

```javascript
import * as dummy from "./foo.js";

if (dummy.flag) {
  //...
}
```

## 4.2 webpack

### 模块化

webpack 一个核心就是让我们尽可能模块化开发，并且会帮助我们处理模块化的依赖关系。而且不仅仅是 JS 文件，CSS、图片、JSON 文件等在 webpack 中都可以被当做模块来使用。

### 打包

webpack 会将各种资源模块进行打包合并成一个或者多个包（Bundle），并且在打包过程中还会进行处理，例如将 es6 的语法转换成 es5 的语法，将 TypeScript 转换成 JS 等等。

### 安装

webpack 依赖 node.js，node.js 自带的软件包管理工具 npm。

```shell
node -v
v12.16.0

npm -v
6.13.4

// 全局安装
npm install webpack@3.6.0 -g

// 局部安装
// 终端里直接执行webpack命令是全局安装，当在package.json中定义了
// scripts时，其中包含了webpack命令，那么使用的是局部webpack
npm install webpack@3.6.0 --save-dev

webpack -v
3.6.0
```

### webpack 简单使用

- 目录结构：

```txt
1_简单使用
  |
  +- index.html
  |
  +- dist
  |    |
  +	   +- bundle.js(webpack命令生成)
  |
  +- src
       |
       +- main.js（通过CommonJS和es6语法导入mathUtil.js和info.js）
       |
       +- mathUtil.js
       |
       +- info.js
```

- 创建 mathUtil.js 和 info.js，并分别导出：

```javascript
/* mathUtil.js 使用CommonJS导出方式 */
function add(n1, n2) {
  return n1 + n2;
}

module.exports = {
  add
};

/* info.js 使用es6导出方式 */
export let name = "sherman";
export let age = "23";
export let height = "1.76";
```

- main.js 进行导入：

```javascript
// CommonJS导入方式
let { add } = require("./mathUtil.js");
// es6导入方式
import { name, age, height } from "./info.js";

console.log(name, age, height);
console.log(add(1, 2));
```

- 使用 webpack 进行打包：

```shell
webpack src\main.js dist\bundle.js
```

- 在 index.html 中引入 bundle.js：

```html
<!DOCTYPE html>
<html>
  <title>webpack简单使用</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <script src="./dist/bundle.js"></script>
  </body>
</html>
```

### 自动化打包

在上面使用 webpack 进行打包时的用法为：`webpack src dst`，现在希望直接使用一个 webpack 命令就能打包：

- 在项目根目录下使用 node 进行初始化：`node init`
- 项目根路径下，自动创建的配置文件：`webpack.config.js`，在该文件中定义入口和出口：

```javascript
const path = require("path");

module.exports = {
  entry: "./src/main.js",
  // 出口需要以path和filename形式存在
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "bundle.js"
  }
};
```

- 直接使用`webpack`即可完成项目打包

### 命令映射

使用`node init`命令对项目初始化时，会创建一个 package.json 文件，该文件中有一个 scripts 属性，可以配置命令映射：

```json
{
  "name": "meetpackage",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "//": "执行build命令相当于执行webpack命令",
    "build": "webpack"
  },
  "author": "",
  "license": "ISC"
}
```

在命令行中输入`npm run build`进行打包。

### 本地 webpack

在 package.json 中配置的 webpack 映射和直接使用 webpack 命令主要的区别：**package.json 中配置的映射会优先在本地 webpack 中查找，直接使用 webpack 查找的是全局 webpack**。

除非一层一层进入 node 目录下 webpack 的目录（`node_mudule/.bin/webpack`），然后在该目录下执行 webpack 才会使用本地 webpack。

安装本地 webpack：

```shell
npm install webpack@3.6.0 --save-dev
```

`--save-dev`代表开发时依赖，webpack 只是在打包时候有用，打包之后的项目不需要在依赖 webpack，因此可以选择开发时依赖安装本地 webpack。

安装完成后：

- 观察 package.json 中，多了一个`devDependencies`属性
- 在项目根目录下，多了一个`node_modules`目录，该目录下存放默认的 node 模块

```json
{
  "name": "meetpackage",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "//": "执行build命令相当于执行webpack命令",
    "build": "webpack"
  },
  "author": "",
  "license": "ISC",
  "devDependencies": {
    "webpack": "^3.6.0"
  }
}
```

## 4.3 webpack 配置 loader

### 使用 CSS 配置——css-loader

在实际开发中，不仅有基本的 JS 代码处理，还需要处理 CSS、图片，也包括将 ES6 语法转换成 ES5 语法，将 TypeScript 转换成 ES5 代码，将 SCSS、LESS 转换成 CSS，将.jsx、.vue 装换成 js 文件等等。

webpack 本身对于这些转换并不支持，需要使用 loader 进行拓展。

- main.js 中引入 css 文件，这样 index.html 直接引入 bundle.js 后就会直接拿到对应的 css 文件：`require("./css/foo.css");`
- 安装对应的 loader，基本的 loader 需要两个：
  - npm install style-loader --save-dev
  - npm install css-loader --save-dev
- 在 webpack.config.js 中进行配置：

```javascript
const path = require("path");

module.exports = {
  entry: "./src/main.js",
  // 出口需要以path和filename形式存在
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "bundle.js"
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        // css-loader只负责将css文件进行加载
        // style-cloader负责将样式加入到DOM中
        // 使用多个loader时候，读取顺序是从右往左进行的
        use: ["style-loader", "css-loader"]
      }
    ]
  }
};
```

- `num run build`进行打包

### 使用 less 配置——less-loader

和使用 CSS 配置相同，首先需要在 main.js 中引入对应的.less 文件，然后安装 less-loader，注意同时需要安装 less 包（less 包是 npm 包下的一部分，用于将 less 文件解析成 css 样式，这个解析过程是 less 完成的，不是 webpack 完成的）。最后需要在 webpack.config.js 文件中配置 less-loader 完成配置（注意 less 和.css 的 loade 不能共用，需要各自配置）：

```javascript
const path = require("path");

module.exports = {
    entry: "./src/main.js",
    // 出口需要以path和filename形式存在
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js'
    },
    module: {
        rules: [
            # ...这里省略
            {
                test: /\.less$/,
                use: ['style-loader', 'css-loader', 'less-loader']
            }
        ]
    }
}
```

### 使用图片配置——url-loader&file-loader

需求：

现在希望利用 CSS 文件将 index.html 的背景图片换成自定义图片。

- 在 CSS 文件中使用`background: url("..img/tom-big.jpg")`
- 图片（及其它文件）相关的加载器有两种：
  - url-loader：如果图片的大小小于 url-loader 中配置的`limit`，则会将图片使用 Base64 转换成字符串形式，不会以文件形式保存在 dist/目录下
  - file-loader：如果图片的大小大于 url-loader 中配置的`limit`，则会将图片保存在 dist\目录下，并且是 32 位 hash 值命名。
- 使用`npm install url-loader --save-dev`安装相关的 url-loader，如果是 file-loader，不需要额外安装
- 如果使用的是 file-loader，有时候希望将图片保存在 dist\目录下特定位置，并且保留原图片的名称，同时尽量避免重复，可以在 options 中配置`name`：

```javascript
const path = require("path");

module.exports = {
    entry: "./src/main.js",
    // 出口需要以path和filename形式存在
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js',
        publicPath: 'dist/'
    },
    module: {
        rules: [
            // ...这里省略
            {
                test: /\.(png|jpg|jpeg|gif)$/,
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            limit: 8192,
                            // 将新的文件放入到dist/img下，并且格式为:
                            // 原图片名称-8位hash值.原图片拓展名
                            name: 'img/[name]-[hash:8].[ext]'
                        }
                    }
                ]
            }
        ]
    }
}
```

### ES6 语法处理——babel-loader

webpack 将 main.js 及其依赖的 js 文件最终打包成 bundle.js 文件，但是在 bundle.js 文件中，使用的语法还是 es6 的，如果浏览器不支持 es6 语法，就会报错。

- babel-loader 安装：

```shell
npm install --save-dev babel-loader@7 babel-core babel-preset-es2015
```

- webpack.config.js 配置：

```js
const path = require("path");

module.exports = {
    entry: "./src/main.js",
    // 出口需要以path和filename形式存在
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js',
        publicPath: 'dist/'
    },
    module: {
        rules: [
            // ... 这里省略
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
            			// 这里和安装babel-preset-es2015对应
                        presets: ['es2015']
                    }
                }
            }
        ]
    }
}
```

- 执行`npm run build`命令后，可以看到 bundle.js 中例如 const 的 es6 语法全部被替换成 es5 中的 var 语法

## 4.4 webpack 配置 Vue

之前使用 Vue 都是通过<script>标签引入的，webpack 配置 Vue 需要使用 Vue 安装的第三种方式：npm 安装，直接安装到当前项目中：

- `npm install Vue --save`：注意此时不能使用--save-dev，因为项目打包后还是需要 Vue
- 在 main.js 中引入 Vue：

```js
// ...这里省略其它
// 使用Vue
import Vue from 'vue';

const app = new Vue({
    el: '#app',
    data: {
        message: 'msg'
    }
})

// index.html
<!DOCTYPE html>
<html>
  <title>webpack配置Vue</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <h2>webpack的loader</h2>
    <div id="app">
      {{message}}
    </div>
    <script src="./dist/bundle.js"></script>
  </body>
</html>

```

- 直接运行`npm run build`报错：

  You are using the runtime-only build of Vue where the template compiler is not available. Either pre-compile the templates into render functions, or use the compiler-included build.

  这设计到两种编译方式：

  - runtime-only：代码中不可以有任何<template>，因为没有 compiler
  - runtime-compiler：有 compiler，所以代码中可以有<template>

  直接使用 npm run build 打包会直接使用 vue-runtime 版本，导致出错

- 解决：在 webpack.config.js 中加入 resolve 配置：

```js
const path = require("path");

module.exports = {
    entry: "./src/main.js",
    // 出口需要以path和filename形式存在
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js',
        publicPath: 'dist/'
    },
    // ...这里省略
    resolve: {
        alias: {
            // 默认使用的是：node_modules/vue/dist/vue.runtime.js
            // 修改成：node_modules/vue/dist/vue.esm.js
            'vue$': 'vue/dist/vue.esm.js'
        }
    }
}
```

### el 和 template

在上面演示过程中，index.html 文件<div>标签中加入了相关获取 Vue 实例数据的 html 代码，实际开发过程中，index.html 只放一个空的<div>标签，用于挂载 Vue 实例，相关的 html 代码会放入到 Vue 实例的 template 中。因为在 Vue 编译期间，会将 template 对应的代码放入到 index.html 对应位置上：

```js
// main.js
// ...省略一部分内容
import Vue from 'vue';

new Vue({
    el: '#app',
    template: `
        <div>
            <h2>{{message}}</h2>
            <button>点击</button>
        </div>
    `,
    data: {
        message: 'msg'
    }
})

// index.html
<!DOCTYPE html>
<html>
  <title>webpack配置Vue</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <div id="app"></div>
    <script src="./dist/bundle.js"></script>
  </body>
</html>
```

### Vue 抽取（一）

上面代码中 Vue 实例的 template 中有 html 代码，随着代码增多会变得非常臃肿，需要进行抽取。

先自定义一个组件，然后将 Vue 实例中的 template 代码放到自定义组件的 template 中，也因为 Vue 实例的 template 中已经全部移到自定义组件中了，那么 Vue 实例中的 data、methods 等都因该同步到自定义组件中。之后 Vue 实例直接注册自定义组件，并且在 template 中引用自定义组件的标签即可：

```js
// ...省略其它内容
// 配置Vue
import Vue from "vue";

// 将原本在Vue实例中的东西先抽取出来，之后在Vue实例中注册App组件即可
const App = {
  template: `
        <div>
            <h2>{{message}}</h2>
            <button @click="onClick">点击</button>
        </div>
    `,
  data() {
    return {
      message: "msg"
    };
  },
  methods: {
    onClick() {
      console.log("click");
    }
  }
};

new Vue({
  el: "#app",
  // 在template中用了一个自定义子组件
  template: "<App/>",
  components: {
    App
  }
});
```

### Vue 抽取（二）

上述抽取虽然简化了 Vue 实例的代码，但是自定义组件还是存在于 Vue 实例的文件中，随着自定义组件不断增多，最终 Vue 实例所在的文件还是会变得非常臃肿。

因为自定义组件也是一个对象，既然是对象，就可以导入。因此可以新建一个存放自定义组件的文件夹，每个文件对应一个自定义组件，在 Vue 实例需要的使用，可以直接 import 进来。特别的，因为每个文件代表一个自定义组件，还可以配合 import default 来优化。

- 在 src 下新建一个 Vue 文件夹用于存放自定义组件，新建一个 app.js 文件，将原本在 main.js 中自定义组件的内容迁移到 app.js 中，并使用 export default 进行导出：

```js
export default {
  template: `
        <div>
            <h2>{{message}}</h2>
            <button @click="onClick">点击</button>
        </div>
    `,
  data() {
    return {
      message: "msg"
    };
  },
  methods: {
    onClick() {
      console.log("click");
    }
  }
};
```

- main.js 中直接使用`import App from './vue/app.js'`进行导入

```js
// ...省略其它内容
import App from "./vue/app.js";

new Vue({
  el: "#app",
  // 在template中用了一个自定义子组件
  template: "<App/>",
  components: {
    App
  }
});
```

### Vue 抽取（最终版本）

虽然 app.js 文件表示一个自定义组件，并且存放在 vue 文件夹下，main.js 需要使用的时候直接 import 进来即可。但是 app.js 中，template、data、methods 以及样式并没有进行分离。为此，可以引入.vue 文件来进行分离。

- 编写 App.vue 文件：

```vue
/* 分离模板 */
<template>
  <div>
    <h2 class="title">{{ message }}</h2>
    <button @click="onClick">点击</button>
  </div>
</template>

/* 分离脚本 */
<script>
export default {
  name: "App",
  data() {
    return {
      message: "msg"
    };
  },
  methods: {
    onClick() {
      console.log("vue------------");
    }
  }
};
</script>

/* 分离样式 */
<style scoped>
.title {
  color: blue;
}
</style>
```

- 安装 vue-loader：要在 main.js 中引入 vue 文件，也需要使用对应的 vue-loader：

```shell
npm install vue-laoder vue-template-compiler --save-dev
```

- 在 wenpack.config.js 中配置 vue-loader：

```js
const path = require("path");

module.exports = {
    entry: "./src/main.js",
    // 出口需要以path和filename形式存在
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js',
        publicPath: 'dist/'
    },
    module: {
        rules: [
            // ...其它省略
            {
                test: /\.vue$/,
                use: ['vue-loader']
            }
        ]
    },
    resolve: {
        alias: {
            // 默认使用的是：node_modules/vue/dist/vue.runtime.js
            // 修改成：node_modules/vue/dist/vue.esm.js
            'vue$': 'vue/dist/vue.esm.js'
        }
    }
}
```

- 直接打包可能会报错：vue-loader was used without the corresponding plugin. Make sure to include VueLoaderPlugin in your webpack config.
- 在 vue-loader14 以后的版本中需要一个插件，可以在 package.json 中将 vue-loader 版本调低：`"vue-template-compiler": "^2.6.11"`，之后执行`npm install`刷新 package.json 文件变化
- 最后执行 npm run build 完成打包

### 补充

Vue 抽取最终版本中，main.js 引入 App.vue 文件时，默认不能省略后缀名，需要在 webpack.config.js 中进行配置：

```js
const path = require("path");

module.exports = {
    entry: "./src/main.js",
    // 出口需要以path和filename形式存在
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js',
        publicPath: 'dist/'
    },
    module: {
        // ...这里省略
    },
    resolve: {
        alias: {
            // 默认使用的是：node_modules/vue/dist/vue.runtime.js
            // 修改成：node_modules/vue/dist/vue.esm.js
            'vue$': 'vue/dist/vue.esm.js'
        },
        // 配置哪些文件可以省略后缀名
        extensions: ['.js', '.css', '.vue']
    }
}
```

## 4.5 webpack 和 plugin

plugin 通常对某个现有的功能进行拓展，plugin 相对于 loader：

- loader 主要用于转换某些类型模块，它是一个转换器（加载器）
- plugin 是插件，对 webpack 本身进行拓展，是一个拓展器

### 版权 plugin

可以在 webpack 对项目打包之后的 bundle.js 文件中加入版权信息：

```js
const path = require("path");
const webpack = require('webpack');

module.exports = {
    // ...这里省略
    plugins: [
        new webpack.BannerPlugin('版权信息')
    ]
}
```

再使用`npm run build`命令打包项目之后，就能在 bundle.js 文件中看到版权信息。

### HtmlWebpackPlugin 插件

在真实项目发布时，发布的是 dist 文件夹中的内容，但是 dist 文件夹中没有 index.html 文件，那么打包的 js 等文件就没有意义了。
所以，需要使用 HtmlWebpackPlugin 插件将 index.html 文件打包到 dist 文件夹下。该插件主要作用：

- 自动生成一个 index.html 文件（可以指定模板来生成）
- 将打包的 js 文件，通过 <script> 标签插入到 body 中

使用 HtmlWebpackPlugin:

- `plugin>npm install html-webpack-plugin --save-dev`
- 在 webpack.config.js 中进行配置 html-webpack-plugin 插件
- 使用 npm run build 命令进行打包

此时打包产生的项目中，dist 文件夹下会产生 index.html 文件：

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Webpack App</title>
  </head>
  <body>
    <script type="text/javascript" src="dist/bundle.js"></script>
  </body>
</html>
```

但是该 index.html 还有一些问题：

- 引入 bundle.js 时，不应该加入`dist/`前缀
- <body>标签是空的，将原来index.html中内容加入到其中来

解决：

- 在 webpack.config.js 的 output 中去掉`publicPath`
- 在配置 HtmlWebpackPlugin 插件时，指定对应的 template：

```js
const path = require("path");
const webpack = require('webpack');
const htmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    // ...省略其它
    plugins: [
        new webpack.BannerPlugin('版权信息'),
        new htmlWebpackPlugin({
            template: 'index.html'
        })
    ]
}
```

### 压缩 JS 插件

项目发布之前需要对 JS 文件进行压缩，避免 JS 文件过大。

- 安装插件：`install uglifyjs-webpack-plugin@1.1.1 --save-dev`
- 在`webpack.config.js`中配置 uglifyjs 插件

此时再去查看 bundle.js 的代码，可以看到该文件已经被压缩过了。

## 4.6 搭建本地服务器

webpack 提供了一个可选的本地开发服务器，这个本地服务器基于 node.js 搭建，内部使用 express 框架，可以实现浏览器自动刷新修改后的结果。

- 安装：npm install webpack-dev-server@2.9.1 --save-dev
- 在 webpack.config.js 中配置选项：

```js
module.exports = {
    // ...省略其它
    devServer: {
        // 为哪一个文件夹提供本地服务
        contentBase: './dist',
        // 页面实时刷新
        inline: true
        // port
        port: 8080
    }
}
```

- 在 package.json 中配置 scripts，因为在 cmd 下执行 webpack-server-dev 是在全局作用域中查找，而现在只在局部作用域中安装了 webpack-server-dev：`"dev": "webpack-dev-server --open"`
- 执行 npm run dev 进行测试

## 4.7 Vue配置文件分离

之前所有的Vue配置文件都放在`webpack-config.js`中，但在实际开放中，对于该文件中的配置应该根据不同的环境进行配置，例如：UglifyjsWebpackPlugin插件应该在生产环境中使用，开放环境应该禁用方便调试；webpack-dev-server应该在开发环境中使用，提高开发效率，在生产环境中禁用。

为此，可以将webpack-config.js配置文件进行分离，分离出三个文件：

-   base.config.js：公共的配置文件
-   dev.config.js：开发时配置文件，例如可以存放webpack-dev-server的配置
-   prod.config.js：生产时配置文件，例如可以存放UglifyjsWebpackPlugin插件配置

具体实现步骤：

-   在项目根路径下新建build文件夹，创建以上三个文件
-   在dev.config.js和prod.config.js中，分别结合base.config.js进行导出：

```js
// ----------------prod.config.js
const uglifyWebpackPlugin = require('uglifyjs-webpack-plugin');

const webpackMerge = require('webpack-merge');
const baseConfig = require('./base.config');

// 将生产环境和base环境进行导出
module.exports = webpackMerge(baseConfig, {
    plugins: [
        new uglifyWebpackPlugin()
    ]
})

// ----------------dev.config.js
const baseConfig = require('./base.config');
const webpackMerge = require('webpack-merge');

// 将开发环境和base环境进行导出
module.exports = webpackMerge(baseConfig, {
    devServer: {
        contentBase: './dist',
        inline: true
    }
})
```

-   删除原有的webpack.config.js文件，并在package.json中指定配置文件位置：

```json
"build": "webpack --config ./build/prod.config.js",
"dev": "webpack-dev-server --open --config ./build/dev.config.js"
```

-   注意在base.config.js中修改输出文件位置：因为现在base.config.js在根目录的build目录下，不在根目录下：

```js
module.exports = {
    entry: "./src/main.js",
    // 出口需要以path和filename形式存在
    output: {
        // 修改./dist ---> ../dist
        path: path.resolve(__dirname, '../dist'),
        filename: 'bundle.js',
        // publicPath: 'dist/'
    }
    // ...这里省略
}
```

