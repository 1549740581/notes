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
            {
                test: /\.css$/,
                // css-loader只负责将css文件进行加载
                // style-cloader负责将样式加入到DOM中
                // 使用多个loader时候，读取顺序是从右往左进行的
                use: [
                    'style-loader',
                    'css-loader'
                ]
            },
            {
                test: /\.less$/,
                use: ['style-loader', 'css-loader', 'less-loader']
            },
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
            },
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
            },
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
        },
        // 配置哪些文件可以省略后缀名
        extensions: ['.js', '.css', '.vue']
    }
}