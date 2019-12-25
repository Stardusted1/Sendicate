var app = angular.module('app', []);

app.directive('uiSource', function () {
    hello = function (el){
        alert(el.value);
    };
    return {
        compile: function (elem) {
            var element = angular.element("<button ng-click='hello(this)' value='1'>click</button>")
            elem.append(element)
        },


    };
});


