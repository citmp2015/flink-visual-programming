(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuNavbarTopCtrl', MenuNavbarTopCtrl);

    /*@ngInject*/
    function MenuNavbarTopCtrl($scope, $rootScope, graphFactory, $log) {

        $scope.clearGraph = clearGraph;
        $scope.exportGraph = exportGraph;
        $scope.importGraph = importGraph;
        $scope.exportHref = '#';

        $scope.importFile = null;
        $scope.onFileLoaded = onFileLoaded;
        $scope.onFileError = onFileError;

        function onFileLoaded() {
            var data = JSON.parse($scope.importFile);
            $rootScope.graph.fromJSON(data);
        }

        function onFileError(error) {
            $log.debug('onFileError');
            $log.debug(error);
        }

        function clearGraph() {
            graphFactory.clearGraph($rootScope.graph);
        }

        function exportGraph() {
            $scope.exportHref = 'data:text/json;charset=utf-8,' + encodeURIComponent(JSON.stringify($rootScope.graph.toJSON()));
        }

        function importGraph(argument) {
            // body...
        }

    }

})();
