$(document).ready(function() {

    $(".list-group-item").click(function() {
        $(this).parent().children("a").removeClass();
        $(this).parent().children("a").addClass("list-group-item");
        $(this).addClass("list-group-item active");
    });

    $(".list-group-item").hover(
            function() {
                $(this).stop().animate({paddingLeft: "40px"}, 200);
            },
            function() {
                $(this).stop().animate({paddingLeft: "15px"});
            }
    );

    $(".navbar-nav li").click(function() {


    });
    /*
     初始化输入框中的时间
     **/
        $(".form-control").date_input();
/*
    var obj;
    $(".form-control").DatePicker({
        format: 'Y-m-d',
        date: $(".form-control").val(),
        current: $(".form-control").val(),
        starts: 1,
        position: 'bottom',
        onBeforeShow: function() {
            obj = $(this);
            obj.DatePickerSetDate($(this).val(), true);
        },
        onChange: function(formated, dates) {
            obj.val(formated);
            obj.DatePickerHide();
        }
    });
    */



    scanTime();
});


function clickMenu(str) {
    $("#" + str).parent().children("div").hide();
    $("#" + str).show();
    if (str === "klfx") {
        loadCoungLog();
    } else if (str === "xlfk") {
        loadOldNewUser();
    } else if (str === "hyd"){
        loadAVGCount();
    }

}
function scanTime() {
    var date = new Date();
    var year = date.getFullYear();

    var month = formatNum(date.getMonth() + 1)
    var day = formatNum(date.getDate());
    var date_str = year + "-" + month + "-" + day;
    $(".dateshow").val(date_str);
    //$("#inputDateEnd").val(date_str);
}
function formatNum(num) {
    if (num < 10) {
        return "0" + num;
    } else {
        return num;
    }
}

function showbox(str) {
    if (str === "tjxyg") {
        var html = "<p>员工姓名： <input type='text'/></p>"
                + "<p>手机号码： <input type='text'/></p>"
                + "<a class='btn btn-comit'>保存</a>";
        $("#box").html("");
        $("#box").append(html);
        $("#box").show();
    }



}

function getAllUser() {
    $.ajax({
        type: "post",
        url: "allUser",
        dataType: 'json',
        success: function(json) {
            alert(json);
        }
    });
}
var date_s = new Date();
function getDate(str) {
    if (str === "today") {
        date_s = new Date();
    } else if (str === "yesterday") {
        date_s = new Date(new Date().getTime() - 1000 * 60 * 60 * 24);
    } else if (str === "beforeday") {
        date_s = new Date(date_s.getTime() - 1000 * 60 * 60 * 24);
    } else if (str === "nextday") {
        date_s = new Date(date_s.getTime() + 1000 * 60 * 60 * 24);
    }
    var year = date_s.getFullYear();
    var month = formatNum(date_s.getMonth() + 1);
    var day = formatNum(date_s.getDate());
    var date_str = year + "-" + month + "-" + day;
    return date_str;
}

function dateToString(date_s) {
    
    if(typeof(date_s ) === "number"){
        var date = new Date(date_s);
        var year = date.getFullYear();
        var month = formatNum(date.getMonth() + 1);
        var day = formatNum(date.getDate());
        var date_str = year + "-" + month + "-" + day;
        return date_str; 
    }else if(typeof(date_s ) === "string"){
        return date_s.substring(0,10);
    }else{
        var year = date_s.getFullYear();
        var month = formatNum(date_s.getMonth() + 1);
        var day = formatNum(date_s.getDate());
        var date_str = year + "-" + month + "-" + day;
        return date_str; 
    }    
}

function getDate_s(date_s){
    if(typeof(date_s ) === "number"){
        return new Date(date_s);
    }else if(typeof(date_s ) === "string"){
        //2014-09-09 00:00:00
        var date = date_s.split(" ");
        var bd = date[0].split("-");
        var ed = date[1].split(":");
        return new Date(bd[0],bd[1]-1,bd[2],ed[0],ed[1],ed[2]);
    } 
}
function getTwoDays(str) {
    var date_str = new Array();
    var date_s = new Date();
    date_str[0] = dateToString(date_s);
    if (str === "lastservenday") {
        date_s = new Date(date_s.getTime() - 1000 * 60 * 60 * 24 * 7);
        date_str[1] = dateToString(date_s);
    } else if (str === "lastssday") {
        date_s = new Date(date_s.getTime() - 1000 * 60 * 60 * 24 * 30);
        date_str[1] = dateToString(date_s);
    } else if (str === "month") {
        date_str[0] = dateToString(date_s.setDate(1));
        date_s.setMonth(date_s.getMonth() + 1);
        date_str[1] = dateToString(new Date(date_s.getTime() - 1000 * 60 * 60 * 24 * 1));
    }
    return date_str;
}

function getTwoDate(str) {
    var date_str = new Array();
    var date_s = new Date();
    date_str[0] = date_s;
    if (str === "lastservenday") {
        date_s = new Date(date_s.getTime() - 1000 * 60 * 60 * 24 * 6);
        date_str[1] = date_s;
    } else if (str === "lastssday") {
        date_s = new Date(date_s.getTime() - 1000 * 60 * 60 * 24 * 29);
        date_str[1] = date_s;
    } else if (str === "month") {
        date_s.setDate(1);
        date_str[1] = new Date(date_s.getTime());
        date_s.setMonth(date_s.getMonth() + 1);
        date_s = new Date(date_s.getTime() - 1000 * 60 * 60 * 24 * 1);
        date_str[0] = date_s;
    }
    return date_str;
}

function validateDate(obj){
    var date_obj = $(obj).parent().find("div.input-group").find("input.form-control");
    var date_s = $(date_obj[0]).val();
    var date_e = $(date_obj[1]).val();
    var dates = new Date(date_s.split("-"));
    var datee = new Date(date_e.split("-"));

     if(date_s === "" || date_e === "" || datee.getTime() < dates.getTime()){
         $(obj).attr("data-content","请选择正确的时间！");
         $(obj).popover('show');
         window.setTimeout(function(){
             $(obj).popover('hide');
         },1500);
         return false;
    }else if((datee.getTime() - dates.getTime()) > 1000 * 60 * 60 *24 * 31){
        $(obj).attr("data-content","时间不能超过一个月！");
        $(obj).popover('show');
         window.setTimeout(function(){
             $(obj).popover('hide');
         },1500);
         return false;
    }else{
         return true;
    }
}
