(function() {

    'use strict';

    angular
        .module('app.console')
        .controller('ConsoleCtrl', ConsoleCtrl);

    /*@ngInject*/
    function ConsoleCtrl($scope, $rootScope) {

        //the console window is completely hidden per default
        //it will be visible after the first call to addItem()

        $scope.items = [];
        $scope.visible = false;
        $scope.minimized = false;

        var $scrollBar = null;

        $scope.$watch('items', function() {
            setTimeout(function(){
                if($scrollBar === null)
                {
                    var test = $('#console .nano');
                    if(test.length > 0)
                        $scrollBar = test;
                    else
                        return;
                }

                $scrollBar.nanoScroller();
                $scrollBar.nanoScroller({scroll: 'bottom'});
            }, 0);
        }, true);

        //text may be formatted using <font>
        //prependDate is optional (default: false)
        $scope.addItem = function(text, prependDate) {

            if(!$scope.visible)
                $scope.visible = true;

            $scope.items.push({
                date: (prependDate ? new Date() : null),
                text: text
            });

        };

        $scope.clear = function() {
            $scope.items = [];
        };

        $scope.minimize = function() {
            $scope.minimized = true;
        };

        $scope.maximize = function() {
            $scope.minimized = false;
        };

        $scope.hide = function() {
            $scope.visible = false;
        };

        $scope.titleBarClicked = function() {
            if(!$scope.minimized)
                return;

            $scope.minimized = false;
        };

    }

})();
