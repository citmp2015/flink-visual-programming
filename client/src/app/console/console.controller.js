(function() {

    'use strict';

    angular
        .module('app.console')
        .controller('ConsoleCtrl', ConsoleCtrl);

    /*@ngInject*/
    function ConsoleCtrl($scope, $rootScope) {

        //the console window is completely hidden per default
        //it will be visible after the first call to addItem()

        var $console = $('aside.console');
        var $scrollBar = $console.find('.nano');

        $scope.items = [];
        $scope.minimized = false;

        $scope.$watch('items', function() {
            setTimeout(function(){
                $scrollBar.nanoScroller();
                $scrollBar.nanoScroller({scroll: 'bottom'});
            }, 0);
        }, true);

        $scope.$watch('minimized', function() {
            //$console is outside the controller, otherwise could've used ng-class in the tpl
            if($scope.minimized)
                $console.addClass('minimized');
            else
                $console.removeClass('minimized');
        });

        //text may be formatted using <font>
        //prependDate is optional (default: false)
        $scope.addItem = function(text, prependDate) {

            if(!$console.is(':visible'))
                $console.show();

            if(prependDate)
            {
                var dt = new Date();
                text = '<span class="time">'+
                    '['+
                    (dt.getHours() < 10 ? '0' : '')+dt.getHours()+
                    ':'+(dt.getMinutes() < 10 ? '0' : '')+dt.getMinutes()+
                    ':'+(dt.getSeconds() < 10 ? '0' : '')+dt.getSeconds()+
                    ']</span>'+
                    text
                ;
            }

            $scope.items.push(text);

        };

        $scope.clear = function() {
            $scope.items = [];
        };

        $scope.minimize = function() {
            $scope.minimized = true;
            $console.find('.panel-heading .btn').blur();
        };

        $scope.maximize = function() {
            $scope.minimized = false;
            $console.find('.panel-heading .btn').blur();
        };

        $scope.hide = function() {
            $console.hide();
        };

        $scope.titleBarClicked = function() {
            if(!$scope.minimized)
                return;

            $scope.minimized = false;
        };

    }

})();
