(function() {

    'use strict';

    angular
        .module('app.datasink')
        .controller('CSVDatasinkModalCtrl', CSVDatasinkModalCtrl);

    function CSVDatasinkModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        $scope.generalsettings = graphFactory.getGeneralSettings();

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.datasink = {
            filePath: cell.attributes.formdata.filePath
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.formdata.filePath = $scope.datasink.filePath;
            cell.attributes.formdata.output_type = 'String'; // jshint ignore:line
            cell.attr('.infoLabel/text', $scope.datasink.filePath.replace(/^.*[\\\/]/, ''));
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
