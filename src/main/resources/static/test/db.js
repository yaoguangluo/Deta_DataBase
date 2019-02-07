var am = angular.module("db", ['ngCookies']);
am.controller('db', ['$cookieStore', '$scope', '$http', function ($cookieStore, $scope, $http) {
	const headers = new Headers();
	headers.append('Access-Control-Allow-Headers', 'Content-Type');
	headers.append('Access-Control-Allow-Origin', '*');
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
		headers.append('Access-ip', ip);
	});
	$scope.email = $cookieStore.get('email');
	$scope.token = $cookieStore.get('token');
	console.info($scope.token);
	$http.get('getAllDBCategory' + '?token='
			+ encodeURIComponent($cookieStore.get('token')), headers).then(function successCallback(response) {
				$scope.loginInfo = response.data.loginInfo;
				$scope.returnResult = response.data.returnResult;
				if($scope.loginInfo == 'unsuccess'){
					alert("操作失败！" + $scope.loginInfo + ' ' + $scope.returnResult);
				}
				$scope.info = response.data;
			}, function errorCallback(response) {
				$scope.info = response;
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
					window.location.href="db.html";
					alert("退出失败！" + $scope.email);
				});
	}
	$scope.click = function getTable(tablePath, pageBegin, pageEnd, direction) {
		$http.get('selectRowsByTablePath?tablePath=' + tablePath + '&pageBegin=' 
				+ pageBegin + '&pageEnd=' + pageEnd + '&direction=' + direction 
				+ '&token=' + encodeURIComponent($cookieStore.get('token')), headers)
				.then(function successCallback(response) {
					$scope.rows = response.data.obj;
					$scope.spec = response.data.spec;
					$scope.pageBegin = response.data.pageBegin;
					$scope.pageEnd = response.data.pageEnd;
					$scope.tablePath = response.data.tablePath;
					$scope.totalPages = response.data.totalPages;
					$scope.loginInfo = response.data.loginInfo;
					$scope.returnResult = response.data.returnResult;
					if($scope.loginInfo =='unsuccess'){
						window.location.href="db.html";
						alert("操作失败！" + $scope.loginInfo + ' ' + $scope.returnResult);
					}
				}, function errorCallback(response) {
					$scope.table = response;
				});
	}
	$scope.inPage = function (tablePath, pageBegin, pageEnd) {
		if($scope.rows[14] != null){
			$scope.click(tablePath, pageBegin, pageEnd, 'next');
		}
	}
	$scope.dePage = function (tablePath, pageBegin, pageEnd) {
		if(pageBegin > 0){
			$scope.click(tablePath, pageBegin, pageEnd, 'prev');
		}
	}
	$scope.insertPage = function (tablePath, culumnOfNewRow, totalPages) {	
		console.info(tablePath);
		console.info(culumnOfNewRow);
		console.info(totalPages);
		$http.get('insertRowByTablePath?tablePath=' + encodeURIComponent(tablePath) 
				+ '&pageIndex=' + totalPages + '&culumnOfNewRow=' 
				+ encodeURIComponent(JSON.stringify(culumnOfNewRow), headers)
				+ '&token=' + encodeURIComponent($cookieStore.get('token')))
				.then(function successCallback(response) {
					$scope.loginInfo = response.data.loginInfo;
					$scope.returnResult = response.data.returnResult;
					if($scope.loginInfo == 'unsuccess'){
						window.location.href="db.html";
						alert("操作失败！" + $scope.loginInfo + ' ' + $scope.returnResult);
					}else{
						$scope.totalPages = response.data.totalPages;
						alert("添加成功！");
						$scope.click(tablePath,$scope.pageBegin,$scope.pageBegin,'next');
						//document.getElementById('light').style.display='none';
					}
				}, function errorCallback(response) {
					$scope.backFeed = "error insertion";
				});
	}
	$scope.updatePage = function (tablePath, culumnOfUpdateRow, totalPages) {	
		console.info(tablePath);
		console.info(culumnOfUpdateRow);
		console.info(totalPages);
		console.info(encodeURIComponent(JSON.stringify(culumnOfUpdateRow)));
		$http.get('updateRowByTablePathAndIndex?tablePath=' 
				+ encodeURIComponent(tablePath) + '&pageIndex=' + totalPages +'&culumnOfUpdateRow=' 
				+ encodeURIComponent(JSON.stringify(culumnOfUpdateRow)) + '&token=' 
				+ encodeURIComponent($cookieStore.get('token')), headers)
				.then(function successCallback(response) {
					$scope.loginInfo = response.data.loginInfo;
					$scope.returnResult = response.data.returnResult;
					if($scope.loginInfo=='unsuccess'){
						window.location.href="db.html";
						alert("操作失败！" + $scope.loginInfo + ' ' + $scope.returnResult);
					}else{
						$scope.totalPages = response.data.totalPages;
						alert("修改成功！");
					} 
				}, function errorCallback(response) {
					$scope.backFeed = "error update";
				});
	}
	$scope.deletePage = function (tablePath, totalPages) {	
		console.info(tablePath);
		console.info(totalPages);
		$http.get('deleteRowByTablePathAndIndex?tablePath=' 
				+ encodeURIComponent(tablePath) + '&pageIndex=' + totalPages + '&token=' 
				+ encodeURIComponent($cookieStore.get('token')), headers)
				.then(function successCallback(response) {
					$scope.loginInfo = response.data.loginInfo;
					$scope.returnResult = response.data.returnResult;
					if($scope.loginInfo == 'unsuccess'){
						window.location.href = "db.html";
						alert("操作失败！" + $scope.loginInfo + ' ' + $scope.returnResult);
					}else{
						$scope.totalPages = response.data.totalPages;
						alert("删除成功！");
						$scope.click(tablePath,$scope.pageBegin,$scope.pageBegin,'next');
					}
				}, function errorCallback(response) {
					window.location.href = "db.html";
					$scope.backFeed = "error delete";
				});
	}
	$scope.execDetaPLSQL = function(LYGQuery){
		console.info(LYGQuery);
		console.info($cookieStore.get('token'));
		$http.get('execDetaPLSQL?LYGQuery=' + encodeURIComponent(LYGQuery) + '&token=' 
				+ encodeURIComponent($cookieStore.get('token')) + '&mod=' 
				+ encodeURIComponent('true'), headers)
				.then(function successCallback(response) {
					$scope.loginInfo = response.data.loginInfo;
					$scope.returnResult = response.data.returnResult;
					if($scope.loginInfo == 'unsuccess'){
						window.location.href = "db.html";
						alert("操作失败！" + $scope.loginInfo + ' ' + $scope.returnResult);
					}else{
						$scope.rows = response.data.obj;
						$scope.spec = response.data.spec;
						$scope.pageBegin = response.data.pageBegin;
						$scope.pageEnd = response.data.pageEnd;
						$scope.tablePath = response.data.tablePath;
						$scope.totalPages = response.data.totalPages;
						$scope.loginInfo = response.data.loginInfo;
						$scope.returnResult = response.data.returnResult;
						alert("执行成功！");
					}
				}, function errorCallback(response) {
					window.location.href="db.html";
					$scope.backFeed = "error delete";
				});
	}
}]);