/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function centumByType(type){
        $.ajax({
            type: "post", 
            url : "centumByType",
            dataType:'json',
            data:{    
             "type" : type 
            },
            success: function(json){
                 var html = " <table  class='table table-striped table-bordered table-condensed text-center'>"
                               + "<tr class='info text-primary'>"
                               +"<th>回头率</th>"
                               +"<th>人数</th>"
                               +"<th>所占百分比</th>"
                               +"<th>操作</th>"
                               + "</tr>";
                 for(i= 0; i < json.length; i++){
                     var int_width = json[i][2].replace("%","0");
                     html = html + 
                    "<tr>"
                        +"<td>" + json[i][0] + "</td>"
                        +"<td>" + json[i][1] + "</td>"
                        +"<td><div class= 'bar' style = 'width:" + int_width + "px'></div>" + json[i][2] + "</td>"
                        +"<td><p><a class='button-table' href='javascript:setPageOption(\""+ type +"\",\"1\");'>明细</a></p></td>"
                    +"<tr>";
                 }
                $("#chart").hide();
                $("#bbbox").html("");
                $("#bbbox").append(html);
            }
        });
}