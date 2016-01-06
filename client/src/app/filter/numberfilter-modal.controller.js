(function() {

    'use strict';

    angular
        .module('app.filter')
        .controller('NumberfilterModalCtrl', FilterModalCtrl);

    /*@ngInject*/
    function FilterModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.operationTypes = [{
            label: '=',
            key: '='
        }, {
            label: '>',
            key: '>'
        }, {
            label: '≥',
            key: '>='
        }, {
            label: '<',
            key: '<'
        }, {
            label: '≤',
            key: '<='
        }];

        console.log(cell.attributes.data.operationType);
        $scope.numberfilter = {
            inputIndex: cell.attributes.data.inputIndex,
            operationType: cell.attributes.data.operationType,
            compareValue: cell.attributes.data.compareValue
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.data.inputIndex = $scope.numberfilter.inputIndex;
            cell.attributes.data.operationType = $scope.numberfilter.operationType;
            cell.attributes.data.compareValue = $scope.numberfilter.compareValue;
            //TODO write to localstorage
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
