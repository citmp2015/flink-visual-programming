(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuNavbarTopCtrl', MenuNavbarTopCtrl);

    /*@ngInject*/
    function MenuNavbarTopCtrl($scope, $rootScope, graphFactory, jsonBuilder, $q, $window, $log, $http, $uibModal, $timeout, localStorageService, hotkeys) {

        $scope.clearGraph = clearGraph;
        $scope.exportGraph = exportGraph;
        $scope.importGraph = importGraph;

        $scope.deployGraph = deployGraph;
        $scope.downloadSrc = downloadSrc;
        $scope.downloadJar = downloadJar;

        $scope.undoGraph = undoGraph;
        $scope.redoGraph = redoGraph;
        $scope.exportHref = '#';

        $scope.importFile = null;
        $scope.onFileLoaded = onFileLoaded;
        $scope.onFileError = onFileError;

        $scope.openConfigModal = openConfigModal;

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

        function openConfigModal() {
            $uibModal.open({
                templateUrl: '/app/generalsettings/generalsettings-modal.tpl.html',
                controller: 'generalsettingsModalCtrl',
                backdrop: 'static'
            });
        }

        var loadingModal;

        function openLoadingModal() {
            loadingModal = $uibModal.open({
                templateUrl: '/app/loadingmodal/loadingmodal.tpl.html',
                controller: 'loadingModalCtrl',
                backdrop: 'static'
            });
        }

        function closeLoadingModal() {
            loadingModal.close();
        }

        function sendGraph(action) {

            return $q(function(resolve, reject) {

                $http.put(graphFactory.getGeneralSettings().flinkUrl + '/graph', jsonBuilder.buildJson($rootScope.graph), {
                    responseType: 'json'
                }).then(function successCallback(response) {
                    if (response.status !== 200 || !response.data) {
                        $log.error('Error on PUT /graph', response);
                        return reject(response);
                    }
                    $log.debug(response);
                    resolve(response.data);
                }, function errorCallback(response) {
                    $log.error('Error on PUT /graph', response);
                    reject(response);
                });

            });

        }

        function deployGraph() {
            openLoadingModal();
            sendGraph().then(function(data) {
                $scope.$on('graph:' + data.uuid + ':deployed', function() {
                    closeLoadingModal();
                });
            }, function() {
                closeLoadingModal();
            });
        }

        function downloadSrc() {
            openLoadingModal();
            sendGraph().then(function(data) {
                $scope.$on('graph:' + data.uuid + ':mvnBuildSucceeded', function() {
                    download('/graph/zip/' + data.uuid, function() {
                        closeLoadingModal();
                    });
                });
            }, function() {
                closeLoadingModal();
            });
        }

        function downloadJar() {
            openLoadingModal();
            sendGraph().then(function(data) {
                $scope.$on('graph:' + data.uuid + ':mvnBuildSucceeded', function() {
                    download('/graph/zip/' + data.uuid, function() {
                        closeLoadingModal();
                    });
                });
            }, function() {
                closeLoadingModal();
            });
        }

        function download(path, callback) {
            $http.get(graphFactory.getGeneralSettings().flinkUrl + path, {
                responseType: 'blob'
            }).then(function successCallback(response) {
                var filename = /filename="([\w\.]+)"/ig.exec(response.headers('Content-Disposition'))[1];
                var blob = new Blob([response.data], {
                    type: response.headers('Content-Type')
                });
                if ($window.navigator.msSaveOrOpenBlob) {
                    $window.navigator.msSaveBlob(blob, filename);
                } else {
                    var elem = $window.document.createElement('a');
                    elem.href = $window.URL.createObjectURL(blob);
                    elem.download = filename;
                    document.body.appendChild(elem);
                    elem.click();
                    document.body.removeChild(elem);
                }
                callback();
            }, function errorCallback(response) {
                $log.error('error while sending the jobgraph', response);
                callback(response);
            });
        }

        function undoGraph() {
            var currentPosition = graphFactory.currentGraphStackPosition.get();
            if (currentPosition > 0) {
                currentPosition--;
            }
            graphFactory.currentGraphStackPosition.set(currentPosition);
            renderGraph();
        }

        function redoGraph() {
            var currentPosition = graphFactory.currentGraphStackPosition.get();
            if (currentPosition < graphFactory.graphStack.size() - 1) {
                currentPosition++;
            }
            graphFactory.currentGraphStackPosition.set(currentPosition);
            renderGraph();
        }

        function renderGraph() {
            var currentPosition = graphFactory.currentGraphStackPosition.get(),
                lastGraph = graphFactory.graphStack.get(currentPosition);

            if (lastGraph !== null) {
                $rootScope.graph.fromJSON(lastGraph);
            }
        }

        $scope.$watch(function() {
            return graphFactory.currentGraphStackPosition.get();
        }, function(newVal, oldVal) {
            if (newVal <= 0) {
                $scope.canUndo = false;
            } else {
                $scope.canUndo = true;
            }

            if (newVal < graphFactory.graphStack.size() - 1) {
                $scope.canRedo = true;
            } else {
                $scope.canRedo = false;
            }
        });

    }

})();
