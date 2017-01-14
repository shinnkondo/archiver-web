
const webpack = require('webpack');
const path = require('path');

const { CheckerPlugin } = require('awesome-typescript-loader')
const SplitByPathPlugin = require('webpack-split-by-path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
// const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = [{
    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.jsx']
    },
    entry: {
        main: './src/index.ts'
    },
    output: {
        path: path.resolve(__dirname, '../build/resources/main/public'),
        filename: '[name].js',
        chunkFilename: '[name].js',
    },
    module: {
        rules: [
            { test: /\.ts$/, loaders: ["awesome-typescript-loader", 'angular2-template-loader'] },
            {
                test: /\.scss$/,
                loaders: ["raw-loader", "sass-loader"],
                exclude: /\.async\.scss$/                
            },
            // Loading angular template
            {
                test: /\.(html|css)$/,
                loader: 'raw-loader',
                exclude: /\.async\.(html|css)$/
            },
            /* Async loading. */
            {
                test: /\.async\.scss$/,
                loader: ExtractTextPlugin.extract({
                    loader: 'css-loader!sass-loader'
                })
            },
            // fonts for font awesome
            { test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "url-loader?limit=10000&mimetype=application/font-woff" },
            { test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "file-loader" }
        ]
    },

    plugins: [
        new ExtractTextPlugin({ filename: 'global.css', disable: false, allChunks: true }),
        new webpack.LoaderOptionsPlugin({
            options: {
                sassLoader: {
                     includePaths: [path.resolve('./node_modules')] // So that @material/x can find sibilings
                },
                context: __dirname,
            },
        }),
        new CheckerPlugin(),
        new SplitByPathPlugin([{
            name: 'vendor',
            path: path.join(__dirname, 'node_modules')
        }], {
                manifest: 'app-entry'
            }),
        // Could not make it not ignore vendor.js. Use it to copy html for now.
        //            new HtmlWebpackPlugin({
        //                template: 'src/index.html',
        //                chunks: []
        //            }),
        new webpack.ContextReplacementPlugin(
            /angular(\\|\/)core(\\|\/)(esm(\\|\/)src|src)(\\|\/)linker/,
            __dirname
        ) // for angular2 to supress warning during build.
    ],
    devtool: "source-map"
}
]

