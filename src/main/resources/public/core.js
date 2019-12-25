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

var main = angular.module("main", [])
    .controller("workbenchCurrentController", function ($scope, $http, $compile) {
        const token = document.getElementsByName("userToken")[0].value;
        const id = document.getElementsByName("userId")[0].value;

        $scope.getCurrentSupplies = function () {
            var supplyList = angular.element(document.querySelector("#List-Of-supplies-current"));
            $http.get("api/supply/" + id + "/get")
                .success(function (data) {
                    supplyList.children().detach();
                    supplies = data;
                    for (supply of data) {
                        var div = /*angular.element*/(document.createElement("div"));
                        div.className += "list-element";
                        div.style.marginTop = "2px";
                        div.style.marginBottom = "2px";
                        var form = document.createElement("form");
                        var div_form_group = document.createElement("div");
                        div_form_group.className += "form-group";
                        var button = document.createElement("button");
                        button.className += "btn btn-primary btn-sm text-left";
                        button.style.width = "100%";
                        button.innerText += supply.name;
                        button.value += supply.id;
                        button.setAttribute("ng-controller", "workbenchCurrentController");
                        button.setAttribute("ng-click", "clicked(" + supply.id + ")");
                        div_form_group.appendChild(button);
                        div.appendChild(div_form_group);
                        div = angular.element(div)
                        $compile(div)($scope);
                        supplyList.append(div);
                        //< div class="list-element" style = "margin-top: 2px;margin-bottom: 2px;">
                        //    <form>
                        //       <div class="form-group" style="margin-bottom: 0px;">
                        //           <button class="btn btn-primary btn-sm text-left" type="submit"
                        //               style="width: 100%;margin-top: 2px;margin-bottom: 2px;">
                        //               Button
                        //           </button>
                        //       </div>
                        //    </form>
                        //</div>
                    }
                    return data;
                })
                .error(function (error) {
                    alert("failure, reload page")
                });
        };
        $scope.init = function ($scope, $http) {
            alert("init");
        };
        $scope.clicked = function (el) {
            let supply;
            for (let s of supplies) {
                if (s.id === el) {
                    supply = s;
                    break;
                }
            }
            if (supply === null) {
                return;
            }
            var SupplyNameOf = angular.element(document.querySelector("#SupplyNameOf"));
            var SupplyDateFrom = angular.element(document.querySelector("#SupplyDateFrom"));
            var SupplyDateTo = angular.element(document.querySelector("#SupplyDateTo"));
            var SupplyStatus = angular.element(document.querySelector("#SupplyStatus"));
            var SupplyDeliveryman = angular.element(document.querySelector("#SupplyDeliveryman"));
            var SupplyReceiver = angular.element(document.querySelector("#SupplyReceiver"));
            var SupplyProvider = angular.element(document.querySelector("#SupplyProvider"));
            var SupplyNumberOfPackages = angular.element(document.querySelector("#SupplyNumberOfPackages"));
            SupplyNameOf.text(supply.name);
            SupplyDateFrom.text(supply.dateBegins.slice(0, 10));
            SupplyDateTo.text(supply.dateEnds.slice(0, 10));
            SupplyStatus.text(supply.status);
            SupplyDeliveryman.text(supply.deliverymanId);
            SupplyReceiver.text(supply.receiverId);
            SupplyProvider.text(supply.providerId);
            SupplyNumberOfPackages.text(supply.packages ? supply.packages.length : 0);
            var listOfPackages = angular.element(document.querySelector("#List-Of-packages-in-supply"))
            listOfPackages.children().detach();
            packages = supply.packages;
            for (let pack of packages) {
                var div = document.createElement("div");
                div.className += "list-element";
                div.style.marginTop = "2px";
                div.style.marginBottom = "2px";
                var div_form_group = document.createElement("div");
                div_form_group.className += "form-group";
                var button = document.createElement("button");
                button.className += "btn btn-primary btn-sm text-left";
                button.style.width = "100%";
                button.innerText += pack.payload;
                button.value += pack.id;
                button.setAttribute("ng-controller", "workbenchCurrentController");
                button.setAttribute("ng-click", "clickedPackage(" + pack.id + ")");
                div_form_group.appendChild(button);
                div.appendChild(div_form_group);
                div = angular.element(div);
                $compile(div)($scope);
                listOfPackages.append(div);
            }
        };

        $scope.clickedPackage = function (packid) {
            let pack;
            for (let s of packages) {
                if (s.id === packid) {
                    pack = s;
                    break;
                }
            }
            if (!pack) {
                return;
            }
            var Payload = angular.element(document.querySelector("#PackagePayload"));
            var PackageHumidity = angular.element(document.querySelector("#PackageHumidity"));
            var PackageTemperature = angular.element(document.querySelector("#PackageTemperature"));
            var PackageLumosity = angular.element(document.querySelector("#PackageLumosity"));
            var PackageAcceleration = angular.element(document.querySelector("#PackageAcceleration"));
            Payload.text(pack.payload);
            PackageHumidity.text("None");
            PackageTemperature.text("Max:"+pack.conditions[0].max+" Min:"+pack.conditions[0].min);
            PackageLumosity.text("None");
            PackageAcceleration.text("None");
            var list_sensors = angular.element(document.querySelector("#List-Of-sensors-current"));
            list_sensors.children().detach();
            for(let sensor of pack.transmitters){
                var div = document.createElement("div");
                div.className += "list-element";
                div.style.marginTop = "2px";
                div.style.marginBottom = "2px";
                var div_form_group = document.createElement("div");
                div_form_group.className += "form-group";
                var button = document.createElement("button");
                button.className += "btn btn-primary btn-sm text-left";
                button.style.width = "100%";
                button.innerText += sensor.id + " sensor";
                button.value += sensor.id;
                div_form_group.appendChild(button);
                div.appendChild(div_form_group);
                div = angular.element(div);
                $compile(div)($scope);
                list_sensors.append(div);
            }
            var chartData = {
                labels: ["S", "M", "T", "W", "T", "F", "S"],
                datasets: [{
                    data: [589, 445, 483, 503, 689, 692, 634],
                },
                    {
                        data: [639, 465, 493, 478, 589, 632, 674],
                    }]
            };
            var chLine = document.getElementById("chLine");
            if (chLine) {
                new Chart(chLine, {
                    type: 'line',
                    data: chartData,
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: false
                                }
                            }]
                        },
                        legend: {
                            display: false
                        }
                    }
                });
            }
        }




    });

var supplies
var packages;


