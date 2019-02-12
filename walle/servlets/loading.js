var fs = require('fs');

var loading = {};

loading.get = function (req, res) {
    var filename = req.query['filename'];
    var filepath = "D:\\eva\\public_resources\\" + filename;
    
    var csvFilename = filename.substring(0, filename.length-4) + ".csv";
    var csvFilepath = filepath.substring(0, filepath.length-4) + ".csv";
    var xlsFilename = filename.substring(0, filename.length-4) + ".xls";
    var xlsFilepath = filepath.substring(0, filepath.length-4) + ".xls";

    if ((fs.existsSync(csvFilepath) == true || fs.existsSync(xlsFilepath) == true) && 
            (fs.existsSync(csvFilepath + ".ok") == true || fs.existsSync(xlsFilepath + ".ok") == true)
            ) {
        var trueFilename = (fs.existsSync(xlsFilepath) == true) ? xlsFilename : csvFilename;
        var trueFilepath = (fs.existsSync(xlsFilepath) == true) ? xlsFilepath : csvFilepath;
        console.log("[loading]: " + trueFilepath + " exists!");

        var outFilepath = `${__dirname}/../public/reports/${trueFilename}`;

        var readable = fs.createReadStream( trueFilepath );
        // 创建写入流
        var writable = fs.createWriteStream( outFilepath );
        // 通过管道来传输流
        readable.pipe( writable );

        readable.on('close', ()=> { res.render('download', { filename : trueFilename }) });

        // res.render('download', { filename : filename });
    }
    else {
        console.log("[loading]: " + filepath + " NOT exists!");
        res.render("loading", { gifId : parseInt(Math.random() * 22) });
    }
}

loading.post = function (req, res) {

}

module.exports = loading;