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