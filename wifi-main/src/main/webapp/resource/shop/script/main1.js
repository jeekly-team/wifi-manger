/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getWifiLogHours(str) {
    var startDate = getDate(str);
   // var endDate = getDate(str);
    var data = new Array();
    var count = 0;
    for (i = 0; i < 24; i++) {
        var times = i + "-" + (i + 1);
        data[i] = {"time": times,
            "count": 0,
            "centum": 0,
            "totalnum": 0
        };
    }
    $.ajax({
        type: "post",
        url: "validateLogDate",
        dataType: 'json',
        data: {
            "startDate": startDate,
            "endDate": startDate
        },
        success: function(json) {
            $.each(json, function(i, item) {
                var date = getDate_s(item.dt);
                var hourse = date.getHours();
                //data[hourse].time = hourse - 1 + "-" + hourse;
                data[hourse].count = data[hourse].count + 1;
                data[hourse].centum = data[hourse].centum + 1;
                count = count + 1;
            });
            var title = "极源wifi图（"+ startDate + ")";
            showChart('chart', data, title);
            var html = " <table class='table table-striped table-bordered table-condensed text-center'>"
                    + "<tr class='info text-primary'>"
                    + "<th>时间</th>"
                    + "<th>连网人数</th>"
                    + "<th>连网人次 </th>"
                    + "<th>百分比</th>"
                    + "</tr>";
            for (i = 0; i < data.length; i++) {
                html = html +
                        "<tr>"
                        + "<td>" + data[i].time + "</td>"
                        + "<td>" + data[i].count + "</td>"
                        + "<td>" + data[i].centum + "</td>"
                        + "<td>" + (count === 0 ? 0 : (data[i].count / count)) * 100 + "%</td>"
                        + "<tr>";
            }
            $("#bbbox").html("");
            $("#bbbox").append(html);
        }
    });
}
function getWifiLogDay(str) {
    var dateArrary = getTwoDate(str);
    var startDate = dateArrary[1];
    var beginDate = startDate;
    var endDate = dateArrary[0];
    var data = new Array();
    var i = 0;
    while (startDate.getTime() <= endDate.getTime()) {
        var times = dateToString(startDate);
        data[i] = {"time": times,
            "count": 0,
            "centum": 0,
            "totalnum": 0
        };
        i++;
        startDate = new Date(startDate.getTime() + 1000 * 60 * 60 * 24 * 1);
    }
    var count = 0;
    $.ajax({
        type: "post",
        url: "validateLogDate",
        dataType: 'json',
        data: {
            "startDate": dateToString(beginDate),
            "endDate": dateToString(endDate)
        },
        success: function(json) {
            $.each(json, function(i, item) {
                var date = dateToString(item.dt);
                for (i = 0; i < data.length; i++) {
                    if (data[i].time === date) {
                        data[i].count = data[i].count + 1;
                        data[i].centum = data[i].centum + 1;
                    }
                }
                count = count + 1;
            });
            var title = "极源wifi图（"+ dateToString(beginDate) + "到" + dateToString(endDate) + ")";
            showChart('chart', data, title);
            var html = "<table class='table table-striped table-bordered table-condensed text-center'>"
                        + "<tr class='info text-primary'>"
                        + "<th>时间</th>"
                        + "<th>连网人数</th>"
                        + "<th>连网人次 </th>"
                        + "<th>百分比</th>"
                        + "</tr>";
            for (i = 0; i < data.length; i++) {
                html = html +
                        "<tr>"
                        + "<td>" + data[i].time + "</td>"
                        + "<td>" + data[i].count + "</td>"
                        + "<td>" + data[i].centum + "</td>"
                        + "<td>" + (count === 0 ? 0 : (data[i].count / count)) * 100 + "%</td>"
                        + "<tr>";
            }
            $("#bbbox").html("");
            $("#bbbox").append(html);
        }
    });
}


function getWifiLogCC(obj) {
    var startDate_str = $("#inputDateStart").val();
    var inputDateEnd_str = $("#inputDateEnd").val();
    if(!validateDate(obj)) return;
    var startArrary = startDate_str.split("-");
    var endArrary = inputDateEnd_str.split("-");
    var startDate = new Date(startArrary[0], startArrary[1] - 1, startArrary[2]);
    var endDate = new Date(endArrary[0], endArrary[1] - 1, endArrary[2]);
    var data = new Array();
    var i = 0;
    while (startDate.getTime() <= endDate.getTime()) {
        var times = dateToString(startDate);
        data[i] = {"time": times,
            "count": 0,
            "centum": 0,
            "totalnum": 0
        };
        i++;
        startDate = new Date(startDate.getTime() + 1000 * 60 * 60 * 24 * 1);
    }
    var count = 0;
    $.ajax({
        type: "post",
        url: "validateLogDate",
        dataType: 'json',
        data: {
            "startDate": startDate_str,
            "endDate": inputDateEnd_str
        },
        success: function(json) {
            $.each(json, function(i, item) {
                var date = dateToString(item.dt);
                for (i = 0; i < data.length; i++) {
                    if (data[i].time === date) {
                        data[i].count = data[i].count + 1;
                        data[i].centum = data[i].centum + 1;
                    }
                }
                count = count + 1;
            });
            var title = "极源wifi图（"+ startDate_str + "到" + inputDateEnd_str + ")";
            showChart('chart', data, title);
            var html = "<table class='table table-striped table-bordered table-condensed text-center'>"
                    + "<tr class='info text-primary'>"
                    + "<th>时间</th>"
                    + "<th>连网人数</th>"
                    + "<th>连网人次 </th>"
                    + "<th>百分比</th>"
                    + "</tr>";
            for (i = 0; i < data.length; i++) {
                html = html +
                        "<tr>"
                        + "<td>" + data[i].time + "</td>"
                        + "<td>" + data[i].count + "</td>"
                        + "<td>" + data[i].centum + "</td>"
                        + "<td>" + (count === 0 ? 0 : (data[i].count / count)).toFixed(4) * 100 + "%</td>"
                        + "<tr>";
            }
            $("#bbbox").html("");
            $("#bbbox").append(html);
        }
    });
}

function showChart(divID, data, title) {
    $("#" + divID).html("");
    $("#" + divID).show();
    var ypost = new Array();
    var xpost = new Array();
    for (i = 0; i < data.length; i++) {
        xpost[i] = data[i].time;
        ypost[i] = data[i].count;
    }
    $.jqplot.config.enablePlugins = true;
    plot1 = $.jqplot(divID, [ypost], {
        animate: !$.jqplot.use_excanvas,
        seriesDefaults: {
            renderer: $.jqplot.BarRenderer,
            pointLabels: {show: false},
            rendererOptions: {
                varyBarColor: true
            }
        },
        axes: {
            xaxis: {
                pad:0.2,
                renderer: $.jqplot.CategoryAxisRenderer,
                ticks: xpost,
                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                tickOptions: {
                    angle: -60,
                    fontSize: '6pt',
                    showMark: false,
                    showGridline: false
                }
            },
            yaxis: {
                tickOptions: {
                    fontSize: '6pt'
                }
            }  
        },
        title: {
            text: title, 
            show: true,
            fontSize: '8pt'
        }, 
        highlighter: {show: false}
    });
}


