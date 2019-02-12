var request = require('request');
var fs = require('fs');

var download = {};

download.get = function (req, res) {

    var suffix = "";
    var report_id = null;
    Object.keys(req.query).forEach(k => {
        var v = req.query[k];
        if (k == 'required_rpt_id') {
            report_id = v;
        }
        else {
            if (v != null && v != '') {
                if (suffix.length == 0) suffix += `?${k}=${encodeURIComponent(v)}`;
                else suffix += `&${k}=${encodeURIComponent(v)}`;
            }
        }
    });
    var url = `http://localhost:8090/${report_id}/download${suffix}`;
    console.log("URL = " + url);
    request(url, function (error, response, body) {
        console.log("body = " + body);
        var filepath = "" + body;
        var idx = -1;
        for (var i = 0; i < filepath.length; i ++) {
            if (filepath[i] == "\\") {
                idx = i;
            }
        }
        var filename = filepath.substr(idx+1);
        var outFilepath = `${__dirname}/../public/reports/${filename}`;

        // 判断文件是否存在，如果不存在，则跳转到loading界面
        // 这种情况用于处理大文件未能及时生成（从而导致浏览器等待太长时间后断开，即使最后生成了文件也无法获取）的情况
        if (fs.existsSync(filepath) == false || fs.existsSync(filepath + ".ok") == false) {
            console.log('file: ' + filepath + " not generated yet now!\r\nloading ...");

            var redirectUrl = "/loading?filename=" + filename;
            console.log("redirectUrl = " + redirectUrl);

            res.redirect(redirectUrl);

            return;
        }


        console.log('in filePath = ' + filepath);
        console.log('outFIlepath = ' + outFilepath);

        var readable = fs.createReadStream( filepath );
        // 创建写入流
        var writable = fs.createWriteStream( outFilepath ); 
        // 通过管道来传输流
        readable.pipe( writable );

        // res.render('download', { filename : filename });
        readable.on('close', ()=> { res.render('download', { filename : filename }) });
    });
};

download.post = function (req, res) {

};

module.exports = download;