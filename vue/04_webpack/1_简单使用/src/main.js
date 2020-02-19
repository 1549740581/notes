// CommonJS导入方式
let { add } = require("./mathUtil.js");
// es6导入方式
import { name, age, height } from "./info.js";

console.log(name, age, height);
console.log(add(1, 2));