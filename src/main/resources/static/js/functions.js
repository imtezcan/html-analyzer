function analyze() {
    var endpoint = "/analyze?url=" + $('#url').val();

    $("#resultsBlock").load(endpoint, function() {
        $("tr.link").each(function() {
            setTimeout(validate($(this)), 1000);
        })
    });
    return false;
}

function validate(row) {
    var endpoint = "/validate?url=" + row.find('a').attr('href');
    $.get(endpoint, function(data) {
        row.find('.working').text(data.working);
        row.find('.reason').text(data.reason);
    });
    return false;
}