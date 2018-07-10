<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=urf-8"/>
    <script type="text/javascript" src="resources/js/jquery-2.0.3.js"></script>
    <script type="text/javascript" src="resources/js/md5.js"></script>

    <style>
        * {
            margin: 0px;
            padding: 0px;
        }

        body {
            font-size: 15px;
            color: #4d4d4d;
            font-family: "微软雅黑";
        }

        #lunbobox {
            width: 100%;
            position: relative;
        }

        .lunbo {
            width: 100%;
            min-height: 800px;
        }

        .lunbo img {
            width: 100%;
            height: 100vh;
            min-height: 800px;
            position: absolute;
            top: 0px;
            left: 0px;
        }

        .lunbo a {
            display: block;
        }

        #lunbobox ul {
            width: 285px;
            position: absolute;
            bottom: 10px;
            right: 0px;
            z-index: 5;
        }

        #lunbobox ul li {
            cursor: pointer;
            width: 10px;
            height: 4px;
            border: 1px solid #cccccc;
            float: left;
            list-style: none;
            background: #cccccc;
            text-align: center;
            margin: 0px 5px 0px 0px;
        }

        .middle_right {
            position: relative;
        }

        .right {
            background-color: pink;
            width: 100%;
            height: 800px;
            word-wrap: break-word;
        }

        #lunbobox {
            width: 100%;
            min-width: 1000px;
            height: 100vh;
            min-height: 300px;
            overflow: hidden;
            position: absolute;
            top: 0px;
            left: 0px;
            z-index: -1;
        }

        .content {
            width: 100%;
            min-width: 1000px;
            height: 100vh;
            min-height: 300px;
            position: relative;

        }

        .logo {
            margin-top: 4%;
            margin-left: 3%;
        }

        .art {
            width: 450px;
            height: 100vh;
            min-height: 800px;
            background-color: rgba(0, 0, 0, 0.5);
            position: absolute;
            top: 0px;
            right: 0px;
        }

        .art1 {
            width: 90%;
            margin-left: 5%;
            text-align: right;
            margin-top: 20%;
        }

        .art1 img {
            margin-right: 15px;
        }

        .art2 {
            width: 85%;
            margin-left: 10%;
            color: #fff;
            margin-top: 15%;
            font-size: 18px;
        }

        .art21 {
            margin-left: 85px;
            font-size: 15px;
            color: #ccc;
        }

        .art2 select {
            width: 100px;
            font-size: 16px;
            margin-left: 10px;
            color: #ccc;
            outline: none;
            border: none;
            /*很关键：将默认的select选择框样式清除*/
            appearance: none !important;
            -moz-appearance: none !important;
            -webkit-appearance: none !important;
            -ms-appearance: none !important;
            /*在选择框的最右侧中间显示小箭头图片*/
            /*右边三角的样式图片*/
            background: url(resources/images/login/login3.png) no-repeat scroll right center transparent;
            padding-right: 15px;
        }

        .op {
            background-color: rgba(0, 0, 0, 0);
            color: #4d4d4d;
        }

        .art3 {
            width: 90%;
            margin-left: 5%;
            background-image: url(resources/images/login/login-bg.png);
            background-size: 100% auto;
            margin-top: 15px;
            border-bottom-left-radius: 10px;
            border-bottom-right-radius: 10px;
        }

        .art3 p {
            width: 90%;
            line-height: 50px;
            margin-left: 5%;
            position: relative;
            border-bottom: solid 1px #dcdcdc;
        }

        .art3 span {
            display: inline-block;
            width: 60px;
        }

        .art3 input {
            width: 220px;
            padding-right: 20px;
            height: 40px;
            margin-left: 10px;
            font-size: 15px;
            outline: none;
            border: none;
        }

        .art3 select {
            width: 240px;
            height: 40px;
            margin-left: 10px;
            font-size: 15px;
            outline: none;
            cursor: pointer;
            border: none;
            outline: none;
            /*很关键：将默认的select选择框样式清除*/
            appearance: none !important;
            -moz-appearance: none !important;
            -webkit-appearance: none !important;
            -ms-appearance: none !important;
            /*在选择框的最右侧中间显示小箭头图片*/
            /*右边三角的样式图片*/
            background: url(resources/images/login/login8.png) no-repeat scroll right center transparent;
            padding-right: 15px;
        }

        .art31 {
            padding-top: 35px;
        }

        .icon1 {
            width: 30px;
            margin-right: 10px;
            margin-bottom: -10px;
        }

        .art32 {
            position: absolute;
            top: 15px;
            right: 10px;
        }

        .art33 {
            position: absolute;
            top: 15px;
            right: 10px;
        }

        .art4 {
            color: #fff;
            width: 90%;
            margin-left: 5%;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        .art4 input {
            width: 20px;
            height: 20px;
            float: left;
            margin-right: 10px;
        }

        .art4 span {
            color: #ccc;
            float: right;
        }

        .art5 {
            width: 90%;
            margin-left: 5%;
            background-color: #0e90ff;
            color: #fff;
            line-height: 45px;
            border-radius: 7px;
            border: none;
            outline: none;
            font-size: 17px;
            cursor: pointer;
        }

        .art6 {
            display: inline-block;
            color: #fff;
            margin-left: 5%;
            margin-top: 20px;
            text-decoration: none;
        }
    </style>
</head>
<body onload="init()">
<div class="middle_right">
    <div id="lunbobox">
        <div class="lunbo">
            <a href="#"><img src="resources/images/login/1.jpg"/></a>
            <a href="#"><img src="resources/images/login/2.jpg"/></a>
            <a href="#"><img src="resources/images/login/3.jpg"/></a>
            <a href="#"><img src="resources/images/login/4.jpg"/></a>
        </div>
    </div>

    <form name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" >
        <input type="hidden" name="password" id="passMd5"/>
        <div class="content">
            <img src="resources/images/login/logo.png" alt="" class="logo"/>
            <div class="art">
                <div class="art1">
                    <img src="resources/images/login/login1.png" alt=""/>
                    <img src="resources/images/login/login2.png" alt=""/>
                </div>
                <div class="art2">
                    用户登录
                    <span class="art21">Language:</span>
                    <select name="" id="">
                        <option value="" class="op">简体中文</option>
                    </select>
                </div>
                <div class="art3">
                    <p class="art31">
                        <img src="resources/images/login/login4.png" alt="" class="icon1"/>
                        <span>用&nbsp;户&nbsp;名</span>
                        <input type="text" id="u" name="loginName" value="${userName}">
                    </p>
                    <p>
                        <img src="resources/images/login/login5.png" alt="" class="icon1"/>
                        <span>密&nbsp;&nbsp;&nbsp;码</span>
                        <input type="password" value="${password}" id="p">
                        <%--<img src="resources/images/login/login6.png" alt="" class="art32"/>--%>
                    </p>
                    <p>
                        <span>验&nbsp;证&nbsp;码</span>
                        <input type="text" class="yz" id="c" name="validate"/>
                        <input type="text" class="yz1" id="validateCodeDivId" onclick="validateCode()"
                               readonly="readonly" style="text-align:center"/>
                    </p>
                </div>
                <div class="art4">
                    <%--<input type="checkbox" id="check1"><label for="check1">记住用户名</label>--%>
                    <%--<span>校园门户登录</span>--%>
                </div>
                <button id="loginButton" type="button" class="art5" onclick="login()">登录</button>
                <%--<a href="" class="art6">忘记密码？</a>--%>
            </div>
        </div>
    </form>


</div>
<script>
    var t;
    var index = 0;
    /////自动播放
    t = setInterval(play, 3000)

    function play() {
        index++;
        if (index > 4) {
            index = 0
        }

        $(".lunbo a ").eq(index).fadeIn(1000).siblings().fadeOut(1000);
    }

    //登录验证
    function login() {
        var username = $("#u").val();
        var password = $("#p").val();
        var code = $("#c").val();
        if (username == "") {
            alert("请输入用户名！");
            return;
        }
        if (password == "") {
            alert("请输入密码！");
            return;
        } else {
            $("#passMd5").val($.md5(password));
        }
        if (code == "") {
            alert("请输入验证码！");
            return;
        } else {
            //比较验证码是否正确
            var code = $("#c").val();
            var validateCode = $("#validateCodeDivId").val().replace(/[ ]/g, "");//去掉字符串所有空格
            if (code.toLowerCase() != validateCode.toLowerCase()) {
                alert("验证码错误!");
                return;
            }
        }
        $.ajax({
            url: "login",
            async:false,
            data: $('#login_form').serialize(),
            type: "post",
            dataType:"json",
            success: function (data) {
                console.log(data);
//                alert("salfjksl;fsdfj");
                if (data.status == 1) {
                    location.href = "main";
                    return false;
                } else {
                    alert(data.message);
                    return;
                }
            }
//            ,error:function (data) {
//                alert("111111111");
//            }
        });
        return false;
    }

    function init() {
        validateCode();
    }

    //生成随机验证码
    function validateCode() {
        var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
        var res = "";
        for (var i = 0; i < 4; i++) {
            var id = Math.ceil(Math.random() * 35);
            res += chars[id] + " ";
        }
        if (res != "") {
            $("#validateCodeDivId").val(res);
        }
    }

    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            $("#loginButton").click();
        }
    }
</script>

</body>
<%-- <img src="resources/images/login/background.png"/>
<div class="yam_login">
	<img class="loginframe" src="resources/images/login/loginframe.png"/>
	<form name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
		<input type="hidden" name="password" id="passMd5"/>	
	 	<div id="uNameDivId" class="uNameDiv">
	 		<input type="text" id="u" name="loginName" value="${userName}" class="input_text" style="height: 29px;width: 294px"/>
	 	</div>
	 	<div id="uPassDivId" class="uPassDiv">
	 		<input type="password" id="p"  value="${password}" class="input_text" style="height: 29px;width: 294px"/>
	 	</div>
	 	<div id="validateDivId" class="validateDiv">
	 		<input type="text" id="c" name="validate" class="input_text" style="height: 29px;width: 203px" />
	 	</div>
	 	<div id="validateCodeDivId" class="validateCodeDiv" onclick="validateCode()"></div>
		<input type="button" value="" onclick="login()" class="butDiv" style="height:45px;width:377px"/>
	 </form>
</div>--%>
</body>
</html>