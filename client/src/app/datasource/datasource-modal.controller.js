(function() {

    'use strict';

    angular
        .module('app.datasource')
        .controller('DatasourceModalCtrl', DatasourceModalCtrl);

    /*@ngInject*/
    function DatasourceModalCtrl($scope, $rootScope, $uibModalInstance) {

        $scope.dataTypes = [{
            label: '- none -',
            key: 'none',
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
            path: null,
            countColumns: 2,
            columns: []
        };

        $scope.save = save;
        $scope.cancel = cancel;

        $scope.$watch('datasource.countColumns', function(newValue, oldValue) {
            if ($scope.datasource.columns.length < newValue) {
                for (var i = $scope.datasource.columns.length; i < newValue; i++) {
                    $scope.datasource.columns.push({
                        column: i,
                        type: angular.extend({}, $scope.dataTypes[0])
                    });
                }
            } else {
                for (var i = $scope.datasource.columns.length; i > newValue; i--) {
                    $scope.datasource.columns.pop();
                }
            }
        });

        function save() {
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
