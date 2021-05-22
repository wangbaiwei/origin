<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0,maxmun-scale=1.0"/>
		<title>Harmong</title>
		<style type="text/css">
		</style>
		<link href="css/big.css" rel="stylesheet" media="(min-device-width:480px)"/>
		<link href="css/small.css" rel="stylesheet" media="(min-device-width:1px) and (max-device-width:499px)"/>
	</head>
	<script type="text/javascript">
		window.onload = init;
		/* 时间 */
		setInterval(function() {
			var time = new Date();
			document.getElementById('top').firstChild.nextElementSibling.innerText = dateFormat('YYYY年MM月dd日 HH:mm:SS',time); 
		},1000);
		
		/* 设置页面的高度 */
		var init=()=>{
			var screenH = document.body.clientWidth * 2;
			document.getElementById("main").style.height= screenH + 'px';
			// 获取屏幕的宽度
			let screenWidth = document.documentElement.clientWidth;
			let fontSize = (20*(screenWidth/320)>20?20+'px':20*(screenWidth/320)+'px');
			document.documentElement.style.fontSize=fontSize;
		}
		window.addEventListener('reload', init);
		window.addEventListener('resize', init);
		
		/* 页面 */
		function jump(obj) {
			document.getElementById('center').children[0].src=obj;
		}
		
		
		/* 时间格式化 */
		function dateFormat(fmt, date) {
		    let ret;
		    const opt = {
		        "Y+": date.getFullYear().toString(),        // 年
		        "m+": (date.getMonth() + 1).toString(),     // 月
		        "d+": date.getDate().toString(),            // 日
		        "H+": date.getHours().toString(),           // 时
		        "M+": date.getMinutes().toString(),         // 分
		        "S+": date.getSeconds().toString()          // 秒
		        // 有其他格式化字符需求可以继续添加，必须转化成字符串
		    };
		    for (let k in opt) {
		        ret = new RegExp("(" + k + ")").exec(fmt);
		        if (ret) {
		            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
		        };
		    };
		    return fmt;
		}
		
		function resign() {
			var body = document.getElementById('body');
			var resignDiv=document.createElement("div");
			resignDiv.style.width='50%';
			resignDiv.style.height='200px'
			resignDiv.style.backgroundColor='red';
			resignDiv.style
			body.appendChild(resignDiv);
		}
	</script>
	<body id="main">
		<div class="layout">
			<div id="top" class="top">
				<div></div>
				<div>
					<li><a href="javascript:resign()">注册</a></li>
					<li><a href="#">登录</a></li>
				</div>
			</div>
			<div id="main" class="main">
				<div class="left">
					<li><a id="menu1" onclick="jump('http://www.baidu.com')" href="#">菜单1</a></li>
					<li><a id="menu2" onclick="jump('http://www.csdn.net/')" href="#">菜单2</a></li>
					<li><a id="menu3" onclick="jump()" href="#">菜单3</a></li>
					<li><a id="menu4" onclick="jump()" href="#">菜单4</a></li>
					<li><a id="menu5" onclick="jump()" href="#">菜单5</a></li>
					<li><a id="menu6" onclick="jump()" href="#">菜单6</a></li>
				</div>
				<div id="center" class="center">
					<iframe src=""></iframe>
				</div>
			</div>
			<div class="bottom">bottom
			</div>
		</div>
	</body>
	<script type="text/javascript">
		var resignDiv;
		var nameInput;
		var passwordInput;
		function resign() {
			if (resignDiv == undefined) {
				var body = document.getElementById('main');
				var screenWidth = window.screen.availWidth;
				var screenHeight = window.screen.availHeight;
				// 注册弹框
				resignDiv = document.createElement('div');
				resignDiv.setAttribute('id', 'resign');
				resignDiv.style.width = '50%';
				resignDiv.style.height = '500px';
				resignDiv.style.background = 'white';
				resignDiv.style.display = 'flex';
				resignDiv.style.zIndex = '100';
				resignDiv.style.border= '1px solid #858585';
				resignDiv.style.borderRadius = '30px';
				resignDiv.style.flexDirection = 'row';
				resignDiv.style.justifyContent = 'center';
				resignDiv.style.alignItems = 'center';
				resignDiv.style.alignSelf = 'center';
				resignDiv.style.position = 'fixed';
				resignDiv.style.left = ((screenWidth - screenWidth * 0.5) / 2) + 'px';
				resignDiv.style.top = ((screenHeight - 600) / 2) + 'px';
				
				var infoDiv = document.createElement("div");
				infoDiv.style.border = '4px solid #858585';
				infoDiv.style.display = 'flex';
				infoDiv.style.flexDirection = 'column';
				// infoDiv.flexWrap = 'wrap';
				infoDiv.style.width = '80%';
				infoDiv.style.height = '50%';
				infoDiv.style.borderRadius = 'inherit';
				infoDiv.style.padding = '1px';
				infoDiv.style.justifyContent = 'center';
				infoDiv.style.alignItems = 'center';
				// 姓名
				var nameLabel = document.createElement("label");
				nameLabel.style.fontFamily = '隶书';
				nameLabel.innerHTML = '姓名:&nbsp;';
				nameLabel.style.fontSize = '30px';
				nameInput = document.createElement("input");
				nameInput.style.width = '70%';
				nameInput.style.height = '20px';
				nameInput.placeholder = '请输入姓名';
				// 密码
				var passwordLabel = document.createElement("label");
				passwordLabel.innerHTML = '密码:&nbsp;';
				passwordLabel.style.fontFamily = '隶书';
				passwordLabel.style.fontSize = '30px';
				passwordInput = document.createElement("input");
				passwordInput.style.width = '70%';
				passwordInput.style.height = '20px';
				passwordInput.placeholder = '请输入密码';
				
				// 确认
				let affirm = document.createElement('button');
				affirm.innerText = '确认';
				affirm.style.width = '100px';
				affirm.style.fontSize = '20px';
				affirm.style.margin = '20px 60 0px 0px';
				affirm.onclick = doSubmit;
				//取消
				
				let cancle = document.createElement('button');
				cancle.innerText = '取消';
				cancle.style.fontSize = '20px';
				cancle.style.width = '100px';
				cancle.style.margin = '20px 0px 0px 60px';
				cancle.onclick = doCancle;
				
				// namesapan
				var nameSpan = document.createElement('span');
				nameSpan.appendChild(nameLabel);
				nameSpan.appendChild(nameInput);
				nameSpan.style.width = '80%';
				// passwordspan
				var passwordSpan = document.createElement('span');
				passwordSpan.style.marginTop = '20px';
				passwordSpan.style.width = '80%';
				passwordSpan.appendChild(passwordLabel);
				passwordSpan.appendChild(passwordInput);
				
				var operateSpan = document.createElement('span');
				operateSpan.appendChild(affirm);
				operateSpan.appendChild(cancle);
				
				infoDiv.appendChild(nameSpan);
				infoDiv.appendChild(passwordSpan);
				infoDiv.appendChild(operateSpan);
				resignDiv.appendChild(infoDiv);
				
				
				body.appendChild(resignDiv);
			} else {
				resignDiv.style.display = 'flex';
			}
		}
		
		
		function doSubmit(evt) {
			
			let name = nameInput.value;
			let password = passwordInput.value;
			let errorMessage = '';
			if (!(/^.{2,5}$/.test(name))) {
				errorMessage += '呢称格式错误！';
			} 
			var nameRegx = /^.{1,8}$/g;
			if (!(nameRegx.test(password))) {
				errorMessage += '密码格式错误！';
			} 
			if (errorMessage === '') {
				resignSubmit(name, password);
			} else {
				alert(errorMessage);
				return;
			}
			
		}
		function doCancle(evt) {
			resignDiv.style.display = 'none';
		}
		
		function resignSubmit(name, password){
				// 常见XMLHttpRequst对象
				xmlrequest = new XMLHttpRequest();
				// 回调函数
				xmlrequest.onreadystatechange = callBack;
				// 异步请求方式 请求服务
				xmlrequest.open("post","resign",true);
				// 请求头
				xmlrequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				// 发送请求
				xmlrequest.send("name=" + name + "&password=" + password);
			}
		// 回调函数  注意：state倾向是满足某种条件 status倾向是某种状态
		function callBack(){
			if (xmlrequest.readyState == 4 && xmlrequest.status == 200) {
				// 获取响应内容
				var data = xmlrequest.responseText;
				if (data == "true") {
					alert("注册成功!");
					resignDiv.style.display = 'none';
				} else {
					alert("注册失败!");
				}
			}
		}
	</script>
</html>
