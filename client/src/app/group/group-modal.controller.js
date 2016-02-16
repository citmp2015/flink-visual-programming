(function() {

    'use strict';

    angular
        .module('app.group')
        .controller('groupModalCtrl', GroupModalCtrl);

    /*@ngInject*/
    function GroupModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.group = {
            tupleIndex: cell.attributes.formdata.tupleIndex
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.formdata.tupleIndex = $scope.group.tupleIndex;
            cell.attr('.infoLabel/text', 'groupBy('+$scope.group.tupleIndex+')');
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
