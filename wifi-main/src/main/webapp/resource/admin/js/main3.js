/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
                +"<p>转换为老顾客人数<span>(最近三十天)</span>:" + json_str.changecounttt + "人 <span>" + (json_str.changecounttt/json_str.lastttnewcount) *100  + "%</sapn></p>";
					
		$("#custonnum_ss").html("");
		$("#custonnum_ss").append(html);
        }  
   });

}
