
var result = {};

var getFinalOrgId = function (sessionOrgId, requestOrgId) {
    console.log("result: session orgId=" + sessionOrgId + ", request orgId=" + requestOrgId);
    if (sessionOrgId == null) return requestOrgId;
    if (requestOrgId == null || requestOrgId == '') return sessionOrgId;
    for (var i = 0; i < 5; i ++) {
        if (sessionOrgId[i] != requestOrgId[i])
            return sessionOrgId;
    }
    return requestOrgId;
}

result.get = function (req, res) {
    var report_id = req.query["required_rpt_id"];
    var pageIndex = parseInt(req.query["pageIndex"]);
    console.log("req.query = " + JSON.stringify(req.query));
    if (report_id == null) {
        res.status(404);
        res.render('404');
        return;
    }
    var requests = {};
    var suffix = "";
    // if (req.session.org_id != null) {
    //     req.query.orgId = req.session.org_id;
    // }
    Object.keys(req.query).forEach(k => {
        if (k == 'required_rpt_id' || k == 'pageIndex') {
            ; // nothing
        }
        else {
            var v = req.query[k];
            if (k == 'orgId') {
                v = getFinalOrgId(req.session.org_id, v);
                console.log("result: final orgId=" + v);
            }
            if (v != null && v != '') {
                suffix += `&${k}=${encodeURIComponent(v)}`;
            }
        }
    });

    var resultUrl = `http://localhost:8090/${report_id}.html?pageIndex=${pageIndex}${suffix}`;
    var preUrl = `/result?required_rpt_id=${report_id}&pageIndex=${pageIndex<=1?1:pageIndex-1}${suffix}`;
    var nextUrl = `/result?required_rpt_id=${report_id}&pageIndex=${pageIndex+1}${suffix}`;
    var downloadUrl = `/download?required_rpt_id=${report_id}${suffix}`;
    res.render('result', {
        resultUrl: resultUrl,
        downloadUrl: downloadUrl,
        preUrl: preUrl,
        nextUrl: nextUrl
    });
}

result.post = function (req, res) {

}

module.exports = result;