function strRepeat(str, count) {
    var ret = "";
    for (var i = 0; i < count; i++) {
        ret += str;
    }
    return ret;
}

function setCat(catId, dataName) {
    var locationData = layui.data(dataName),
        record = locationData.list ? locationData.list : "";
    if (record) {
        for (var i = 0; i < record.length; i++) {
            if (record[i].id == catId) {
                return record[i].title;
            }
        }
    }
}