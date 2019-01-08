var am = angular.module("login", ['ngCookies']);
am.controller('login', ['$cookieStore', '$scope', '$http', function ($cookieStore, $scope, $http) {
	const headers = new Headers();
	headers.append('Access-Control-Allow-Headers', 'Content-Type');
	headers.append('Access-Control-Allow-Origin', '*');
	$scope.login = function (email, password) {	
		console.info(email);
		console.info(password);
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
			}),
			noop = function() {},
			localIPs = {},
			ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
			key;
			function iterateIP(ip) {
				if (!localIPs[ip]) onNewIP(ip);
				localIPs[ip] = true;
			}
			//create a bogus data channel
			pc.createDataChannel("");
			// create offer and set local description
			pc.createOffer().then(function(sdp) {
				sdp.sdp.split('\n').forEach(function(line) {
					if (line.indexOf('candidate') < 0) return;
					line.match(ipRegex).forEach(iterateIP);
				});
				pc.setLocalDescription(sdp, noop, noop);
			}).catch(function(reason) {
				// An error occurred, so handle the failure to connect
			});
			//sten for candidate events
			pc.onicecandidate = function(ice) {
				if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
				ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
			};
		}
		// Usage
		getUserIP(function(ip){
			$cookieStore.put("ip", ip);
		});
		$http.get('login?uEmail=' + encodeURIComponent(email) 
				+ '&uPassword=' + encodeURIComponent(password), headers)
				.then(function successCallback(response) {
					$scope.token = response.data.userToken;
					$scope.email = response.data.userEmail;
					$cookieStore.put('token', $scope.token);
					$cookieStore.put('email', $scope.email);
					$scope.loginInfo = response.data.loginInfo;
					$scope.returnResult = response.data.returnResult;
					if($scope.loginInfo=='success'){
						window.location.href="index.html";
						alert("登陆成功！" + $scope.loginInfo + ' ' + $cookieStore.get('email'));
					}else{
						$scope.backFeed = "invalid registeration！";
						window.location.href="loginto.html";
						alert("登陆失败！" + $scope.loginInfo + ' ' + $scope.returnResult);
					}
				}, function errorCallback(response) {
					$scope.backFeed = "invalid login！";
					window.location.href="loginto.html";
					alert("登陆失败！" + $scope.email);
				});
	}
}]);