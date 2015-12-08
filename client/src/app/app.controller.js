(function() {

    'use strict';

    angular
        .module('app')
            .controller('AppCtrl', AppCtrl);

    /*@ngInject*/
    function AppCtrl($scope, $rootScope) {

				$scope.graph = new joint.dia.Graph;

        $scope.onDropComplete = function(data, evt) {
            console.log($scope);
            $scope.graph.addCells([$scope.renderNumberFilter(evt.x, evt.y)]);
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
