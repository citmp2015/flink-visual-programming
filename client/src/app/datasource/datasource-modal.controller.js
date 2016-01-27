(function() {

    'use strict';

    angular
        .module('app.datasource')
        .controller('CSVDatasourceModalCtrl', CSVDatasourceModalCtrl)
        .controller('TextDatasourceModalCtrl', TextDatasourceModalCtrl);

    /*@ngInject*/
    function CSVDatasourceModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, templateFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.dataTypes = [{
            label: '- none -',
            key: 'none'
        }, {
            label: 'String',
            key: 'string'
        }, {
            label: 'Float',
            key: 'float'
        }, {
            label: 'Integer',
            key: 'integer'
        }];

        $scope.datasource = {
            filePath: cell.attributes.formdata.filePath,
            countColumns: cell.attributes.formdata.countColumns,
            columns: cell.attributes.formdata.columns,
            javaSourceCode: cell.attributes.formdata.javaSourceCode
        };

        $scope.save = save;
        $scope.cancel = cancel;

        $scope.$watch('datasource.countColumns', function(newValue, oldValue) {
            var i;
            if ($scope.datasource.columns.length < newValue) {
                for (i = $scope.datasource.columns.length; i < newValue; i++) {
                    $scope.datasource.columns.push({
                        column: i,
                        type: angular.extend({}, $scope.dataTypes[0])
                    });
                }
            } else {
                for (i = $scope.datasource.columns.length; i > newValue; i--) {
                    $scope.datasource.columns.pop();
                }
            }
        });

        function save() {
            cell.attributes.formdata.filePath = $scope.datasource.filePath;
            cell.attributes.formdata.countColumns = $scope.datasource.countColumns;
            cell.attributes.formdata.columns = $scope.datasource.columns;
            cell.attributes.formdata.output_type = getTypeString(cell.attributes.formdata.columns);
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function getTypeString(columns) {
            if (columns.length > 0) {
                var types = columns[0].type.label;

                for (var index = 1; index < columns.length; index++) {
                    types = types + ',' + columns[index].type.label;
                }
                return 'Tuple' + columns.length + '<' + types + '>';
            }
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

    function TextDatasourceModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.datasource = {
            filePath: cell.attributes.formdata.filePath
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.formdata.filePath = $scope.datasource.filePath;
            cell.attributes.formdata.output_type = 'String';
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
