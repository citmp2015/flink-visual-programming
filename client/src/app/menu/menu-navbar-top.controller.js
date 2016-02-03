(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuNavbarTopCtrl', MenuNavbarTopCtrl);

    /*@ngInject*/
    function MenuNavbarTopCtrl($scope, $rootScope, graphFactory, jsonBuilder, $log, $http, $uibModal, ENDPOINT) {

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
            $('.import-wrapper input[type="file"]').click();
        }

        $scope.openConfiguration = function() {
            $uibModal.open({
                templateUrl: '/app/generalsettings/generalsettings-modal.tpl.html',
                controller: 'generalsettingsModalCtrl',
                backdrop: 'static'
            });
        };

        function sendGraph(action) {
            var loadingModal = $uibModal.open({
                templateUrl: '/app/loadingmodal/loadingmodal.tpl.html',
                controller: 'loadingModalCtrl',
                backdrop: 'static'
            });

            setTimeout(function(){
                var json = jsonBuilder.buildJson($rootScope.graph);
                var formData = {
                    action: action,
                    graph: json
                };
                console.log('Sending', JSON.stringify(json));
                $http.post(ENDPOINT + '/submit_jobgraph', formData, config).then(
                    function successCallback(response) {
                        console.log(response);
                        loadingModal.close();
                    }, function errorCallback(response) {
                        console.log(response);
                        loadingModal.close();
                    }
                );
            }, 1500);
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
