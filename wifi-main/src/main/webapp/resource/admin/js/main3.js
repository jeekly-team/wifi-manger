/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 var count_data = new Array();
 
function loadOldNewUser(){
     var json_str = {
         "alloldcount":0,
         "lastttoldcount":0,
         "lastttnewcount":0,
         "changecounttt":0,
         "changebfb":0
     }; 

     var dateArray = getTwoMonthDate('lastTT');
     count_data =new Array();
     $.ajax({
            type: "post", 
            url : "countLog",
            dataType:'json',
            
            success: function(json){
                var it = 0;
                 $.each(json, function(i, item) {
                    var datetime = item.dt;
                    var isuser = false;
                    for (var i = 0; i < count_data.length; i ++){
                        if(item.wifiuser_id === count_data[i].user){
                            count_data[i].type = "old";
                            isuser = true;
                        }
                    }
                    if(!isuser){
                        var count_json = {
                                                'user':'',
                                                'type':'',
                                                'istt':false,
                                                'ischangett':false
                                            };
                        isuser = true;
                        count_json.user = item.wifiuser_id;
                        count_json.type = "new";
                        if(datetime >= dateArray[0].getTime() && datetime <= dateArray[1].getTime()){
                            count_json.istt = true;
                            count_json.ischangett = true;
                        }else{
                             count_json.istt = false;    
                             count_json.ischangett = false;
                        }
                        count_data[i] = count_json;
                        it = it + 1;
                    }
                     
            }); 
            //统计新老顾客人数
            for (var j = 0; j < count_data.length; j++){
                if(count_data[j].type === "old"){
                    json_str.alloldcount = json_str.alloldcount + 1;
                }
                if(count_data[j].type === "old" && count_data[j].istt){
                    json_str.lastttoldcount = json_str.lastttoldcount + 1;
                }
                if(count_data[j].type === "new" && count_data[j].istt){
                    json_str.lastttnewcount = json_str.lastttnewcount + 1;
                }
                if(count_data[j].type === "old" && count_data[j].ischangett){
                    json_str.changecounttt = json_str.changecounttt + 1;
                }
            }
            var html = "<p>累计老用户数:" + json_str.alloldcount + "人</p>"
                +"<p>老顾客来店人数<span>(最近三十天)</span>:" + json_str.lastttoldcount + "人</p>"
                +"<p>新顾客来店人数<span>(最近三十天)</span>:" + json_str.lastttnewcount + "人</p>"
                +"<p>转换为老顾客人数<span>(最近三十天)</span>:" + json_str.changecounttt + "人 <span>" + (json_str.lastttnewcount === 0 ? 0 : (json_str.changecounttt/json_str.lastttnewcount) *100)  + "%</sapn></p>";
					
		$("#custonnum_ss").html("");
		$("#custonnum_ss").append(html);
        }  
   });

}
**/

function loadOldNewUser(){
    $.ajax({
            type: "post", 
            url : "countAllUser",
            dataType:'json',
            success: function(json){
                var html = "<p>累计老用户数:" + json[0][0] + "人</p>"
                +"<p>老顾客来店人数<span>(最近三十天)</span>:" + json[0][1] + "人</p>"
                +"<p>新顾客来店人数<span>(最近三十天)</span>:" + (json[0][2] - json[0][1]) + "人</p>"
                +"<p>转换为老顾客人数<span>(最近三十天)</span>:" + json[0][3] + "人 <span>" + (json[0][2] - json[0][1] === 0 ? 0 : (json[0][3]/(json[0][2] - json[0][1])) *100)  + "%</sapn></p>";
					
		$("#custonnum_ss").html("");
		$("#custonnum_ss").append(html);
            }
    });
}

function getCountLog(){
    var startDate_str = $("#inputDateStart_2").val();
    var inputDateEnd_str = $("#inputDateEnd_2").val();
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
                 var html = "<table width='100%' border='2' color='blue' cellspacing='1' cellpadding='1'>"
                            + "<tr>"
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
                             newUserCount = json[i][0];
                             totalUserCount = totalUserCount + json[i][0];
                         }else if(json[i][1] === date_str && json[i][2] === "old"){
                             oldUserCount = json[i][0];
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
                        +"<td>" + newUserCount + "</td>"
                        +"<td>" + oldUserCount + "</td>"
                        +"<td>" + totalUserCount + "</td>"
                    +"<tr>";
            }
            showPlot('chart',data_array);
           $("#bbbox").html("");
           $("#bbbox").append(html);  
           
        }  
   });
}

function showPlot(divID, data) {
    
    $("#" + divID).html("");
    
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
        title: "极源wifi统计图",
        highlighter: {show: true}
    });
}

