var currentSupplyButton = document.getElementById("curSupplBut");
var historySupplyButton = document.getElementById("hisSupplBut");
var newSupplButton = document.getElementById("newSupplBut");

var supplyHistoryContainer = document.getElementById("supply-history-container");
var newSupplyContainer = document.getElementById("new-supply-container");
var currentSupplyContainer = document.getElementById("supply-container");

function historyChosen(data) {
    data.className += " current-button";
    newSupplButton.className = newSupplButton.className.split(" ")[0];
    currentSupplyButton.className = currentSupplyButton.className.split(" ")[0];

    supplyHistoryContainer.className = supplyHistoryContainer.className.split(" ")[0];
    newSupplyContainer.className += " d-none";
    currentSupplyContainer.className += " d-none";
}

function addNewSupplyChosen(data) {
    data.className += " current-button";
    historySupplyButton.className = historySupplyButton.className.split(" ")[0];
    currentSupplyButton.className = currentSupplyButton.className.split(" ")[0];

    newSupplyContainer.className = newSupplyContainer.className.split(" ")[0];
    supplyHistoryContainer.className += " d-none";
    currentSupplyContainer.className += " d-none";
}

function currentSupplyChosen(data) {
    data.className += " current-button";
    historySupplyButton.className = historySupplyButton.className.split(" ")[0];
    newSupplButton.className = newSupplButton.className.split(" ")[0];

    currentSupplyContainer.className = currentSupplyContainer.className.split(" ")[0];
    newSupplyContainer.className += " d-none";
    supplyHistoryContainer.className += " d-none";
}

var HumidityChart = document.getElementById("HumidityChart");
var TemperatureChart = document.getElementById("TemperatureChart");
var LuminosityChart = document.getElementById("LuminosityChart");
var AccelerationChart = document.getElementById("AccelerationChart");


function selectAnother(id) {
    switch (id) {
        case 0: //Humidity history
            HumidityChart.className = HumidityChart.className.split(" ")[0];
            TemperatureChart.className+="d-none";
            LuminosityChart.className+="d-none";
            AccelerationChart.className+="d-none";
            break;
        case 1: //Temperature history
            TemperatureChart.className = TemperatureChart.className.split(" ")[0];
            HumidityChart.className+="d-none";
            LuminosityChart.className+="d-none";
            AccelerationChart.className+="d-none";
            break;
        case 2://Luminosity history
            LuminosityChart.className = LuminosityChart.className.split(" ")[0];
            TemperatureChart.className+="d-none";
            HumidityChart.className+="d-none";
            AccelerationChart.className+="d-none";
            break;
        case 3://Acceleration history
            AccelerationChart.className = AccelerationChart.className.split(" ")[0];
            TemperatureChart.className+="d-none";
            LuminosityChart.className+="d-none";
            HumidityChart.className+="d-none";
            break;
    }
}
