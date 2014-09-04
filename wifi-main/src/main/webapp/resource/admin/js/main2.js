/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loadCoungLog(){
     var json_str = {
         "wbcount":0,
         "sjcount":0,
         "qqcount":0,
         "wxcount":0,
         "allcount":0
     }; 
     $.ajax({
            type: "post", 
            url : "countLog",
            dataType:'json',
            
            success: function(json){
                 $.each(json, function(i, item) {
                    if(item.type === "wx"){
                        json_str.wxcount = json_str.wxcount + 1;               
                    }else if(item.type === "blog"){
                        json_str.wbcount = json_str.wbcount + 1;               
                    }else if(item.type === "phmsg"){
                        json_str.sjcount = json_str.sjcount + 1;               
                    }else if(item.type === "qq"){
                        json_str.qqcount = json_str.qqcount + 1;               
                    }
                    json_str.allcount = json_str.allcount + 1;
            }); 
            var html = "<p>全部客流累计人数:" + json_str.allcount + "人</p>"
                +"<p>手机客流累计人数:" + json_str.sjcount + "人</p>"
                +"<p>微博客流累计人数:" + json_str.wbcount + "人</p>"
                +"<p>QQ客流累计人数:" + json_str.qqcount + "人</p>"
                +"<p>微信客流累计人数:" + json_str.wxcount + "人</p>";
					
		$("#custonnum").html("");
		$("#custonnum").append(html);
        }  
   });

}
var currentDate = new Date();
function getTwoMonthDate(str){
    var dateArray = new Array();
    if(str === "TMonth"){
        var startDate = new Date();
        startDate.setDate(1);
        dateArray[0] = new Date(startDate);
        startDate.setMonth(startDate.getMonth() + 1);
        var endDate = new Date(startDate);
        endDate.setDate(0);  
        dateArray[1] = endDate;
    }else if(str === "nextMonth"){
        var startDate = new Date(currentDate);
        var endDate = new Date(currentDate);
        startDate.setMonth(currentDate.getMonth() + 1);
        startDate.setDate(1);
        endDate.setMonth(currentDate.getMonth() + 2);
        endDate.setDate(0);
        dateArray[0] = startDate;
        dateArray[1] = endDate;
        currentDate = startDate;
    }else if(str === "lastMonth"){
        var startDate = new Date(currentDate);
        var endDate = new Date(currentDate);
        startDate.setMonth(currentDate.getMonth() - 1);
        startDate.setDate(1);
        endDate.setMonth(currentDate.getMonth());
        endDate.setDate(0);
        dateArray[0] = startDate;
        dateArray[1] = endDate;
        currentDate = startDate;
    }else if(str === "lastTT"){
        var endDate = new Date();
        var startDate = new Date(endDate.getTime() - 1000*60*60*24 * 30);
        dateArray[0] = startDate;
        dateArray[1] = endDate;
    }
    return dateArray;
}
function getWifiLogMonth(){
        //var dateArray = getTwoMonthDate(str);
        var startDate_str = $("#inputDateStart_1").val();
        var inputDateEnd_str = $("#inputDateEnd_1").val();
        var startArrary = startDate_str.split("-");
        var endArrary = inputDateEnd_str.split("-");
        var startDate = new Date(startArrary[0],startArrary[1] - 1,startArrary[2]);
        var endDate = new Date(endArrary[0],endArrary[1] - 1,endArrary[2]);
        var data = new Array();
        var i = 0;
        while(startDate.getTime() <= endDate.getTime()){
                var times = dateToString(startDate);
                data[i] = { "time":times,
                            "wbcount":0,
                            "sjcount":0,
                            "qqcount":0,
                            "wxcount":0,
                            "allcount":0
                                   };
                i ++;
                startDate = new Date(startDate.getTime() + 1000*60*60*24 * 1);
        }
        var count = 0;
        $.ajax({ 
            type: "post", 
            url : "validateLogDate",
            dataType:'json',
            data:{    
             "startDate" : startDate_str,    
             "endDate" : inputDateEnd_str    
            },
           
            success: function(json){
                $.each(json, function(i, item) {
                    var date = dateToString(new Date(item.dt));
                    for (i = 0; i < data.length; i ++){
                        if(data[i].time === date){
                            if(item.type === "wx"){
                                data[i].wxcount = data[i].wxcount + 1;               
                            }else if(item.type === "blog"){
                                data[i].wbcount = data[i].wbcount + 1;               
                            }else if(item.type === "phmsg"){
                                data[i].sjcount = data[i].sjcount + 1;               
                            }else if(item.type === "qq"){
                                data[i].qqcount = data[i].qqcount + 1;               
                            }
                             data[i].allcount = data[i].allcount + 1;
                        }
                    }
                    count = count + 1;
            });
            showLineChart('chart',data);
            var html = "<table width='100%' border='2' color='blue' cellspacing='1' cellpadding='1'>"
					+ "<tr>"
					+"<th>日期</th>"
					+"<th>合计</th>"
					+"<th>手机客流 </th>"
					+"<th>微博客流</th>"
                                        +"<th>微信客流</th>"
                                        +"<th>QQ客流</th>"
					+ "</tr>";
          for (i = 0; i < data.length; i++){
              html = html + 
                "<tr>"
                    +"<td>" + data[i].time + "</td>"
                    +"<td>" + data[i].allcount + "</td>"
                    +"<td>" + data[i].sjcount + "</td>"
                    +"<td>" + data[i].wbcount + "</td>"
                    +"<td>" + data[i].wxcount + "</td>"
                    +"<td>" + data[i].qqcount + "</td>"
                +"<tr>";
            }   
           $("#bbbox").html("");
           $("#bbbox").append(html); 
        }  
    });
   }

function getWifiLogMonthStr(str){
        var dateArray = getTwoMonthDate(str);
        var startDate_str = dateToString(dateArray[0]);
        var inputDateEnd_str = dateToString(dateArray[1]);
        var startArrary = startDate_str.split("-");
        var endArrary = inputDateEnd_str.split("-");
        var startDate = new Date(startArrary[0],startArrary[1] - 1,startArrary[2]);
        var endDate = new Date(endArrary[0],endArrary[1] - 1,endArrary[2]);
        var data = new Array();
        var i = 0;
        while(startDate.getTime() <= endDate.getTime()){
                var times = dateToString(startDate);
                data[i] = { "time":times,
                            "wbcount":0,
                            "sjcount":0,
                            "qqcount":0,
                            "wxcount":0,
                            "allcount":0
                                   };
                i ++;
                startDate = new Date(startDate.getTime() + 1000*60*60*24 * 1);
        }
        var count = 0;
        $.ajax({ 
            type: "post", 
            url : "validateLogDate",
            dataType:'json',
            data:{    
             "startDate" : startDate_str,    
             "endDate" : inputDateEnd_str    
            },
           
            success: function(json){
                $.each(json, function(i, item) {
                    var date = dateToString(new Date(item.dt));
                    for (i = 0; i < data.length; i ++){
                        if(data[i].time === date){
                            if(item.type === "wx"){
                                data[i].wxcount = data[i].wxcount + 1;               
                            }else if(item.type === "blog"){
                                data[i].wbcount = data[i].wbcount + 1;               
                            }else if(item.type === "phmsg"){
                                data[i].sjcount = data[i].sjcount + 1;               
                            }else if(item.type === "qq"){
                                data[i].qqcount = data[i].qqcount + 1;               
                            }
                             data[i].allcount = data[i].allcount + 1;
                        }
                    }
                    count = count + 1;
            });
            var html = "<table width='100%' border='2' color='blue' cellspacing='1' cellpadding='1'>"
					+ "<tr>"
					+"<th>日期</th>"
					+"<th>合计</th>"
					+"<th>手机客流 </th>"
					+"<th>微博客流</th>"
                                        +"<th>微信客流</th>"
                                        +"<th>QQ客流</th>"
					+ "</tr>";
          for (i = 0; i < data.length; i++){
              html = html + 
                "<tr>"
                    +"<td>" + data[i].time + "</td>"
                    +"<td>" + data[i].allcount + "</td>"
                    +"<td>" + data[i].sjcount + "</td>"
                    +"<td>" + data[i].wbcount + "</td>"
                    +"<td>" + data[i].wxcount + "</td>"
                    +"<td>" + data[i].qqcount + "</td>"
                +"<tr>";
            }   
           $("#bbbox").html("");
           $("#bbbox").append(html); 
        }  
    });
   }


function showLineChart(divID, data) {
    
    $("#" + divID).html("");
    
    var xpost = new Array();
    var ypost_1 = new Array();
    var ypost_2 = new Array();
    var ypost_3 = new Array();
    var ypost_4 = new Array();
    
    for (i = 0; i < data.length; i ++){
        xpost[i] = data[i].time;
        ypost_1[i] = data[i].sjcount;
        ypost_2[i] = data[i].wbcount;
        ypost_3[i] = data[i].wxcount;
        ypost_4[i] = data[i].qqcount;
    }
    plot = $.jqplot(divID, [ypost_1, ypost_2, ypost_3, ypost_4], {
        title: 'wifi折线图', //图表表头标题
        seriesDefaults:{
                
                pointLabels: { show: true },
                rendererOptions: {
                varyBarColor: true
                }
            },
            
        series:[
           
            {label: '手机'},{label: '微博'},{label: '微信'},{label: 'QQ'}
            ],
        legend: {  show:true, 
                    location: 'nw',
                    xoffset: 12,   
                    yoffset: 12
                },
        axes: {
            //xaxis: {min: 0, max: 8}, //准确控制x轴最大值及最小值
           // yaxis: {min: 0, max: 20, numberTicks: 5}, //准确控制y轴最大值及最小值,间隔个数
            xaxis: {
                
                renderer: $.jqplot.CategoryAxisRenderer,
                ticks: xpost,
                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                tickOptions: {
                    angle: -60,
                    fontSize: '8pt',
                    showMark: true,
                    showGridline: false
                }
            }
        },
//series: [{ color: '#5FAB78'}],                    //定义趋势线颜色   
        highlighter: {
            showMarker:true,
            show:true,    
            tooltipAxes: 'y',
            useAxesFormatters: true,
            formatString:'<table> \
                <tr><td>数目:</td><td>%d</td></tr> \
            </table>'
        }

    });
}