(function() {

    'use strict';

    angular
        .module('app.filter')
        .controller('NumberfilterModalCtrl', FilterModalCtrl);

    /*@ngInject*/
    function FilterModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, templateFactory, $log) {

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

        $scope.numberfilter = {
            tupleIndex: cell.attributes.formdata.tupleIndex,
            operationType: cell.attributes.formdata.operationType,
            compareValue: cell.attributes.formdata.compareValue,
			javaSourceCode: cell.attributes.formdata.javaSourceCode
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.formdata.tupleIndex = $scope.numberfilter.tupleIndex;
            cell.attributes.formdata.operationType = $scope.numberfilter.operationType;
            cell.attributes.formdata.compareValue = $scope.numberfilter.compareValue;
			cell.attributes.formdata.javaSourceCode = templateFactory.createNumberFilterTemplate($scope.numberfilter.operationType, $scope.numberfilter.compareValue);
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
