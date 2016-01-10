(function() {

    'use strict';

    angular
        .module('app.group')
        .controller('groupModalCtrl', GroupModalCtrl);

    /*@ngInject*/
    function GroupModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.group = {
            inputIndex: cell.attributes.data.inputIndex,
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.data.inputIndex = $scope.group.inputIndex;
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
