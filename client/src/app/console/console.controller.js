(function() {

    'use strict';

    angular
        .module('app.console')
        .controller('ConsoleCtrl', ConsoleCtrl);

    /*@ngInject*/
    function ConsoleCtrl($scope, $rootScope) {

        $scope.items = [];
        $scope.visible = true;
        $scope.minimized = true;

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

        $scope.titleBarClicked = function() {
            if(!$scope.minimized)
                return;

            $scope.minimized = false;
        };

    }

})();
