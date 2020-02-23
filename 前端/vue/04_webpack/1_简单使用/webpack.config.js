const path = require("path");

module.exports = {
    entry: "./src/main.js",
    // 出口需要以path和filename形式存在
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js'
    }
}