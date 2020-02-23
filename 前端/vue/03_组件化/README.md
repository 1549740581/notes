# 三、组件化

## 3.1 什么是组件化？

*   Vue组件化思想：
    -   提供了一种抽象，让我们可以开发出来一个个独立可复用的小组件来构造我们的应用
    -   任何一个应用都会被抽象成一棵组件树

尽可能将页面拆分成一个个小的、可复用的组件，这样代码更加方便阻止和管理，拓展性更强。

-   注册组件步骤：
    -   创建组件构造器：cpnCtor=Vue.extends({...})，通常会传入一个`template`作为组件模板
    -   注册组件：Vue.componnet(“cpn-name”, cpnCtor)
    -   使用组件：在Vue实例作用域范围内使用组件：<cpn-name></cpn-name>

## 3.2 基本使用

### 原始方法使用

现在希望将一段<div>封装成一个组件，然后直接使用自定义组件的标签名<cpn>进行调用：

```html
<body>
    <div id="div1">
      <!-- 3. 使用组件 -->
      <cpn></cpn>
      <cpn></cpn>
      <!-- 也可以嵌套使用 -->
      <div>
        <cpn></cpn>
      </div>
    </div>
  </body>
  <script>
    // 1. 创建组件构造器
    const cpnCtor = Vue.extend({
      template: `
          <div>
            <h1>我是标题</h1>
            <p>我是内容------------</p>
            <p>我是内容++++++++++++</p>
          </div>
          `
    });
    // 2. 注册组件
    Vue.component("cpn", cpnCtor);
    const app = new Vue({
      el: "#div1",
      data: {}
    });
  </script>
```

Vue2.x文档里几乎看不到原始方法进行组件注册，现在一般都是使用语法糖形式注册组件，之后介绍。

### 全局组件&局部组件

-   全局组件：Vue.component('cpn-name', cpnCtor)注册的组件为全局组件，意味着：在多个Vue实例下都可以使用该组件（注意：实际开发中一般只有一个Vue实例）
-   局部组件：Vue的入参Options有一个`conponents`选项，用于注册局部组件

```html
<!DOCTYPE html>
<html>
  <title>全局组件&局部组件</title>
  <script src="../../js/vue.js"></script>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <div id="div1">
      <cpnglobal></cpnglobal>
      <!-- cpnlocal在div2中注册，不能在div1中使用，报错 -->
      <cpnlocal></cpnlocal>
    </div>

    -----------------------------------
    <!-- 全局组件在多个Vue实例下都可以使用 -->
    <div id="div2">
      <cpnglobal></cpnglobal>
      <cpnlocal></cpnlocal>
    </div>
  </body>
  <script>
    //   创建局部组件构造器
    const cpnCtorLocal = Vue.extend({
      template: `
          <div>
            <h1>title-local</h1>
            <p>content</p>
          </div>
          `
    });

    // 创建全局组件构造器
    const cpnCtorGlobal = Vue.extend({
      template: `
        <div>
            <h1>title-gobal></h1>
            <p>content</p>
        </div>
        `
    });

    // 注册全局组件
    Vue.component("cpnglobal", cpnCtorGlobal);
    const app1 = new Vue({
      el: "#div1"
    });

    const app2 = new Vue({
      el: "#div2",
      components: {
        cpnlocal: cpnCtorLocal
      }
    });
  </script>
</html>
```

### 父子组件

使用Vue.extend({...})创建组件构造器时，不仅可以传入template选项，还可以传入`components`选项，即：可以在一个组件里面注册另外一个组件，两者形成父子组件。

**Vue实例可以也可以看做是顶级组件**

```html
<!DOCTYPE html>
<html>
  <title>父子组件</title>
  <script src="../../js/vue.js"></script>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <div id="div1">
      <!-- son组件和fater组件平级，然后fater组件内部又包含一个son组件 -->
      <fathercpn></fathercpn>
      <soncpn></soncpn>
    </div>
  </body>
  <script>
    // 定义子组件构造器
    const cpnCtorSon = Vue.extend({
      template: `
        <div>
            <h1>Son</h1>
        </div>
          `
    });

    // 定义父组件构造器
    const cpnCtorFather = Vue.extend({
      template: `
            <div>
                <h1>Father</h1>
                <!-- 使用子组件 -->
                <soncpn></soncpn>
            </div>
        `,
      // 注册子组件
      components: {
        soncpn: cpnCtorSon
      }
    });
    const app = new Vue({
      el: "#div1",
      components: {
        fathercpn: cpnCtorFather,
        // 仍然可以在这里对子组件进行注册
        soncpn: cpnCtorSon
      }
    });
  </script>
</html>
```

### 注册组件语法糖

原始注册Vue组件构造器使用Vue.extend({...})方法，语法糖形式下，直接将对象{...}放入到Vue.component()的第二个参数里，即省去了调用Vue.extend()的步骤：

```html
<!DOCTYPE html>
<html>
  <title>注册组件语法糖</title>
  <script src="../../js/vue.js"></script>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <div id="div1">
      <cpnlocal></cpnlocal>
      <cpnglobal></cpnglobal>
    </div>
  </body>

  <script>
    Vue.component("cpnglobal", {
      template: `
        <div>
            <h1>语法糖注册全局组件</h1>
        </div>
        `
    });
    const app = new Vue({
      el: "#div1",
      components: {
        cpnlocal: {
          template: `
                <div>
                    <h1>语法糖注册局部组件</h1>
                </div>
              `
        }
      }
    });
  </script>
</html>
```

### template分离

在上面注册组件时候，无论是全局组件还是局部组件，template中都直接包含了html标签，当html较复杂时，注册组件代码会显得非常臃肿。此时可以使用template分离，具体有两种方式：

-   使用<script>标签，但是type必须为：text/x-template
-   使用<template>标签：注意使用<div>标签包装起来

```html
<!DOCTYPE html>
<html>
  <title>template分离</title>
  <script src="../../js/vue.js"></script>
  <head>
    <meta charset="utf-8" />
  </head>

  <body>
    <div id="div1">
      <cpn1></cpn1>
      <cpn2></cpn2>
    </div>

    <template id="cpn2">
      <div>
        <h1>template分离方式二</h1>
      </div>
    </template>
  </body>

  <script type="text/x-template" id="cpn1">
    <div>
        <h1>template分离方式一</h1>
    </div>
  </script>
  <script>
    const app = new Vue({
      el: "#div1",
      components: {
        cpn1: {
          template: "#cpn1"
        },
        cpn2: {
          template: "#cpn2"
        }
      }
    });
  </script>
</html>
```

### 组件和实例数据

#### 组件能不能访问实例data属性

组件是一个单独模块功能的封装，这个模块中有属于自己的HTML模板，也应该有自己的数据data，那么组件能够直接访问到顶层Vue实例的data么？

不能。即使能够访问，那么也会导致顶层Vue实例的data变得非常臃肿。所以可以推测，Vue组件应该有保存自己data的地方。

#### 组件有自己的data属性

注册组件时，入参选项不仅可以有`template`、还可以有`data`，并且特别的：

-   data属性是一个函数：data: function() {...}
-   该函数的返回值类型对象：return {...}
-   返回的对象中保存着组件所需要的数据

```html
<!DOCTYPE html>
<html>
  <title>组件自己的data</title>
  <script src="../../js/vue.js"></script>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <!-- 模板分离 -->
    <template id="cpn">
      <div>
        <h1>{{title}}</h1>
        <h1>{{content}}</h1>
      </div>
    </template>

    <div id="div1">
      <cpn></cpn>
      <cpn></cpn>
    </div>
  </body>
  <script>
    const app = new Vue({
      el: "#div1",
      components: {
        cpn: {
          template: "#cpn",
          //   组件自己的data
          data() {
            return {
              title: "title",
              content: "content"
            };
          }
        }
      }
    });
  </script>
</html>
```

#### 组件data为什么必须是函数

组件的目的是为了复用，但是在复用组件的时候不应该相互影响，例如：

如果将计数器（**-按钮 计数器 +按钮**）封装成一个组件，当使用多个计数器组件时候，不希望点击一个组件的`+按钮`时候，所有组件的计数器都会增加，而是希望对应组件的计数器增加。这就是组件不会相互影响。

如果组件data是对象类型，那么多个组件的data相当于指向同一片内存区域，本质上是一个**浅拷贝**；但是如果组件data是一个函数，那么多个组件的data为调用函数后返回值，类型为对象，本质上做了一个**深拷贝**，即函数调用返回不同对象，内存相互隔离。正是因为组件data是函数才会让组件不相互影响，才能让组件做到真正复用。

```html
<!DOCTYPE html>
<html>
  <title>组件data必须是函数</title>
  <script src="../../js/vue.js"></script>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="cpn1">
      <div>
        <button @click="decrement">-</button>
        <span>{{counter}}</span>
        <button @click="increment">+</button>
      </div>
    </template>
    <div id="div1">
      <cpn></cpn>
      <cpn></cpn>
      <cpn></cpn>
    </div>
  </body>
  <script>
    const obj = {
      counter: 0
    };
    const app = new Vue({
      el: "#div1",
      components: {
        cpn: {
          template: "#cpn1",
          data() {
            return {
              counter: 0
            };

            // 返回obj相当于data返回的是对象，共享内存，浅拷贝，相互影响
            // return obj;
          },
          methods: {
            decrement() {
              this.counter--;
            },
            increment() {
              this.counter++;
            }
          }
        }
      }
    });
  </script>
</html>
```

## 3.3 父子组件通信

在父子组件中，子组件是不能直接引用父组件或者Vue实例的数据的。但是在实际开发中，往往一些数据是需要从上层传递到下层的：比如在一个页面中我们从服务器请求了很多数据，其中一部分数据不是由整个页面的大组件来展示的，而是需要传递给底层小组件进行显示。这样的机制，可以避免子组件再次发送网络请求获取数据，而是直接从父组件中拿到数据。

Vue中父子组件的通信方式：

-   父组件通过**props**向子组件传递数据
-   子组件通过**事件**向父组件发送消息

注意：Vue实例可以看做是顶层根组件，因此在实际开发中，Vue实例和子组件的通信和父子组件之间的通信过程是完全相同的。

### 父组件props子组件

组件注册时候还可以传递`props`选项，用于父组件传递数据给子组件。在使用子组件时，需要使用`v-bind`将子组件数据和父组件数据进行绑定。props可以是数组，也可以是对象，推荐使用对象类型，因为可以提供限制类型、提供默认值以及是否是必选项等功能：

```html
<!DOCTYPE html>
<html>
  <title>父组件props子组件</title>
  <script src="../../js/vue.js"></script>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="t_cpn">
      <div>
        <p>子组件获取父组件数据</p>
        <h2>获取Vue实例的Message数据：{{cmessage}}</h2>
        <h2>获取Vue实例的Movies数据：{{cmovies}}</h2>
        <h2>获取Vue实例的Ext数据：{{cext}}</h2>
      </div>
    </template>

    <div id="div1">
      <!-- 通过v-bind，将子组件的cmovies和cmessage和父组件进行绑定 -->
      <!-- 如果不用v-bind，cmovies和cmessage直接绑定字符串"movies", "message" -->
      <cpn v-bind:cmovies="movies" :cmessage="message" :cext="ext"></cpn>
    </div>
  </body>
  <script>
    cpn = {
      template: "#t_cpn",
      // props使用数组，数组每个元素是字符串，但是其实是变量
      // props: ["cmovies", "cmessage"]

      // props使用对象更好，可以限制类型、提供默认值以及设置是否必须
      props: {
        cmessage: {
          type: String,
          default: "",
          require: true
        },
        cmovies: {
          type: Array,
          default() {
            return [];
          },
          require: false
        },
        cext: {
          validator(value) {
            return ["success", "fail", "danger"].indexOf(value) != -1;
          }
        }
      }
    };
    const app = new Vue({
      el: "#div1",
      data: {
        // 需要传递给子组件的数据
        message: "message",
        movies: ["aaaa", "bbbb", "cccc", "dddd"],
        // invalid
        // ext: "foo"

        ext: "success"
      },
      // es6语法
      components: {
        cpn
      }
    });
  </script>
</html>
```

#### props的数据验证

props支持的数据验证类型有：

-   String
-   Number
-   Boolean
-   Array
-   Object
-   Date
-   Function
-   Symbol

验证也支持自定义类型，同时如果验证类型时Array、Object类型时，提供默认值应该使用工厂函数：

```vue
cpn = {
      template: "#t_cpn",
      props: {
        cmovies: {
          type: Array,
		 // 在高版本Vue中：default: []会报错
          default() {
            return [];
          },
          require: false
       }
    }
};
```

props限制类型时，可以同时限制多种类型：

```vue
age: [String, Number] // age限制为String类型和Number类型
```

#### 自定义验证

props在使用对象类型时，可以自定义自己的验证类型：

```vue
cpn = {
	template: "#t_cpn",
	props: {
		cext: {
			// 限制cext只能接受以下字符串中的某一个
			validator(value) {
				return ["success", "fail", "danger"].indexOf(value) != -1;
			}
		}
	}
};
```

#### 自定义类型

```vue
function Person(firstName, secondName) {
	this.firstName = firstName;
	this.secondName = secondName;
}

Vue.component("foo", {
	props: {
		// 限制author的类型为自定义类型Person
		author: {
			type: Person
		}
	}
})
```

#### props驼峰命名问题

v-bind并不支持驼峰命名绑定，props中如果key使用了驼峰命名，需要使用**-**进行转换：

```html
<!DOCTYPE html>
<html>
  <title>驼峰命名问题</title>
  <script src="../../js/vue.js"></script>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="t-cpn">
      <div>
        <h2>{{cFooBarInfo}}</h2>
      </div>
    </template>
    <div id="div1">
      <!-- v-bind不支持驼峰绑定，会出现空值 -->
      <!-- <cpn :cFooBarInfo="fooBarInfo"></cpn> -->

      <cpn :c-foo-bar-info="fooBarInfo"></cpn>
    </div>
  </body>
  <script>
    const cpn = {
      template: "#t-cpn",
      props: {
        cFooBarInfo: {
          type: Object,
          default() {
            return {};
          }
        }
      }
    };
    const app = new Vue({
      el: "#div1",
      data: {
        fooBarInfo: {
          name: "foo",
          msg: "bar",
          ext: "foo-bar-info"
        }
      },
      components: {
        cpn
      }
    });
  </script>
</html>
```

### 子组件$emit父组件

子组件通过`$emit`向父组件发射自定义事件，具体过程：

-   子组件监听相应的系统事件，例如button的click事件，并准备在click事件内部发射自定义事件
-   在click事件内部通过`$emit`发射自定义事件并传递参数
-   父组件监听子组件通过`$emit`发射的自定义事件，并进行回调处理
-   父组件在自己的methods内部进行回调处理，并且能够接收到子组件发射自定义事件时携带的参数

```html
<!DOCTYPE html>
<html>
  <title>子组件发射事件给父组件</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="t-cpn">
      <div>
        <!-- 1. 子组件监听相应事件，并准备在即将发生的事件内（例如click事件）发射自定义事件 -->
        <button v-for="item in categories" @click="btnClick(item)">
          {{item.msg}}
        </button>
      </div>
    </template>

    <div id="div1">
      <!-- 3. 父组件监听子组件发射的自定义事件：btn-click-emit，并进行回调 -->
      <cpn @btn-click-emit="btnClickCallback"></cpn>
    </div>
    <script src="../../js/vue.js"></script>
    <script>
      const cpn = {
        template: "#t-cpn",
        data() {
          return {
            categories: [
              { id: "1111", msg: "数码3C" },
              { id: "1112", msg: "家用办公" },
              { id: "1113", msg: "饮料酒水" }
            ]
          };
        },
        methods: {
          // 2. 子组件发射自定义事件：事件名称，事件参数
          btnClick(item) {
            this.$emit("btn-click-emit", item);
          }
        }
      };

      const app = new Vue({
        el: "#div1",
        data: {},
        components: {
          cpn
        },
        methods: {
          // 4. 父组件进行回调处理
          btnClickCallback(item) {
            console.log(item);
          }
        }
      });
    </script>
  </body>
</html>
```

#### 驼峰命名问题

子组件监听`v-on`系统事件以及父组件监听子组件发射的自定义事件时，都不要使用驼峰命名规则，可以使用**-**进行分割，回调函数可以使用驼峰命名规则。

#### v-on

v-on不仅能够监听DOM事件，还能够监听子组件发射的自定义事件。



## 3.4 案例

#### 需求一

父组件有data数据num1和num2，通过props传递给子组件cnum1，cnum2，然后又两个输入框分别和cnum1、cnum2双向绑定。出现的问题：

Avoid mutating a prop directly since the value will be overwritten whenever the parent component re-renders. Instead, use a data or computed property based on the prop's value.

原因：

cnum1和cnum2是通过props获取到父组件的数据，也就是说这两个数据应该由父组件进行修改，而不能通过其它组件，例如这里的input进行修改。

解决：

在自定义组件中加入自己的data数据dnum1、dnum2，并使用cnum1、cnum2赋值。

#### 需求二

```html
props-cnum1：{{cnum1}}   (1)
data-dnum1：{{dnum1}}	(2)
<input2/>				 (3)
props-cnum2：{{cnum2}}	(4)
data-dnum2：{{dnum2}}	(5)
<input2/>				 (6)
```

希望input和对应的dnum进行绑定，input框中修改时，对应的dnum改变同时对应的cnum也改变。

思路：

现在input修改时，需要dnum和cnum都改变，直接使用v-model不能实现，因为它只能实现单个数据的双向绑定。可以使用v-model的底层原理：v-model=v-bind + @事件（这里是@input事件）来完成。即

-   v-model绑定dnum值：`:value="dnum1"`、`:value="dnum2"`
-   @input事件内部修改对应dnum的值，并发射自定义事件报告dnum已经改变，父组件监听到dnum改变的事件后，同时修改num数据（父组件的num数据会自动通过props传递给子组件的cnum，并修改子组件的dnum值）

#### 需求三

input（3）的值改变时，（4）（5）（6）的值会修改成（3）中值的100倍；intput（6）的值改变时，（1）（2）（3）的值会修改成（6）的值的1/100。

思路：

@input事件发生时，除了需要发射num1已经修改的事件外，还需要发射num2的值已经修改的事件，并且此时参数值num1的100倍。同理，另一个@input事件发生时，也要额外发射num1的值已经修改的事件。



#### 完整代码

```html
<!DOCTYPE html>
<html>
  <title>案例</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="t-cpn">
      <div>
        <h2>props-cnum1：{{cnum1}}</h2>
        <h2>data-dnum1：{{dnum1}}</h2>
        <!-- <input v-model="dnum1" /> -->

        <!-- 在input时间内发射自定义事件，通知父组件input内部的值已经修改 -->
        <input :value="dnum1" @input="num1Change" />

        <h2>props-cnum2：{{cnum2}}</h2>
        <h2>data-dnum2：{{dnum2}}</h2>
        <!-- <input v-model="dnum2" /> -->
        <input :value="dnum2" @input="num2Change" />
      </div>
    </template>
    <div id="div1">
      <!-- 父组件监听字符键发射的自定义组件 -->
      <cpn
        :cnum1="num1"
        :cnum2="num2"
        @num1-change="num1ChangeCallback"
        @num2-change="num2ChangeCallback"
      ></cpn>
    </div>
    <script src="../../js/vue.js"></script>
    <script>
      const cpn = {
        template: "#t-cpn",
        // 子组件的data，<input>标签应该绑定data，而不是绑定props
        data() {
          return {
            dnum1: this.cnum1,
            dnum2: this.cnum2
          };
        },
        // 父组件的num传递给子组件cnum
        props: {
          cnum1: Number,
          cnum2: Number
        },
        methods: {
          num1Change(event) {
            this.dnum1 = event.target.value;
            this.dnum2 = this.dnum1 * 100;
            // 通知修改父组件的num1和num2
            this.$emit("num1-change", this.dnum1);
            this.$emit("num2-change", this.dnum2);
          },
          num2Change(event) {
            this.dnum2 = event.target.value;
            this.dnum1 = this.dnum2 / 100;
            // 通知修改父组件的num1和num2
            this.$emit("num2-change", this.dnum2);
            this.$emit("num1-change", this.dnum1);
          }
        }
      };
      const app = new Vue({
        el: "#div1",
        data: {
          num1: 1,
          num2: 2
        },
        components: {
          cpn
        },
        methods: {
          // 父组件回调并修改this.num1的值，this.num1的值会通过props传递给子组件，和赋值给data数据
          num1ChangeCallback(value) {
            this.num1 = parseInt(value);
          },
          num2ChangeCallback(value) {
            this.num2 = parseInt(value);
          }
        }
      });
    </script>
  </body>
</html>
```

#### watch替代方案

组件的Options入参还提供一个`watch`选项，用于监听当前组件中某个属性的变化，例如子组件需要监听dnum1、dnum2的变化，可以使用：

```vue
const cpn = {
	template: "#t-cpn",
	// 子组件的data，<input>标签应该绑定data，而不是绑定props
	data() {
		return {
			dnum1: this.cnum1,
			dnum2: this.cnum2
		};
	},
	// 父组件的num传递给子组件cnum
	props: {
		cnum1: Number,
		cnum2: Number
	},
	methods: {
		num1Change(event) {
			console.log("---------------");
            this.dnum1 = event.target.value;
            this.$emit("num1-change", this.dnum1);
        },
        num2Change(event) {
            console.log("+++++++++++++++");
            this.dnum2 = event.target.value;
            this.$emit("num2-change", this.dnum2);
        }
    },
	watch: {
        dnum1(newValue) {
        this.dnum2 = newValue * 100;
        this.$emit("num2-change", this.dnum2);
        },
        dnum2(newValue) {
        this.dnum1 = newValue / 100;
        this.$emit("num1-change", this.dnum1);
        }
	}
};
```

## 3.5 父子组件访问方式

有时候需要父组件直接访问子组件，子组件直接访问父组件，或者是子组件直接访问根组件。Vue提供了两种方式的访问：

-   父组件访问子组件：使用`$children`或者`$refs`
-   子组件访问父（根）组件：使用`$parent`、`$root`

### 父访问子

#### $children

$children可以获取当前组件的所有子组件，是一个Object类型数组，通过下标可以访问到对应的组件。但是实际开发中不推荐使用这种方式获取子组件。

#### $refs

$refs默认情况下为null，需要在自定义组件的标签中加上`ref`属性，每加上一个`ref`，$refs数组就会增加一个元素。

```html
<!DOCTYPE html>
<html>
  <title>$child和$refs</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="t-cpn">
      <div></div>
    </template>
    <div id="div1">
      <cpn ref="ref1"></cpn>
      <cpn ref="ref2"></cpn>
      <button @click="btnClick">点击</button>
    </div>
    <script src="../../js/vue.js"></script>
    <script>
      const app = new Vue({
        el: "#div1",
        components: {
          cpn: {
            template: "#t-cpn",
            data() {
              return {
                name: "sherman",
                age: 23
              };
            },
            methods: {
              childFun() {
                console.log("child function");
              }
            }
          }
        },
        methods: {
          btnClick() {
            // console.log(this.$children[0].name);
            // this.$children[1].childFun();

            // 推荐使用$refs方式
            console.log(this.$refs.ref1.name);
            this.$refs.ref2.childFun();
          }
        }
      });
    </script>
  </body>
</html>
```

### 子访问父

字组件访问父组件可以直接通过`$parent`完成，但是实际开放中一般不会使用，因为组件的目的是为了复用，如果子组件中使用$parent对父组件进行访问，那么自定义的子组件就会和父组件耦合。另外，`$root`可以让子组件直接访问到根组件，即Vue实例。

```html
<!DOCTYPE html>
<html>
  <title>$parent和$root</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="t-cpn">
      <div>
        <button @click="btnClick">点击</button>
      </div>
    </template>

    <div id="div1">
      <cpn></cpn>
    </div>
    <script src="../../js/vue.js"></script>
    <script>
      const app = new Vue({
        el: "#div1",
        data: {
          message: "msg",
          foo: "bar"
        },
        components: {
          cpn: {
            template: "#t-cpn",
            methods: {
              btnClick() {
                //  获取父组件和根组件
                console.log(this.$parent.message);
                console.log(this.$root.foo);
              }
            }
          }
        }
      });
    </script>
  </body>
</html>
```

## 3.6 Slot

组件插槽（Slot）的目的是为了让封装的组件更具有扩展性。实际使用中，Slot应该抽取组件的共性，保留不同，用于复用。

### 基本使用

-   基本用法：在template标签中加入<slot></slot>
-   可以有默认值：默认使用<button>填充slot：<slot><button></button></slot>
-   一个slot可以有多个元素：多个元素会全部替换slot

```html
<template id="t-cpn">
    <div>
        <h2>title</h2>
        <p>自定义组件模板</p>
        <!-- 默认slot为<button> -->
        <slot><button>默认slot</button></slot>
    </div>
</template>

<div id="div1">
    <!-- 使用<span>替代slot -->
    <cpn><span>span</span></cpn>

    <!-- 单个slot中包含多个元素，会全部替换 -->
    <cpn>
        <div>
            <span>1.span</span>
            <button>2.button</button>
            <p>3.p</p>
        </div>
    </cpn>

    <!-- 不提供slot会使用默认值 -->
    <cpn></cpn>
</div>
```

### 具名插槽

当封装的组件模板中有多个<slot>时，使用自定义元素替换时，需要使用名称去表明要替换哪一个slot：

-   如果不指定名称（slot），会替换所有没有名称的slot
-   自定义标签通过名称（slot），只替换对应名称的slot

```html
<template id="t-cpn">
    <div>
        <slot name="left"><span>left</span></slot>
        <slot name="center"><span>center</span></slot>
        <slot name="right"><span>right</span></slot>
    </div>
</template>

<div id="div1">
    <!-- 注意slot属性是放在要替换的元素标签内，不是放在外层自定义标签<cpn>内部 -->
    <cpn><button slot="left">替换左边</button></cpn>
    <cpn><h2 slot="center">替换中间</h2></cpn>
    <cpn><p slot="right">替换右边</p></cpn>
</div>
```

### 编译作用域

当一个变量包含在多个组件中时，对应变量在哪个组件中进行查找就涉及到编译作用域问题。官方定义：

父组件模块的所有东西都会在父级作用域内编译；子组件模块的所有东西都会在子级作用域内编译。

```html
<!DOCTYPE html>
<html>
  <title>编译作用域</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="t-cpn">
      <div>
        <h2>title</h2>
        <p>content</p>
        <!-- button会去子组件中查找isShow的值 -->
        <!-- <button v-show="isShow">按钮</button> -->
      </div>
    </template>

    <div id="div1">
      <!-- isShow会去Vue实例下查找isShow的值 -->
      <cpn v-show="isShow"></cpn>
    </div>
    <script src="../../js/vue.js"></script>
    <script>
      const app = new Vue({
        el: "#div1",
        data: {
          isShow: true
        },
        components: {
          cpn: {
            template: "#t-cpn"
          },
          data() {
            return {
              isShow: false
            };
          }
        }
      });
    </script>
  </body>
</html>
```

### 作用域插槽

作用域插槽作用：父组件替换插槽的标签，但是内容由子组件来提供。

需求：

子组件中有一个数组，里面的数据是以<li>标签形式展示，在希望在使用该组件时，能够保留数据不变，但是更换样式，例如数组中数据以 **-** 进行分割，显示在一行上：

```html
<!DOCTYPE html>
<html>
  <title>作用域插槽</title>
  <head>
    <meta charset="utf-8" />
  </head>
  <body>
    <template id="t-cpn">
      <!-- 使用<slot>将默认显示方式包起来，将pLang绑定到data上，用于传递数据 -->
      <slot :data="pLang">
        <div>
          <ul>
            <!-- 默认是使用<li>进行显示 -->
            <li v-for="item in pLang">{{item}}</li>
          </ul>
        </div>
      </slot>
    </template>

    <div id="div1">
      <cpn></cpn>

      <!-- 自定义显示样式 -->
      <cpn>
        <template slot-scope="slot">
          <div>
            {{slot.data.join(" - ")}}
          </div>
        </template>
      </cpn>
    </div>
    <script src="../../js/vue.js"></script>
    <script>
      const app = new Vue({
        el: "#div1",
        data: {
          message: ""
        },
        components: {
          cpn: {
            template: "#t-cpn",
            data() {
              return {
                pLang: ["C", "C++", "Python", "Java", "JS"]
              };
            }
          }
        }
      });
    </script>
  </body>
</html>
```