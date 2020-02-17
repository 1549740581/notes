# 一、基本语法

## 2.1 事件监听

前端开发过程中，需要监听各种事件：点击、拖拽、键盘事件等，在Vue中使用**v-on**进行事件监听，v-on可以使用**@**语法糖进行简化：

### v-on基本使用

```html
<body>
    <div id="div1">
      {{counter}}
      <button @click="increment">+</button>
      <button @click="decrement">-</button>
    </div>
  </body>
  <script>
    const app = new Vue({
      el: "#div1",
      data: {
        counter: 0
      },
      methods: {
        increment() {
          this.counter++;
        },
        decrement() {
          this.counter--;
        }
      }
    });
</script>
```

### v-on传递参数

-   当函数没有参数时，()可以加也可以不加

-   当函数有一个参数，但是调用时并没有传递参数，默认会将屏幕原生事件event传递过去

-   当需要同时传递屏幕原生事件和自定义参数时，使用**$event**表示屏幕原生自定义事件

```html
<body>
    <div id="div1">
        <!-- 当函数没有参数时，()可以加也可以不加 -->
        <button @click="func1()">btn1</button>
        <button @click="func1">btn2</button>
        <!-- 当函数有一个参数，但是调用时并没有传递参数，默认会将屏幕原生事件event传递过去 -->
        <button @click="func2">btn3</button>
        <!-- 当需要同时传递屏幕原生事件和自定义参数时，使用$event表示屏幕原生自定义事件 -->
        <button @click="func3(1, $event)">btn4</button>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {},
        methods: {
            func1() {
                console.log("func1");
            },
            func2(event) {
                console.log("++++++++", event);
            },
            func3(args, event) {
                console.log("++++++++", args, event);
            }
        }
    });
</script>
```

### v-on修饰符

Vue提供了一些修饰符可以方便的处理事件调用：

-   .stop：调用event.stopPropagation()，阻止事件冒泡
-   .prevent：调用event.preventDefault()，阻止一些默认行为
-   .{KeyCoder|KeyAlias}：事件是特定键时才进行出发
-   .native：监听组件根元素的原生组件，之后介绍
-   .once：只触发一次回调

注意：

-   修饰符可以链式调用，例如：@click.stop.prevent="..."
-   .once：如果使用了链式调用.once.stop，只会在第一次能够阻止事件冒泡，之后不能

```html
<body>
    <div id="div1" @click="divClick">
        <!-- stop修饰符防止：事件冒泡 -->
        msg <button @click.stop="btnClick">stop</button>

        <!-- prevent修饰符阻止默认的行为，例如submit默认点击就会根据URL进行提交 -->
        <form action="foo url">
            <input type="submit" value="提交" @click.prevent.stop="formClick" />
        </form>

        <!-- 键盘监听，需要keyCoder或者keyAlias -->
        <input type="text" @keyup.enter="textKeyUp" />

        <!-- once修饰符表明只能监听一次，注意第二次之后，stop属性并不能阻止事件冒泡 -->
        <button @click.once.stop="btnOnceClick">once</button>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {},
        methods: {
            divClick() {
                console.log("div click");
            },
            btnClick() {
                console.log("stop");
            },
            formClick() {
                // 手动提交，并且会阻止submit默认的提交行为，可以在这里自定义数据处理逻辑
                console.log("prevent");
            },
            textKeyUp() {
                console.log("enter");
            },
            btnOnceClick() {
                console.log("once");
            }
        }
    });
</script>
```

## 2.2 条件判断

### v-if & v-else

v-if和v-else相当于if-else条件判断：

```html
<body>
    <div id="div1">
        <h2 v-if="flag">v-if</h2>
        <h2 v-else>v-else</h2>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            flag: true
        }
    });
</script>
```

### v-else-if

v-else-if相当于if-else-if，但是实际中并不推荐这种形式，因为它会直接在标签中进行判断，应该优先考虑使用计算属性：

```html
<body>
    <div id="div1">
        <h2 v-if="score >= 90">优秀</h2>
        <h2 v-else-if="score >= 80">良好</h2>
        <h2 v-else-if="score >= 60">及格</h2>
        <h2 v-else>不及格</h2>

        <!-- 推荐使用计算属性 -->
        <h1>{{result}}</h1>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            score: 99
        },
        computed: {
            result() {
                let result = "";
                if (this.score >= 90) {
                    result = "优秀";
                } else if (this.score >= 80) {
                    result = "良好";
                } else if (this.score >= 60) {
                    result = "及格";
                } else {
                    result = "不及格";
                }
                return result;
            }
        }
    });
</script>
```

### 用户登录切换案例

用户可以使用：用户名和邮箱进行登录，现在实现一个功能，点击按钮后切换不同的登录功能：

```html
<body>
    <div id="div1">
        <span v-if="isUserLogin">
            <label for="username">用户登录</label>
            <!-- 注意key属性，当两个input的key不相同时，VDOM就不会进行复用 -->
            <input type="text" placeholder="用户名" id="username" key="username" />
        </span>

        <span v-else>
            <label for="email">邮箱登录</label>
            <input type="text" placeholder="邮箱" id="email" key="email" />
        </span>
        <button @click="isUserLogin=!isUserLogin">切换登录类型</button>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            isUserLogin: true
        }
    });
</script>
```

### VDOM复用问题

在上面案例中：如果我们现在input中输入了内容，然后点击切换按钮切换到另一种登录方式时，此时input框中仍然保留着之前输入的内容。

-   原因：Vue在进行DOM渲染时，并不会直接将DOM内容渲染到视图上，而是会创建一个虚拟DOM（VDOM），并且处于性能考虑，会尽可能复用之前的元素，而不是创建新的元素。在前面案例中，如果两个input标签没有使用key属性，点击按钮切换时，会复用之前的input，并对比两者的改动，文本框中的内容保持不变
-   解决方式：如果不希望Vue进行复用，可以在input标签中加入key并且保证两个input的key值不相同即可

### v-show

v-show和v-if非常相似，用于决定一个元素是否显示，v-show和v-if主要区别：

-   v-if当条件为false时，包含v-if的元素会直接被删除掉，即不会存在DOM中
-   v-show当条件为false时，包含v-if的元素会添加一个行内样式：display=none，仍然存在DOM中

使用场景：

-   当需要频繁切换时，推荐使用v-show，避免v-if的频繁创建和删除元素
-   当切换频率一般时，推荐使用v-if

```html
<body>
    <div id="div1">
        <h2 v-if="isShow" id="vif">isShow</h2>
        <h2 v-show="isShow" id="vshow">isShow</h2>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            isShow: true
        }
    });
</script>
```

## 2.3 循环遍历

### v-for

v-for用于遍历，可以直接遍历数组，也可以遍历对象：

-   遍历数组：默认是遍历数组值，也可以通过元组(item idx)获取下标
-   遍历对象：默认是遍历对象的value值，也可以通过元组(val key idx)获取key和下标值

```html
<body>
    <div id="div1">
        <ul>
            <!-- v-for遍历数组，可以通过元组获取下表索引值 -->
            <li v-for="(name, idx) in names">{{idx+1}}: {{name}}</li>
        </ul>

        <ul>
            <!--
			v-for遍历对象，可以通过元组获取value-key-index，如果只有一个值时，默认是value
			-->
            <li v-for="(val, key, idx) in info">
                {{idx+1}}: {{key}}-{{val}}
            </li>
        </ul>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            names: ["sherman", "wqy", "tl", "gantlei"],
            info: {
                name: "sherman",
                age: 23,
                height: 1.76
            }
        }
    });
</script>
```

### v-for的key属性

官方推荐在使用v-for时，给对应的元素或者组件添加行:key属性。在需要向数组中插入一个新元素时，默认的Diff算法会依次更新待插入元素位置之后的所有元素，然后在末尾添加一个元素，并更新最后一个元素，达到插入的效果，这种Diff算法是非常低效的。

如果使用key来给每个节点做一个唯一标识，Diff算法就可以正确地识别该节点，找到正确的位置并插入。

因此：key属性的作用就是高效的更新VDOM。

推荐文章链接：

-   https://www.zhihu.com/question/61064119
-   https://calendar.perfplanet.com/2013/diff/

```html
<div id="div1">
    <ul>
        <!-- 添加key属性 -->
        <li v-for="(name, idx) in names" :key="name">{{idx+1}}: {{name}}</li>
    </ul>
</div>
```

### Vue检测数组更新

Vue是响应式的，当数组数据发生变化时，Vue能够监听到变化，并且在页面上做出相应更新操作。对于绝大数数组操作都是响应式的：

-   push()、unshift()
-   pop()、shift()
-   splice()
-   sort()
-   reverse()

**数组非响应式写法**：this.arr[0] = "change"，注意这种基于下标的方式更新，Vue并不会监听。替代方案：

-   使用splice()：this.arr.splice(0, 1, "change")
-   使用Vue提供的set()方法：Vue.set(arr, 0, "change")

```vue
<body>
    <div id="div1">
        <ul>
            <li v-for="item in arr">{{item}}</li>
        </ul>
        <button @click="change">change</button>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            arr: ["a", "b", "c", "d"]
        },
        methods: {
            change() {
                // this.arr[0] = "change";
                // this.arr.splice(0, 1, "change");
                Vue.set(this.arr, 0, "change");
            }
        }
    });
</script>
```

## 2.4 购物车案例

### 框架搭建

-   index.html

```html
<!DOCTYPE html>
<html>
  <title>购物车案例</title>
  <head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <div id="app">
      <div v-if="books.length">
        <table>
          <thead>
            <tr>
              <th></th>
              <th>书籍名称</th>
              <th>出版日期</th>
              <th>价格</th>
              <th>数量</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(book, idx) in books">
              <td>{{book.id}}</td>
              <td>{{book.name}}</td>
              <td>{{book.date}}</td>
              <td>{{book.price | finalPrice}}</td>
              <td>
                <button
                  @click="decrement(idx)" v-bind:disabled="book.count <= 1">-</button>
                {{book.count}}
                <button @click="increment(idx)">+</button>
              </td>
              <td>
                <button @click="removeHandle(idx)">移除</button>
              </td>
            </tr>
          </tbody>
        </table>
        <h2>总价格：{{totalPrice | finalPrice}}</h2>
      </div>
      <div v-else>
        <h2>购物车为空!</h2>
      </div>
    </div>

    <script src="../../js/vue.js"></script>
    <script src="main.js"></script>
  </body>
</html>
```

-   style.css

```css
table {
  border: 1px solid #e9e9e9;
  border-collapse: collapse;
  border-spacing: 0;
}

th,
td {
  padding: 8px 16px;
  border: 1px solid #a39797;
  text-align: left;
}

th {
  background-color: #f7f7f7;
  color: #5c6b77;
  font-weight: 600;
}
```

-   main.js

```javascript
const app = new Vue({
    el: '#app',
    data: {
        books: [
            {
                id: 1,
                name: "算法导论",
                date: "2006-01",
                price: 85.0,
                count: 1
            },
            {
                id: 2,
                name: "算法",
                date: "2008-01",
                price: 75.0,
                count: 1
            },
            {
                id: 3,
                name: "Unix编程艺术",
                date: "2010-03",
                price: 105.3,
                count: 2
            },
            {
                id: 4,
                name: "Java编程思想",
                date: "2003-01",
                price: 100.5,
                count: 1
            }
        ]
    },
    filters: {
        finalPrice(price) {
            return "￥" + parseFloat(price).toFixed(2);
        }
    },
    methods: {
        decrement(idx) {
            this.books[idx].count--;
        },
        increment(idx) {
            this.books[idx].count++;
        },
        removeHandle(idx) {
            this.books.splice(idx, 1);
        }
    },
    computed: {
        totalPrice() {
            let res = 0.0;
            for (let i = 0; i < this.books.length; ++i) {
                res += this.books[i].count * this.books[i].price;
            }
            return res;
        }
    },
});
```

### 过滤器

过滤器也是Options入参的可选项，本质上还是一个函数，用法：**变量 | filter**，变量会被当做过滤器的入参：

```javascript
filters: {
    finalPrice(price) {
        return "￥" + parseFloat(price).toFixed(2);
    }
}
// html
<td>{{book.price | finalPrice}}</td>
```

### 禁用button

当商品的数量为1时，此时不能再进行减操作，可以禁用button：

```html
<button @click="decrement(idx)" v-bind:disabled="book.count <= 1">-</button>
```

### 购物车为空提示

当删除购物车中所有商品时，应该显示提示：购物车为空。可以将之前购物车的逻辑放入到一个div标签中，并通过v-if来判断books.length是否大于0，在通过v-else来显示提示内容：

```html
<div id="app">
    <div v-if="books.length">
        ...
    </div>
    <div v-else>
        购物车为空！
    </div>
</div>
```

### 按钮点击事件

点击+按钮对应商品数量增加，点击-按钮对应商品数量减少，点击移除，对应商品行消失：

```javascript
methods: {
    decrement(idx) {
        this.books[idx].count--;
    },
    increment(idx) {
    	this.books[idx].count++;
    },
    removeHandle(idx) {
    	this.books.splice(idx, 1);
	}
}
```

### 购物车总价格

购物车总价格可以通过计算属性来获取：

```javascript
computed: {
    totalPrice() {
        let res = 0.0;
        for (let i = 0; i < this.books.length; ++i) {
            res += this.books[i].count * this.books[i].price;
        }
        return res;
    }
},
```

### for循环的其它形式

for循环在es6中可以简写：

-   for idx in books：idx是索引，不是book对象

```javascript
// 注意这种方式i let idx in ...，idx是索引
let res = 0.0;
for (let idx in this.books) {
    res += this.books[idx].count * this.books[idx].price;
}
return res;
```

-   for book of books：book是对象

```javascript
let res = 0.0;
for (let item of this.books) {
    res += item.count * item.price;
}
return res;
```

高阶函数reduce：

```javascript
// 标准写法
return this.books.reduce(function (pre, book) {
    return pre + book.price * book.count;
}, 0);

// =>简写形式
return this.books.reduce((pre, book) => { return pre + book.price * book.count }, 0);
```

## 2.5 表单绑定v-model

### 实现

表单控件在实际开发中非常常见，特别是用户信息的提交，需要大量的表单。Vue使用v-model指令来实现**表单元素和数据的双向绑定**。

```html
<body>
    <div id="div1">
        <input v-model="message">
        {{message}}
        </input>
    </div>
</body>
<script>
    const app = new Vue({
        el: '#div1',
        data: {
            message: 'sherman'
        }
    });
</script>
```

### 原理

对于上面的<input>标签和message之间的双向绑定，其实相当于两个命令：

-   :value="message"：v-bind指令，将message绑定到<input>的value上
-   @input="message=$event.target.value"：v-on指令监听input变化，并绑定到message上

```html
<body>
    <div id="div1">
        <!--
			<input v-model="message">
				{{message}}
			</input>
		-->

        <!-- v-model等同于以下两个操作 -->
        <input @input="message=$event.target.value" :value="message" />
        {{message}}
    </div>
</body>
```

### v-model和radio结合

v-model绑定绑定某个属性时（例如sex）正好能形成互斥效果，和radio功能类似：

```html
<body>
    <div id="div1">
        <!--
			1. name指定相同时，提交表单时不能重复，才能达到只选择一个的效果
			2. 当v-model绑定都是绑定sex时，name="sex"可以省略
		-->
        <label for="male">
            <input type="radio" id="male" name="sex" value="男" v-model="sex" />男
        </label>
        <label for="female">
            <input type="radio" id="female" name="sex" value="女" v-model="sex" />女
        </label>
        <h2>您选择的性别是：{{sex}}</h2>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            sex: "男"
        }
    });
</script>
```

### v-model和checkbox

-   单选框

```html
<body>
    <div id="div1">
        <!-- 单选框 -->
        <label for="license">
            <input type="checkbox" id="license" v-model="isAgree">同意协议</label>
        </label>
    <h2>是否同意协议：{{isAgree}}</h2>
    <button :disabled="!isAgree">下一步</button>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            isAgree: false,
        }
    });
</script>
```

-   多选框

```html
<body>
    <div id="div1">
        <!-- 多选框 -->
        <label for="play">
            <input type="checkbox" id="play" value="play" v-model="hobbies">play
        </label>
        <label for="music">
            <input type="checkbox" id="music" value="music" v-model="hobbies">music
        </label>
        <label for="eat">
            <input type="checkbox" id="eat" value="eat" v-model="hobbies">eat
        </label>
        <label for="sleep">
            <input type="checkbox" id="sleep" value="sleep" v-model="hobbies">sleep
        </label>
        <h2>您的爱好是：{{hobbies}}</h2>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            hobbies: []
        }
    });
</script>
```

### v-model和select

-   单选

```html
<body>
    <div id="div1">
        <!-- 单选 -->
        <select id="fruit" name="fruit" v-model="fruit">
            <option name="香蕉">香蕉</option>
            <option name="苹果">苹果</option>
            <option name="榴莲">榴莲</option>
            <option name="葡萄">葡萄</option>
        </select>
        <h2>您选择的水果是：{{fruit}}</h2>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            fruit: "榴莲"
        }
    });
</script>
```

-   多选

```html
<body>
    <div id="div1">
        <!-- 多选 -->
        <select id="fruits" name="fruits" v-model="fruits" multiple>
            <option name="香蕉">香蕉</option>
            <option name="苹果">苹果</option>
            <option name="榴莲">榴莲</option>
            <option name="葡萄">葡萄</option>
        </select>
        <h2>您选择的水果是：{{fruits}}</h2>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            fruits: []
        }
    });
</script>
```

### 值绑定

在前面的示例中，所有的value都是固定写死的，真实环境中，value应该是从服务器端获取的，然后动态赋值的。可以通过v-bind:value动态的value进行绑定值，这就是**值绑定**。

```html
<body>
    <div id="div1">
        <!-- 在label标签上进行循环 -->
        <label v-for="hobby in hobbiesFromDB" :for="hobby">
            <input
                   type="checkbox"
                   :value="hobby"
                   :id="hobby"
                   v-model="hobbies"
            />{{hobby}}
        </label>
        <h2>您选择的爱好是：{{hobbies}}</h2>
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            hobbies: [],
            // 假设这些值是从服务器（或者DB）获取
            hobbiesFromDB: ["吃", "喝", "睡", "玩", "code"]
        }
    });
</script>
```

### 修饰符

v-model的修饰符和class的修饰符类似，直接使用.符号即可。v-model主要有三个修饰符：

-   lazy：v-model默认情况下是在input事件中同步输入框的数据，即是实时显示的，lazy修饰符会在enter或者失去焦点之后显示
-   number：默认情况下，输入框输入的数据是被当做字符串处理的，number修饰符可以将内容当做数字处理
-   trim：trim修饰符可以自动去除输入内容左右两端的空格

```html
<body>
    <div id="div1">
        <input type="text" v-model.lazy="msg" />{{msg}}
        <br />
        <input type="text" v-model.number="foo" />{{foo}}-{{typeof foo}}
        <br />
        <input type="text" v-model.trim="bar" />#{{bar}}#
    </div>
</body>
<script>
    const app = new Vue({
        el: "#div1",
        data: {
            msg: "msg",
            foo: "foo",
            bar: "bar"
        }
    });
</script>
```