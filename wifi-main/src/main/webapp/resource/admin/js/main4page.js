/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setPageOption(size_ulr,info_url,divid){
    var max_size = 0;
    var url = {
        size_url:size_ulr,
        info_url:info_url
    };
     $.ajax({
            type: "post", 
            url : size_ulr,
            dataType:'json',
            data:{    
             "type" : type 
            },
            success: function(data){
               max_size = data; 
            }
        });
        $("#" + divid).html("<div id='infodiv'></div><div id='Pagination, class='pagination'></div>");
    var optInit ={
        "items_per_page":5,
	"next_text":"下一页",
	"num_display_entries":10,
	"num_edge_entries":2,
	"prev_text":"上一页",
        "url":url,
	"callback":pageselectCallback
    };
    $("#Pagination").pagination(max_size, optInit);
}

function pageselectCallback(page_index, jq, url){
    $.ajax({
            type: "post", 
            url : url.info_url,
            dataType:'json',
            data:{    
             "page" : page_index 
            },
            success: function(data){
               var html = "";
               for (i = 0; i < data.length; i++){
                   for (j = 0; j < data[i].length; i++){
                       html = html + "<span>" + data[i][j];
                   }
               }
               $("#infodiv").html(html);
            }
        });
}