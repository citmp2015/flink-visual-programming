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

        $scope.$on('graph:mvnBuildOutput', function(e, uuid, output) {
            addItem(uuid, output, true);
        });

        //text may be formatted using <font>
        //prependDate is optional (default: false)
        var $scrollBar = null;
        var uuids = [];
        function addItem(uuid, text, prependDate) {
            var uuidIndex = uuids.indexOf(uuid);
            if (uuidIndex === -1) {
                uuidIndex = uuids.push(uuid) - 1;
            }
            $scope.items.push({
                number: uuidIndex + 1,
                uuid: uuid,
                text: text,
                date: (prependDate ? new Date() : null),
            });
            maximize();
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
