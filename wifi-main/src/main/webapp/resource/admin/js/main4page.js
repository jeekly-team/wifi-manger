/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setPageOption(){
    var url = {
        size_url:"validateLogByCountSize",
        info_url:"validateLogByCount"
    };
     $.ajax({
            type: "post", 
            url : url.size_url,
            dataType:'json',
            data:{  
             "count":1,   
             "type" : "wx" 
            },
            success: function(data){
                $("#bbbox").html("<div id='infodiv'></div><div id='Pagination' class='pagination'></div>");
                var optInit ={
                    "items_per_page":1,
                    "next_text":"下一页",
                    "num_display_entries":5,
                    "num_edge_entries":2,
                    "prev_text":"上一页",
                    "url":url,
                    "callback":pageselectCallback
                };
                $("#Pagination").pagination(data, optInit);
            }
        });
       
}

function pageselectCallback(page_index, jq, opts){
    $.ajax({
            type: "post", 
            url : opts.url.info_url,
            dataType:'json',
            data:{   
             "count":1,   
             "type" : "wx", 
             "page" : page_index ,
             "pagesize":opts.items_per_page
            },
            success: function(data){
                var html = "<table width='100%' border='2' color='blue' cellspacing='1' cellpadding='1'>"
                            + "<tr>"
                            +"<th>最后访问时间</th>"
                            +"<th>会员账号</th>"
                            +"<th>姓名</th>"
                            +"<th>来店次数</th>"
                            +"<th>注册时间</th>"   
                            + "</tr>";
               for (i = 0; i < data.length; i++){

                      html = html + 
                            "<tr>"
                                +"<td>" + data[i][1] + "</td>"
                                +"<td>" + data[i][3] + "</td>"
                                +"<td>" + "" + "</td>"
                                +"<td>" + data[i][0] + "</td>"
                                +"<td>" + data[i][2] + "</td>"
                            +"<tr>";

               }
               $("#infodiv").html(html);
            }
        });
}