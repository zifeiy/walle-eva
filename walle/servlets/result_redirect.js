var result_redirect = {};

result_redirect.get = function (req, res) {
    var report_id = req.query.report_id;
    var org_id = req.query.org_id;
    if (report_id == null) {
        res.status(404);
        res.render('404');
        return;
    }
    console.log("org_id= " + org_id);
    if (org_id != null && org_id[0] == 'A') {
        org_id = org_id.substring(1);
    }
    if (org_id == null || org_id == '883000' || org_id == '999999' || org_id == '883999') {
        org_id = null;
    }
    
    req.session.org_id = org_id;
    console.log("result_redirect: " + req.session.org_id);
    res.redirect(`/front/${report_id}.html`);
}

result_redirect.post = function (req, res) {

}

module.exports = result_redirect;