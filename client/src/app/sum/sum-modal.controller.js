(function() {

    'use strict';

    angular
        .module('app.sum')
        .controller('sumModalCtrl', SumModalCtrl);

    /*@ngInject*/
    function SumModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.sum = {
            tupleIndex: cell.attributes.formdata.tupleIndex
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.formdata.tupleIndex = $scope.sum.tupleIndex;
            cell.attr('.infoLabel/text', 'sum('+$scope.sum.tupleIndex+')');
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
