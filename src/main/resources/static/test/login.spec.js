document.writeln("<div id=\"login\" ng-app=\"login\" ng-controller=\"login\" ng-click=\"click()\">{{info}}</div>");
document.writeln("<script type='text/javascript' src='base/test/login.js'></script>");
/*
describe('hello world', function () {
    it('should contains world', function () {
        expect('Hello world').toContain('world');
        expect($scope.click).success();
    });
});
 */
describe('login', function () {
	var scope, $httpBackend;
	beforeEach(angular.mock.module('login'));
	beforeEach(angular.mock.inject(function ($rootScope, $controller, _$httpBackend_) {
		$httpBackend = _$httpBackend_;
		scope = $rootScope.$new();
		$controller('login', {
			$scope: scope
		});
	}));
	it('click should fetch users', inject(function ($injector) {
		//  var $httpBackend = $injector.get('$httpBackend');
		$httpBackend.when('GET', 'http://localhost:3306/aa?aa=1').respond({"end": "123"});
		scope.click();
		$httpBackend.flush();
		alert(scope.isrun);
		alert(scope.info);
	}));
});

