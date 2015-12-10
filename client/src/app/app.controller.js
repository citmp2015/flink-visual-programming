(function() {

    'use strict';

    angular
        .module('app')
        .controller('AppCtrl', AppCtrl);

    /*@ngInject*/
    function AppCtrl($scope, $rootScope, $state, graphFactory, $log) {

        var graph = new joint.dia.Graph();

        graph.on('add', function(cell) {
            if (cell.attributes.data && cell.attributes.data.modalController) {
                $state.go('app.component', {
                    id: cell.id,
                    modalController: cell.attributes.data.modalController,
                    modalTemplateUrl: cell.attributes.data.modalTemplateUrl
                });
            }
        });

        graph.on('change', function(cell) {

        });

        graph.on('remove', function(cell) {

        });

        function onDropComplete(data, evt) {
            // TODO: replace static sizes
            var posX = evt.x - 250,
                posY = evt.y - 51;

            if (data.type === 'stringFilter') {
                $scope.graph.addCells([graphFactory.renderStringFilter(posX, posY, 1, 2)]);
            } else if (data.type === 'numberFilter') {
                $scope.graph.addCells([graphFactory.renderNumberFilter(posX, posY, 1, 1)]);
            } else if (data.type === 'csvDatasource') {
                $scope.graph.addCells([graphFactory.renderCsvDatasource(posX, posY, $state)]);
            }
        }

        $scope.onDropComplete = onDropComplete;
        $rootScope.graph = graph;

    }

})();
