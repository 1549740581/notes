// CommonJS导入方式
let { add } = require("./js/mathUtil.js");
// es6导入方式
import { name, age, height } from "./js/info.js";

console.log(name, age, height);
console.log(add(1, 2));

// 加载css
require("./css/foo.css");

// 加载less
require("./css/bar.less");

// 配置Vue
import Vue from 'vue';

// 将原本在Vue实例中的东西先抽取出来，之后在Vue实例中注册App组件即可
// const App = {
//     template: `
//         <div>
//             <h2>{{message}}</h2>
//             <button @click="onClick">点击</button>
//         </div>
//     `,
//     data() {
//         return {
//             message: 'msg'
//         }
//     },
//     methods: {
//         onClick() {
//             console.log("click");
//         }
//     },
// }

// import App from './vue/app.js';

// 最终方案：使用.vue文件进行分离
import App from './vue/Vue';
new Vue({
    el: '#app',
    // 在template中用了一个自定义子组件
    template: '<App/>',
    components: {
        App
    }
})