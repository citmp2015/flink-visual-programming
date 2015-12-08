(function() {

    'use strict';

    angular
        .module('app')
            .controller('AppCtrl', AppCtrl);

    /*@ngInject*/
    function AppCtrl($scope, $rootScope) {

        console.log($scope);

        // $scope.numberFilter = function() {
        //     $scope.renderNumberFilter();
        // };

        $scope.onDropComplete = function(data, evt) {
            scope.graph.addCells([numberFilterA(evt.x, evt.y)]);
        };

        function numberFilterA(posX, posY) {
            var rect = new joint.shapes.basic.Rect({
                position: { x: posX, y: posY },
                size: { width: 100, height: 30 },
                attrs: {
                    rect: { fill: 'blue' },
                    text: { text: 'Number Filter', fill: 'white' } }
            });

            return rect;
        }

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
