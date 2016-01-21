(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuNavbarTopCtrl', MenuNavbarTopCtrl);

    /*@ngInject*/
    function MenuNavbarTopCtrl($scope, $rootScope, graphFactory, jsonBuilder, $log, $http) {

        $scope.clearGraph = clearGraph;
        $scope.exportGraph = exportGraph;
        $scope.importGraph = importGraph;
        $scope.runGraph = runGraph;
        $scope.getCode = getCode;
        $scope.getJar = getJar;
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

        function sendGraph(action) {
            var json = jsonBuilder.buildJson($rootScope.graph);
            //console.log('initial', $rootScope.graph.toJSON());
            console.log('sending the following object', json);
            var formData = {
                action: action,
                graph: JSON.stringify(json)
            };
            var config = {
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            };
            $http.post('submit_jobgraph', formData, config).then(
                function successCallback(response) {
                    console.log(response);
                }, function errorCallback(response) {
                    console.log(response);
                }
            );
        }

        function runGraph() {
            sendGraph('deploy');
        }

        function getCode() {
            sendGraph('download_sources');
        }

        function getJar() {
            sendGraph('download_jar');
        }

    }

})();
