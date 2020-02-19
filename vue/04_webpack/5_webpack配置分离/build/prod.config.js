const uglifyWebpackPlugin = require('uglifyjs-webpack-plugin');

const webpackMerge = require('webpack-merge');
const baseConfig = require('./base.config');

// 将生产环境和base环境进行导出
module.exports = webpackMerge(baseConfig, {
    plugins: [
        new uglifyWebpackPlugin()
    ]
})