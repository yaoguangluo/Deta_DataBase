var am = angular.module("index", ['ngCookies']);
am.controller('index', ['$cookieStore', '$scope', '$http', '$compile', function ($cookieStore, $scope, $http, $compile) {
	const headers = new Headers();
	headers.append('Access-Control-Allow-Headers', 'Content-Type');
	headers.append('Access-Control-Allow-Origin', '*');
	$scope.email = $cookieStore.get('email');
	$scope.token = $cookieStore.get('token');
	//ip查找代码来自百度搜索如下4个链接。
	//1:http://stackoverflow.com/questions/391979/how-to-get-clients-ip-address-using-javascript-only
	//2:https://github.com/diafygi/webrtc-ips
	//3:http://www.yiichina.com/tutorial/459
	//4:http://www.html-js.com/article/Learn-JavaScript-every-day-to-understand-what-JavaScript-Promises
	function getUserIP(onNewIP) { //  onNewIp - your listener function for new IPs
		//compatibility for firefox and chrome
		var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
		var pc = new myPeerConnection({
			iceServers: []
		}), noop = function() {}, localIPs = {},
		ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g, key;
		function iterateIP(ip) {
			if (!localIPs[ip]) onNewIP(ip);
			localIPs[ip] = true;
		}
		pc.createDataChannel("");
		pc.createOffer().then(function(sdp) {
			sdp.sdp.split('\n').forEach(function(line) {
				if (line.indexOf('candidate') < 0) return;
				line.match(ipRegex).forEach(iterateIP);
			});
			pc.setLocalDescription(sdp, noop, noop);
		}).catch(function(reason) {
		});
		pc.onicecandidate = function(ice) {
			if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
			ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
		};
	}
	// Usage
	getUserIP(function(ip){
		$cookieStore.put("ip", ip);
	});

	$scope.logout = function (email, token) {	
		console.info(email);
		$http.get('logout?uEmail=' + encodeURIComponent(email)
				+'&uToken=' + encodeURIComponent(token), headers)
				.then(function successCallback(response) {
					$scope.token = response.data.userToken;
					$scope.email = response.data.userEmail;
					$cookieStore.put('token', $scope.token);
					$cookieStore.put('email', $scope.email);
					window.location.href="index.html";
					alert("退出成功！" + $cookieStore.get('email'));
				}, function errorCallback(response) {
					$scope.backFeed = "invalid logout！";
					alert("退出失败！" + $scope.email);
				});
	}
	
	$scope.shake = function(){
		document.getElementById('audio').play();
		$("#right-info-bar").delay("fast").animate({width:'480px'});
		$("#right-info-bar").delay("fast").animate({width:'400px'});
		$("#right-info-bar").delay("fast").animate({width:'460px'});
		$("#right-info-bar").animate({width:'440px'});
	}
	
	$scope.ask = function (askMessage) {
		//$scope.shake();
		console.info($scope.pointIp);
		console.info(askMessage);
		$http.post('ask?ip=' + encodeURIComponent($cookieStore.get("ip"))
				+ '&token=' + encodeURIComponent($scope.token)
				+ '&message=' + encodeURIComponent(askMessage)
				+ '&pointIp=' + encodeURIComponent($scope.pointIp), headers)
				.then(function successCallback(response) {
					$scope.loginInfo = response.data.loginInfo;
					$scope.returnResult = response.data.returnResult;
					if($scope.loginInfo == 'unsuccess'){
						alert("询问操作失败！" + $scope.loginInfo + ' ' + $scope.returnResult);
					}else{
						$scope.feedBack("update", $scope.pointIp);
					}
				}, function errorCallback(response) {
					//alert("询问请求错误！" + $scope.loginInfo + ' ' + $scope.returnResult);
				});
	}

	$scope.feedBack = function (type, pointIp) {
		if($scope.pointIp == null){
			$scope.pointIp = pointIp;
		}
		var isShake=0;
		$http.post('feedBack?ip=' + encodeURIComponent($cookieStore.get("ip"))
				+ '&token=' + encodeURIComponent($scope.token)
				+ '&pointIp=' + encodeURIComponent(pointIp)
				, headers)
				.then(function successCallback(response) {
					$scope.loginInfo = response.data.loginInfo;
					$scope.returnResult = response.data.returnResult;
					if($scope.loginInfo == 'unsuccess'){
						//alert("服务操作错误！" + $scope.loginInfo + ' ' + $scope.returnResult);
					}else{
						if(type == "update"){
							for (var i = 0; i < response.data.obj.length; i++){
								if(response.data.obj[i].isRead == 'false'){
									//shake
									if(isShake==0){
										$scope.shake();
										isShake=1;
									}
									//shaked
									var timestamp = response.data.obj[i].time;
									var newDate = new Date();
									newDate.setTime(timestamp);
									if(response.data.obj[i].object == 'Guest'){
										document.getElementById('communication').innerHTML+= 
											'<div style="background-color:#F4FFF4"><p style="text-align:left">'+(response.data.obj[i].object + '(' + newDate.toGMTString() + ')' + ':' + '' + '\n')+'</p>'
											+ '<p><font color="FF0000">'+response.data.obj[i].message+'</font></p></div>';
									}else{
										document.getElementById('communication').innerHTML+=
											'<div style="background-color:#FFF1FF"><p style="text-align:right">'+(response.data.obj[i].object + '(' + newDate.toGMTString() + ')' + ':' + '' + '\n')+'</p>'							
											+ '<p><font color="0000FF">'+response.data.obj[i].message+'</font></p></div>';
									}		
								}
							}
						}
						if(type == "select"){
							document.getElementById('communication').innerHTML='';
							for (var i = 0; i < response.data.obj.length; i++){
								var timestamp = response.data.obj[i].time;
								var newDate = new Date();
								newDate.setTime(timestamp);
								console.log(newDate.toGMTString());
								if(isShake==0){
									$scope.shake();
									isShake=1;
								}
								if(response.data.obj[i].object == 'Guest'){
									document.getElementById('communication').innerHTML+= 
										'<div style="background-color:#F4FFF4"><p style="text-align:left">'+(response.data.obj[i].object + '(' + newDate.toGMTString() + ')' + ':' + '' + '\n')+'</p>'
										+ '<p><font color="FF0000">'+response.data.obj[i].message+'</font></p></div>';
								}else{
									document.getElementById('communication').innerHTML+=
										'<div style="background-color:#FFF1FF"><p style="text-align:right">'+(response.data.obj[i].object + '(' + newDate.toGMTString() + ')' + ':' + '' + '\n')+'</p>'							
										+ '<p><font color="0000FF">'+response.data.obj[i].message+'</font></p></div>';
								}	
							}
							$scope.getAskers(); 
						}	
					}
					document.getElementById('communication').scrollTop =document.getElementById('communication').scrollHeight;
				}, function errorCallback(response) {
					//alert("获取操作错误！" + $scope.loginInfo + ' ' + $scope.returnResult);
				});
	}

	$scope.getAskers = function () {	
		console.log(11);
		$http.post('getAskers?token=' + encodeURIComponent($scope.token), headers)
		.then(function successCallback(response) {
			$scope.loginInfo = response.data.loginInfo;
			$scope.returnResult = response.data.returnResult;
			if($scope.loginInfo == 'unsuccess'){
			}else{
				console.log(111);
				console.log($scope.askers);
				$scope.askers = response.data.obj;
				var html="";
				for(var i=0;i<$scope.askers.length;i++){
					console.log(1111);
					console.log($scope.askers[i]);
					var ng="<a href='javascript:void(0);' ng-click='feedBack(\"select\",\"" 
						+ $scope.askers[i] + "\")'>" + $scope.askers[i] + "</a>";
					html+="<li>" + ng + "</li>";	
				}
				var $html = $compile(html)($scope);
				console.log(html);
				$("#askinfo-select").empty();
				$("#askinfo-select").append($html);
			}
		}, function errorCallback(response) {
			alert("查询请求错误！" + $scope.loginInfo + ' ' + $scope.returnResult);
		});
	}
}]);