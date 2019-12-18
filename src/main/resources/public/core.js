var demo = angular.module('demo', [])
    .controller('Hello', function ($scope, $http) {
        $http.get('http://localhost:8080/api/new_user/').then(function (response) {
            $scope.user = response.data;
        });

    });

var profile = angular.module("profile", [])
    .controller('profileController', function ($scope, $http) {
        $scope.submitProfile = function () {

            const name = $scope.username;
            const phone = $scope.phone;
            const emailaddr = $scope.emailaddr;
            const address = $scope.address;
            const addressWeb = $scope.addressWeb;
            const description = $scope.description;

            const url = document.getElementsByName("urlRequest")[0].value;
            const data1 = {
                name,
                phone,
                emailaddr,
                address,
                addressWeb,
                description
            };
            let submitBButton = document.getElementsByName("submitButton")[0];
            submitBButton.value = "In progress...";
            $http.post(url,
                data1)
                .success(function (data) {
                    submitBButton.value = "Save";
                    $scope.username = data.name;
                    $scope.emailaddr = data.emailaddr;
                    $scope.phone = data.phone;
                    $scope.address = data.address;
                    $scope.addressWeb = data.addressWeb;
                    $scope.description = data.description;
                    alert("Successfully")
                })
                .error(function (err) {
                    submitBButton.value = "Save";
                    alert(err);
                })

        }
    });
