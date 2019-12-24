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

var main = angular.module("main",[])
    .controller("workbenchCurrentController", function ($scope, $http) {

        const token = document.getElementsByName("userToken")[0].value;
        const id = document.getElementsByName("userId")[0].value;

        $scope.getCurrentSupplies = function(){
            var supplyList = document.getElementById("List-Of-supplies-current");
            $http.get("api/supply/"+id+"/get")
                .success(function (data) {
                    supplyList.innerHTML = null;
                    for(supply of data){
                        var div = document.createElement("div");
                        div.className+="list-element";
                        div.style.marginTop = "2px";
                        div.style.marginBottom = "2px"

                        var form = document.createElement("form");
                        
                        var div_form_group = document.createElement("div");
                        div_form_group.className += "form-group";

                        var button = document.createElement("button");
                        button.className += "btn btn-primary btn-sm text-left";
                        button.type = "submit";
                        button.style.width = "100%";
                        button.innerText += supply.name;
                        button.value += supply.id;
                        button.setAttribute("ng-controller", "workbenchCurrentController");
                        button.setAttribute("ng-click", "clicked()");
                        button.setAttribute("compile","html")
                        div_form_group.appendChild(button);
                        form.appendChild(div_form_group);
                        div.appendChild(form);
                        $compile(div);
                        supplyList.appendChild(div);

                        //< div class="list-element" style = "margin-top: 2px;margin-bottom: 2px;" >
                        //    <form>
                        //       <div class="form-group" style="margin-bottom: 0px;">
                        //           <button class="btn btn-primary btn-sm text-left" type="submit"
                        //               style="width: 100%;margin-top: 2px;margin-bottom: 2px;">Button
                        //               </button>
                        //       </div>
                        //    </form>
                        //</div >
                    }

                })
                .error(function (error) {
                    alert("failure, reload page")
            });
        };
        $scope.init = function ($scope, $http) {
            alert("init");
        },
        $scope.clicked = function () {
            alert("clicked");
        }
    });