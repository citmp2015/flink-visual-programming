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
                $scope.graph.addCells([renderStringFilter(posX, posY)]);
            } else if (data.type === 'numberFilter') {
                $scope.graph.addCells([renderNumberFilter(posX, posY)]);
            }
        };

    }

		function renderNumberFilter(posX, posY) {
				return new joint.shapes.basic.Rect({
						position: { x: posX, y: posY },
						size: { width: 100, height: 30 },
						attrs: {
								rect: { fill: 'blue' },
								text: { text: 'Number Filter', fill: 'white' } }
				});
		}

		function renderStringFilter(posX, posY) {
				return new joint.shapes.basic.Rect({
						position: { x: posX, y: posY },
						size: { width: 100, height: 30 },
						attrs: {
								rect: { fill: 'green' },
								text: { text: 'String Filter', fill: 'white' } }
				});
		}

})();
