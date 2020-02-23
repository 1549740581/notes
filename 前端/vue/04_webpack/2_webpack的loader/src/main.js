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