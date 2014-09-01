$(document).ready(function(){
	$(".list-group-item").click(function(){
		$(this).parent().children("a").removeClass();
		$(this).parent().children("a").addClass("list-group-item");
		$(this).addClass("list-group-item active");
	});
	
	$(".list-group-item").hover(
			function () {
				$(this).stop().animate({ paddingLeft: "40px" }, 200);
			}, 
			function () {
				$(this).stop().animate({ paddingLeft: "15px" });
			}
		);

	$(".navbar-nav li").click(function(){
		    

	});
	/*
	初始化输入框中的时间
	**/

	var obj;
	$(".dateshow").DatePicker({
		format:'Y-m-d',
		date: $(".dateshow").val(),
		current: $(".dateshow").val(),
		starts: 1,
		position: 'right',
		onBeforeShow: function(){
			obj = $(this);
			obj.DatePickerSetDate($(this).val(), true);
		},
		onChange: function(formated, dates){
			obj.val(formated);
			obj.DatePickerHide();
		}
	});
	

/**
	line1 = [ [1,2],[2,7], [3,9], [4,16] ]; //子统计1数据
        

        //--最简
        plot = $.jqplot('chart1', [ line1 ], {
            seriesDefaults : {
                renderer : $.jqplot.BarRenderer, //使用柱状图表示
                rendererOptions : {
                    barMargin : 95
                //柱状体组之间间隔
                }
            },
			title:"极源wifi柱状图"
        });
		
		
  var line1=[['2008-08-12 4:00PM',4], ['2008-09-12 4:00PM',6.5], ['2008-10-12 4:00PM',5.7], ['2008-11-12 4:00PM',9], ['2008-12-12 4:00PM',8.2]];
  var plot1 = $.jqplot('chart', [line1], {
    title:'极源wifi折现图',
    axes:{
        xaxis:{
            renderer:$.jqplot.DateAxisRenderer
        }
    },
    series:[{lineWidth:4, markerOptions:{style:'square'}}]
  });
**/
	scanTime();
});

function clickMenu(str){
	$("#" + str).parent().children("div").hide();
	$("#" + str).show();
	if(str == "klfx"){
		var html = "<p>全部客流累计人数:" + "20000" + "人次</p>"
				  +"<p>手机客流累计人数:" + "20000" + "人次</p>";
					
		$("#custonnum").html("");
		$("#custonnum").append(html);
	}
	scanTime();
}
function scanTime(){
	var date = new Date();
	var year = date.getFullYear();

	var month = formatNum(date.getMonth() + 1);
	var day = formatNum(date.getDate());
	var date_str = year + "-" + month + "-" + day;
	$(".dateshow").val(date_str);
	//$("#inputDateEnd").val(date_str);
}
function formatNum(num){
	if(num < 10){
		return "0" + num;
	}else{
		return num;
	}
}

function showbox(str){
	if(str == "tjxyg"){
		var html = "<p>员工姓名： <input type='text'/></p>" 
					+ "<p>手机号码： <input type='text'/></p>"
					+ "<a class='btn btn-comit'>保存</a>";
		$("#box").html("");
		$("#box").append(html);
		$("#box").show();
	}
	
	
	
}
