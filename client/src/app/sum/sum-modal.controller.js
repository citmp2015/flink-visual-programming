(function() {

    'use strict';

    angular
        .module('app.sum')
        .controller('sumModalCtrl', SumModalCtrl);

    /*@ngInject*/
    function SumModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.sum = {
            inputIndex: cell.attributes.data.inputIndex,
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.data.inputIndex = $scope.sum.inputIndex;
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
