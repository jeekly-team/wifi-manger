/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loadAVGCount(){
     $.ajax({
        type: "post",
        url: "loadAVGCount",
        dataType: 'json',
        success: function(data) {
              var html = "<div>" 
                            + "<p>平均来店人数<span>（所有顾客）</span>:" + data[0][0] + "人</p>"
                            + "<p>平均来店人数<span>（最近三十天）</span>:" + data[0][1] + "人</p>"
                        + "</div>";
					
		$("#custonnum_jy").html("");
		$("#custonnum_jy").append(html);
        }
    });
}

function getActiveUser(type){
    var date = null;
    if(type === "inp"){
        date = new Array();
        date[0] = $("#inputDateStart_3").val();
        date[1] = $("#inputDateEnd_3").val();
         var dates = new Date(date[0]);
        var datee = new Date(date[1]);
        if(dates.getTime() > datee.getTime()){
            alert("请选择正确的时间段!");
            return;
    }
    }else{
        date = getMonthDate(type);
    }
    $.ajax({
        type: "post",
        url: "getActiveUser",
        data:{
            startDate:date[0],
            endDate:date[1]
        },
        dataType: 'json',
        success: function(data) {
              var html = "<span> " + date[0] + "到" + date[1] + " </span>"
                            +"<table width='100%' border='2' color='blue' cellspacing='1' cellpadding='1'>"
                            + "<tr>"
                            +"<th>来店次数</th>"
                            +"<th>人数</th>"
                            +"<th>所占百分比</th>"
                            + "</tr>";
                 for(i= 0; i < data.length; i++){
                     html = html + 
                    "<tr>"
                        +"<td>" + data[i][0] + "</td>"
                        +"<td>" + data[i][1] + "</td>"
                        +"<td>" + data[i][2] + "</td>"
                    +"<tr>";
                 }
                $("#chart").hide();
                $("#bbbox").html("");
                $("#bbbox").append(html);
        }
    });
}
var current_date = new Date();
function getMonthDate(type){
    var date = new Date();
    var dateArray = new Array();
    if(type === "lasttD"){
        var date_strs = dateToString(date);
        var date_stre = dateToString(new Date(date.getTime() - 1000 * 60 * 60 * 24 * 30));
        dateArray[1] = date_strs;
        dateArray[0] = date_stre;
    }else if(type === "currentM"){
        date.setDate(1);
        dateArray[0] = dateToString(new Date(date.getTime()));
        date.setMonth(date.getMonth() + 1);
        date = dateToString(new Date(date.getTime() - 1000 * 60 * 60 * 24 * 1));
        dateArray[1] = date;
    }else if(type === "nextM"){
        current_date.setDate(1);
        current_date.setMonth(current_date.getMonth() + 1);
        dateArray[0] = dateToString(new Date(current_date.getTime()));
        current_date.setMonth(current_date.getMonth() + 1);
        current_date = new Date(current_date.getTime() - 1000 * 60 * 60 * 24 * 1);
        dateArray[1] = dateToString(current_date);
    }else if(type === "lastM"){
        current_date.setDate(1);
        current_date.setMonth(current_date.getMonth() - 1);
        dateArray[0] = dateToString(new Date(current_date.getTime()));
        current_date.setMonth(current_date.getMonth() + 1);
        current_date = new Date(current_date.getTime() - 1000 * 60 * 60 * 24 * 1);
        dateArray[1] = dateToString(current_date);
    }
    return dateArray;
}

