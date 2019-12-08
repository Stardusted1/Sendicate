
angular.module('demo', [])
    .controller('Hello', function($scope, $http) {
        $http.get('http://localhost:8080/api/new_user/').
        then(function(response) {
            $scope.user = response.data;
        });

    });

angular.module("profile",[])
    .controller('prof',function updateProfile($scope, $http) {

    })
