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

        };
        $scope.newToken = function () {
            const url = document.getElementsByName("urlRequest")[0].value;
            const userToken = document.getElementsByName("userToken")[0].value;
            $http.post(url+"/new_token/"+userToken,[])
                .success(function (data) {
                    document.getElementsByName("userToken")[0].value = data.token;
                    document.getElementById("TokenVisible").innerText = data.token;
                    alert("Changed successfully");
                }).error(function (data) {
                    alert("There was an error during processing your request");
            })
        }
    });

var main = angular.module("main", [])
    .controller("workbenchCurrentController", function ($scope, $http, $compile) {
        const userToken = document.getElementsByName("userToken")[0].value;
        const userId = document.getElementsByName("userId")[0].value;
        const userType = document.getElementsByName("userType")[0].value;

        $scope.getCurrentSupplies = function () {
            var supplyList = angular.element(document.querySelector("#List-Of-supplies-current"));
            $http.get("api/supply/" + userId + "/get")
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
            currentSupply = supply;
            var SupplyNameOf = angular.element(document.querySelector("#SupplyNameOf"));
            var SupplyDateFrom = angular.element(document.querySelector("#SupplyDateFrom"));
            var SupplyDateTo = angular.element(document.querySelector("#SupplyDateTo"));
            var SupplyStatus = angular.element(document.querySelector("#SupplyStatus"));
            var SupplyDeliveryman = angular.element(document.querySelector("#SupplyDeliveryman"));
            var SupplyReceiver = angular.element(document.querySelector("#SupplyReceiver"));
            var SupplyProvider = angular.element(document.querySelector("#SupplyProvider"));
            var SupplyCondition = angular.element(document.querySelector("#SupplyCondition"));
            var SupplyNumberOfPackages = angular.element(document.querySelector("#SupplyNumberOfPackages"));
            var DeliveredButton = angular.element(document.querySelector("#SupplyDeliveredButton"));
            var TakenButton = angular.element(document.querySelector("#SupplyTakenButton"));
            var AcceptButton = angular.element(document.querySelector("#AcceptButton"));
            DeliveredButton.addClass("d-none");
            TakenButton.addClass("d-none");
            AcceptButton.addClass("d-none");
            if (userType === "DELIVERYMAN" || userType === "RECEIVER") {
                if (supply.status === "DELIVERED") {
                    if (userType === "RECEIVER") {
                        TakenButton.removeClass("d-none");
                    }
                } else if (supply.status === "PENDING") {
                    DeliveredButton.addClass("d-none");
                    TakenButton.addClass("d-none");
                }
            }
            if (userType === "DELIVERYMAN") {
                if (supply.status === "PENDING") {
                    if (!supply.deliverymanApproved)
                        AcceptButton.removeClass("d-none");
                }
            } else if (userType === "RECEIVER") {
                if (supply.status === "PENDING") {
                    if (!supply.receiverApproved)
                        AcceptButton.removeClass("d-none");
                }
            }


            SupplyNameOf.text(supply.name);
            SupplyDateFrom.text(supply.dateBegins.slice(0, 10));
            SupplyDateTo.text(supply.dateEnds.slice(0, 10));
            SupplyStatus.text(supply.status);
            SupplyDeliveryman.text(supply.deliverymanId);
            SupplyReceiver.text(supply.receiverId);
            SupplyProvider.text(supply.providerId);
            SupplyCondition.text(supply.condition);
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
            var PackageStatus = angular.element(document.querySelector("#PackageStatus"));
            Payload.text(pack.payload);
            PackageStatus.text(pack.status);
            PackageHumidity.text("None");
            PackageTemperature.text("Max:" + pack.conditions[0].max + " Min:" + pack.conditions[0].min);
            PackageLumosity.text("None");
            PackageAcceleration.text("None");
            var list_sensors = angular.element(document.querySelector("#List-Of-sensors-current"));
            list_sensors.children().detach();
            for (let sensor of pack.transmitters) {
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
            var colors = ['#ffe845',
                '#2346a7',
                '#0c3f38',
                '#23e666',];
            var dataTemperature = [];
            var dataHumidity = [];
            var dataAcceleration = [];
            var dataLuminocity = [];
            var time = [];
            for (let frame of pack.history) {
                dataTemperature.push(frame.temperature);
                dataAcceleration.push(frame.acceleration);
                dataHumidity.push(frame.humidity);
                dataLuminocity.push(frame.illumination);
                time.push(frame.datetime.slice(0, 10) + "\n" + frame.datetime.slice(11, 19))
            }
            var chartDataT = {
                labels: time,
                datasets: [{
                    data: dataTemperature,
                    backgroundColor: 'transparent',
                    borderColor: colors[0],
                    borderWidth: 4,
                    pointBackgroundColor: colors[0]
                }]
            };
            var chartDataH = {
                labels: time,
                datasets: [{
                    data: dataHumidity,
                    backgroundColor: 'transparent',
                    borderColor: colors[1],
                    borderWidth: 4,
                    pointBackgroundColor: colors[1]
                }]
            };
            var chartDataA = {
                labels: time,
                datasets: [{
                    data: dataAcceleration,
                    backgroundColor: 'transparent',
                    borderColor: colors[3],
                    borderWidth: 4,
                    pointBackgroundColor: colors[3]
                }]
            };
            var chartDataL = {
                labels: time,
                datasets: [{
                    data: dataLuminocity,
                    backgroundColor: 'transparent',
                    borderColor: colors[2],
                    borderWidth: 4,
                    pointBackgroundColor: colors[2]
                }]
            };
            var chLineTemperature = document.getElementById("chLineTemperature");
            if (chLineTemperature) {
                new Chart(chLineTemperature, {
                    type: 'line',
                    data: chartDataT,
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
            var chLineAcceleration = document.getElementById("chLineAcceleration");
            if (chLineAcceleration) {
                new Chart(chLineAcceleration, {
                    type: 'line',
                    data: chartDataA,
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
            var chLineLuminosity = document.getElementById("chLineLuminosity");
            if (chLineLuminosity) {
                new Chart(chLineLuminosity, {
                    type: 'line',
                    data: chartDataL,
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
            var chLineHumidity = document.getElementById("chLineHumidity");
            if (chLineHumidity) {
                new Chart(chLineHumidity, {
                    type: 'line',
                    data: chartDataH,
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
        };

        $scope.supplyDelivered = function () {

            if (!currentSupply) {
                return;
            }
            $http.patch("api/supply/" + currentSupply.id + "/delivered/" + userToken + "/" + userId, [])
                .success(function () {
                    $scope.getCurrentSupplies();
                    var DeliveredButton = angular.element(document.querySelector("#SupplyDeliveredButton"));
                    DeliveredButton.addClass("d-none");
                })
        }

        $scope.acceptSupply = function () {
            let url = "api/supply/" + currentSupply.id + "/accepted/" + userToken + "/" + userId + "/" + userType;
            $http.patch(url, [])
                .success(function () {
                    $scope.getCurrentSupplies();
                    var AcceptButton = angular.element(document.querySelector("#AcceptButton"));
                    AcceptButton.addClass("d-none");
                })
        }


    })

    .controller("newSupplyController", function ($scope, $http, $compile) {
        const userToken = document.getElementsByName("userToken")[0].value;
        const userId = document.getElementsByName("userId")[0].value;
        var conditionIs = angular.element(document.querySelector("#formCheck-1"));
        var maxVal = angular.element(document.querySelector("#maxVal"));
        var minVal = angular.element(document.querySelector("#minVal"));
        var preferredVal = angular.element(document.querySelector("#preferredVal"));
        $scope.onIdInputD = function (value) {
            if ($scope.deliverymanId !== prevIdDeliveryman) {
                if ($scope.deliverymanId.length > 17) {
                    $http.get("/api/users/deliveryman/" + $scope.deliverymanId,)
                        .success(function (data) {
                                if (!data)
                                    return;
                                var deliverymanPic = angular.element(document.querySelector("#pictureDeliveryman"));
                                var nameDeliveryman = angular.element(document.querySelector("#nameDeliveryman"));
                                var descriptionDeliveryman = angular.element(document.querySelector("#descriptionDeliveryman"));
                                var deliverymans_info = angular.element(document.querySelector("#deliverymans-info"));
                                deliverymanPic.attr("src", data.pictureUrl);
                                nameDeliveryman.text(data.name)
                                descriptionDeliveryman.text(data.description);
                                deliverymans_info.removeClass("d-none")
                            }
                        ).error(function () {
                    });
                    prevIdDeliveryman = $scope.deliverymanId
                }
            }
        };
        $scope.onIdInputR = function () {
            if ($scope.receiverId !== prevIdReceiver) {

                if ($scope.receiverId.length > 17) {
                    $http.get("/api/users/receiver/" + $scope.receiverId,)
                        .success(function (data) {
                                if (!data)
                                    return;
                                var receiverPic = angular.element(document.querySelector("#pictureReceiver"));
                                var nameReceiver = angular.element(document.querySelector("#nameReceiver"));
                                var descriptionReceiver = angular.element(document.querySelector("#descriptionReceiver"));
                                var receiver_info = angular.element(document.querySelector("#receiver-info"));
                                receiverPic.attr("src", data.pictureUrl);
                                nameReceiver.text(data.name)
                                descriptionReceiver.text(data.description);
                                receiver_info.removeClass("d-none")
                            }
                        ).error(function () {
                    });
                    prevIdReceiver = $scope.deliverymanId;
                }

            }
        };
        $scope.postSupply = function () {
            var dateFrom = angular.element(document.querySelector("#supplyDateBegin")).val();
            var dateTo = angular.element(document.querySelector("#supplyDateEnd")).val();
            var supplyName = angular.element(document.querySelector("#supplyName")).val();
            var deliveryman = angular.element(document.querySelector("#deliverymanId")).val()
            var receiver = angular.element(document.querySelector("#receiverId")).val();
            newSupply = {
                name: supplyName,
                dateFrom: dateFrom,
                dateTo: dateTo,
                deliveryman: deliveryman,
                receiver: receiver,
                packages: newPackages,
            };
            $http.post("api/supply/new_supply/"+userId+"/"+userToken, newSupply)
                .success(function (data) {
                    if (true) {
                        let listOfTransmitters = angular.element(document.querySelector("#list-of-transmitters"));
                        listOfTransmitters.children().detach();
                        let listOfPackages = angular.element(document.querySelector("#List-Of-packages"));
                        listOfPackages.children().detach();
                        newSupply = [];
                        newPackages = [];
                        newConditions =[];
                        newTransmitters =[];
                        alert("Successfully!")
                    }else {
                        alert("Check parameters and try again")
                    }
                }).error(function () {
                    alert("Something went wrong")
            });
            alert("posted")
        };
        $scope.addTransmitter = function () {
            let transmitterId = angular.element(document.querySelector("#TransmitterId")).val()
            $http.get("api/transmitter/" + transmitterId)
                .success(function (data) {
                    if (!data) {
                        alert("this transmitter currently unavailable")
                        return;
                    }
                    newTransmitters.push(transmitterId)
                    let div = document.createElement("div")
                    div = angular.element(div);
                    div.addClass("form-group d-inline-flex")
                    div.prop("id", "TransmitterId_" + transmitterId)
                    let input = angular.element(document.createElement("input"))
                    input.addClass("border rounded form-control")
                    input.prop("disabled", true)
                    input.val(transmitterId)
                    div.append(input)
                    let button = angular.element(document.createElement("button"))
                    button.addClass("btn btn-danger");
                    button.attr("ng-click", "removeTransmitter(" + transmitterId + ")");
                    button.attr("ng-controller", "newSupplyController")
                    let icon = angular.element(document.createElement("i"))
                    icon.addClass("fa fa-remove")
                    button.append(icon)
                    div.append(button)
                    $compile(div)($scope)
                    let listOfTransmitters = angular.element(document.querySelector("#list-of-transmitters"));
                    listOfTransmitters.append(div);
                }).error(function () {
                alert("this transmitter currently unavailable")
            })
        };
        $scope.removeTransmitter = function (id) {
            for (let tr of newTransmitters) {
                if (tr == id) {
                    newTransmitters.pop();
                }
            }
            let listOfTransmitters = angular.element(document.querySelector("#TransmittersList")).children();
            for (let i = 2; i < listOfTransmitters.length; i++) {
                if (listOfTransmitters[i].id.split("_")[1] == id)
                    listOfTransmitters[i].remove();
            }
        };
        $scope.openHumidityConditions = function () {
            for (let condition of newConditions) {
                if (condition.type == "h") {
                    minVal.val(condition.minval);
                    maxVal.val(condition.maxval);
                    preferredVal.val(condition.pref)
                    conditionIs.val(true)
                    openedCondition = condition
                    return
                }
            }
            openedCondition = {
                minval: 0,
                maxval: 0,
                pref: 0,
                type: "h"
            };
            maxVal.val(0);
            minVal.val(0);
            preferredVal.val(0);
            conditionIs.val(false);
        };
        $scope.openTemperatureConditions = function () {
            for (let condition of newConditions) {
                if (condition.type == "t") {
                    minVal.val(condition.minval);
                    maxVal.val(condition.maxval);
                    preferredVal.val(condition.pref)
                    conditionIs.val(true)
                    openedCondition = condition
                    return
                }
            }
            openedCondition = {
                minval: 0,
                maxval: 0,
                pref: 0,
                type: "t"
            };
            maxVal.val(0);
            minVal.val(0);
            preferredVal.val(0);
            conditionIs.val(false);
        };
        $scope.openLuminocityConditions = function () {
            for (let condition of newConditions) {
                if (condition.type == "l") {
                    minVal.val(condition.minval);
                    maxVal.val(condition.maxval);
                    preferredVal.val(condition.pref)
                    conditionIs.val(true)
                    openedCondition = condition
                    return
                }
            }
            openedCondition = {
                minval: 0,
                maxval: 0,
                pref: 0,
                type: "l"
            };
            maxVal.val(0);
            minVal.val(0);
            preferredVal.val(0);
            conditionIs.val(false);
        };
        $scope.openAccelerationConditions = function () {
            for (let condition of newConditions) {
                if (condition.type == "a") {
                    minVal.val(condition.minval);
                    maxVal.val(condition.maxval);
                    preferredVal.val(condition.pref)
                    conditionIs.val(true)
                    openedCondition = condition
                    return
                }
            }
            openedCondition = {
                minval: 0,
                maxval: 0,
                pref: 0,
                type: "a"
            };
            maxVal.val(0);
            minVal.val(0);
            preferredVal.val(0);
            conditionIs.val(false);
        };
        $scope.checkCondition = function () {
            openedCondition.maxval = maxVal.val();
            openedCondition.minval = minVal.val();
            openedCondition.pref = preferredVal.val();
            for (let cond of newConditions) {
                if (openedCondition.type === cond.type) {
                    if (conditionIs.val() === "on") {
                        cond.maxval = openedCondition.maxval;
                        cond.minval = openedCondition.minval;
                        cond.pref = openedCondition.pref;
                    } else {
                        newConditions.pop(cond);
                    }
                    return;
                }
            }
            newConditions.push(openedCondition);
        };

        $scope.addPackage = function () {
            let listOfPackages = angular.element(document.querySelector("#List-Of-packages"));
            var payload = angular.element(document.querySelector("#Payload")).val();
            var transmitters = newTransmitters;
            newTransmitters = [];
            var conditions = newConditions;
            newConditions = [];
            let pack = {
                id: listOfPackages.children().length,
                payload: payload,
                transmitters: transmitters,
                conditions: conditions
            }
            newPackages.push(pack);

            let div = angular.element(document.createElement("div"));
            div.addClass("list-element");
            div.prop("id", pack.id);
            div.css("margin-top", "2px");
            div.css("margin-bottom", "2px");
            let formGroup = angular.element(document.createElement("div"));
            formGroup.addClass("form-group");

            let button = document.createElement("button");
            button.setAttribute("ng-controller", "newSupplyController");
            button.setAttribute("ng-click", "removePackage(" + pack.id + ")");
            button = angular.element(button);
            button.addClass("btn btn-primary btn-sm text-left");
            button.css("width", "100%");
            button.text(pack.payload);
            $compile(button)($scope);
            formGroup.append(button);
            div.append(formGroup);
            $compile(div)($scope);
            listOfPackages.append(div);
            let listOfTransmitters = angular.element(document.querySelector("#list-of-transmitters"));
            listOfTransmitters.children().detach();
        };
        $scope.removePackage = function (id) {
            for (let pack of newPackages) {
                if (pack.id === id) {
                    newPackages.pop(pack);
                }
            }
            angular.element(document.querySelector("#List-Of-packages")).children()[id].remove();
        }
    })
;
/* new supply controller*/
var openedCondition;
var newTransmitters = [];
var newPackages = [];
var newConditions = [];
var newSupply = [];

/*Workbench current controller*/
var prevIdDeliveryman;
var prevIdReceiver;
var supplies;
var currentSupply;
var packages;


