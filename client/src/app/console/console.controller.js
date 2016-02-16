(function() {

    'use strict';

    angular
        .module('app.console')
        .controller('ConsoleCtrl', ConsoleCtrl);

    /*@ngInject*/
    function ConsoleCtrl($scope, $rootScope) {

        $scope.items = [];
        $scope.minimized = true;

        $scope.addItem = addItem;
        $scope.clear = clear;
        $scope.minimize = minimize;
        $scope.maximize = maximize;
        $scope.titleBarClicked = titleBarClicked;

        var $scrollBar = null;
        $scope.$watch('items', function() {
            if ($scrollBar === null) {
                var test = $('#console .nano');
                if (test.length > 0) {
                    $scrollBar = test;
                } else {
                    return;
                }
            }
            $scrollBar.nanoScroller({
                scroll: 'bottom'
            });
        }, true);

        //text may be formatted using <font>
        //prependDate is optional (default: false)
        function addItem(text, prependDate) {
            $scope.items.push({
                date: (prependDate ? new Date() : null),
                text: text
            });
        }

        function clear() {
            $scope.items = [];
        }

        function minimize() {
            $scope.minimized = true;
        }

        function maximize() {
            $scope.minimized = false;
        }

        function titleBarClicked() {
            if (!$scope.minimized) {
                return;
            }
            $scope.minimized = false;
        }

    }

})();
