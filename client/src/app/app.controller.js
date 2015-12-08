(function() {

    'use strict';

    angular
        .module('app')
            .controller('AppCtrl', AppCtrl);

    /*@ngInject*/
    function AppCtrl($scope, $rootScope) {

		$scope.graph = new joint.dia.Graph;

        $scope.onDropComplete = function(data, evt) {
            // TODO: replace static sizes
            var posX = evt.x - 250,
                posY = evt.y - 51;
                console.log(posX, posY);
            if (data.type === 'stringFilter') {
                $scope.graph.addCells([$scope.renderStringFilter(posX, posY)]);
            } else if (data.type === 'numberFilter') {
                $scope.graph.addCells([$scope.renderNumberFilter(posX, posY)]);
            }
        };

    }

})();
