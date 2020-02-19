const baseConfig = require('./base.config');
const webpackMerge = require('webpack-merge');

// 将开发环境和base环境进行导出
module.exports = webpackMerge(baseConfig, {
    devServer: {
        contentBase: './dist',
        inline: true
    }
})