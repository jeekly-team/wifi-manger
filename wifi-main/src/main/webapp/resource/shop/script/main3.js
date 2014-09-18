/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loadOldNewUser(){
    $.ajax({
            type: "post", 
            url : "countAllUser",
            dataType:'json',
            success: function(json){
                /*
                var html = "<p>累计老用户数:" + json[0][0] + "人</p>"
                +"<p>老顾客来店人数<span>(最近三十天)</span>:" + json[0][1] + "人</p>"
                +"<p>新顾客来店人数<span>(最近三十天)</span>:" + (json[0][2] - json[0][1]) + "人</p>"
                +"<p>转换为老顾客人数<span>(最近三十天)</span>:" + json[0][3] + "人 <span>" + (json[0][2] - json[0][1] === 0 ? 0 : (json[0][3]/(json[0][2] - json[0][1])).toFixed(4) *100)  + "%</sapn></p>";
                */
                var html = "<ul class='list-group'>"
                           +"<li class='list-group-item list-group-item-success'>累计老用户数:<span class='badge'>" + json[0][0] + "人</span></li>"
                             +"<li class='list-group-item list-group-item-info'>老顾客来店人数(最近三十天):<span class='badge'>" + json[0][1] + "人</span></li>"
                             +"<li class='list-group-item list-group-item-warning'>新顾客来店人数(最近三十天):<span class='badge'>" + (json[0][2] - json[0][1]) + "人</span></li>"
                             +"<li class='list-group-item list-group-item-danger'>转换为老顾客人数(最近三十天):<span class='badge'>" + json[0][3] + "人 </span><span class='badge'>" + (json[0][2] - json[0][1] === 0 ? 0 : (json[0][3]/(json[0][2] - json[0][1])).toFixed(4) *100) + "%</span></li>"
                          +"</ul>";
		$("#custonnum_ss").html("");
		$("#custonnum_ss").append(html);
            }
    });
}

function getCountLog(obj){
    var startDate_str = $("#inputDateStart_2").val();
    var inputDateEnd_str = $("#inputDateEnd_2").val();
    if(!validateDate(obj)) return;
    var startArrary = startDate_str.split("-");
    var endArrary = inputDateEnd_str.split("-");
    var startDate = new Date(startArrary[0],startArrary[1] - 1,startArrary[2]);
    var endDate = new Date(endArrary[0],endArrary[1] - 1,endArrary[2]);
    var data_array = new Array();
    var it = 0;
     $.ajax({
            type: "post", 
            url : "countLogUser",
            dataType:'json',
            data:{    
             "startDate" : startDate_str,    
             "endDate" : inputDateEnd_str    
            },
            success: function(json){
                 var html = " <table  class='table table-striped table-bordered table-condensed text-center'>"
                             + "<tr class='info text-primary'>"
                            +"<th>日期</th>"
                            +"<th>老顾客</th>"
                            +"<th>新顾客 </th>"
                            +"<th>合计</th>"
                            + "</tr>";
                while(startDate.getTime() <= endDate.getTime()){
                    var date_str = dateToString(startDate);
                    var newUserCount = 0; //本日内新用户数量
                    var oldUserCount = 0; //本日内老用户数量
                    var totalUserCount = 0;
                    for (i = 0; i < json.length; i ++){
                         if(json[i][1] === date_str && json[i][2] === "new"){
                             newUserCount = newUserCount + json[i][0];
                             totalUserCount = totalUserCount + json[i][0];
                         }else if(json[i][1] === date_str && json[i][2] === "old"){
                             oldUserCount = oldUserCount + json[i][0];
                             totalUserCount = totalUserCount + json[i][0];
                         }
                     }
                    var json_user = {
                        "date_str":date_str,
                        "newUserCount":newUserCount,
                        "oldUserCount":oldUserCount,
                        "totalUserCount":totalUserCount  
                    };
                    data_array[it++] = json_user;
                    startDate = new Date(startDate.getTime() + 1000*60*60*24 * 1);
                    html = html + 
                    "<tr>"
                        +"<td>" + date_str + "</td>"
                        +"<td>" + oldUserCount + "</td>"
                        +"<td>" + newUserCount + "</td>"
                        +"<td>" + totalUserCount + "</td>"
                    +"<tr>";
            }
            var title = "极源新老顾客图（"+ startDate_str + "到" + inputDateEnd_str + ")";
            showPlot('chart',data_array,title);
           $("#bbbox").html("");
           $("#bbbox").append(html);  
           
        }  
   });
}

function showPlot(divID, data, title) {
    
    $("#" + divID).html("");
    $("#" + divID).show();
    var xpost = new Array();
    var ypost_1 = new Array();
    var ypost_2 = new Array();
    
    for (i = 0; i < data.length; i ++){
        xpost[i] = data[i].date_str;
        ypost_1[i] = data[i].newUserCount;
        ypost_2[i] = data[i].oldUserCount;

    }
    plot = $.jqplot(divID, [ypost_1, ypost_2], {
         seriesDefaults: {
            renderer: $.jqplot.BarRenderer,
            pointLabels: {show: true},
            rendererOptions: {
                varyBarColor: false
            }
        },
        series:[ 
            {label: '新顾客'},{label: '老顾客'}
            ],
        legend: {  show:true, 
                location: 'nw',
                xoffset: 12,   
                yoffset: 12
            },
        axesDefaults: {
            useSeriesColor: true //如果有多个纵（横）坐标轴，通过该属性设置这些坐标轴是否以不同颜色显示
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
                    showMark: true,
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
            fontSize: '9pt'
        }, 
        highlighter: {show: true}
    });
}

