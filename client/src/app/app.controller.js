(function() {

    'use strict';

    angular
        .module('app')
            .controller('AppCtrl', AppCtrl);

    /*@ngInject*/
    function AppCtrl($scope, $rootScope) {
        console.log($scope);

        $scope.onDropComplete = function(data, evt) {
            console.log($scope.numberFilter(evt.x, evt.y));
            $scope.graph.addCells([$scope.numberFilter(evt.x, evt.y)]);
        };

        function renderStringFilter(posX, posY) {
            var rect = new joint.shapes.basic.Rect({
                position: { x: posX, y: posY },
                size: { width: 100, height: 30 },
                attrs: {
                    rect: { fill: 'green' },
                    text: { text: 'String Filter', fill: 'white' } }
            });

            return rect;
        }

    }

})();
