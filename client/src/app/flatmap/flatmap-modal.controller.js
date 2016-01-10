(function() {

    'use strict';

    angular
        .module('app.flatmap')
        .controller('flatmapModalCtrl', FlatmapModalCtrl);

    /*@ngInject*/
    function FlatmapModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);
		
		$scope.editor=cell.attributes.data.javaSourceCode;
        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.data.javaSourceCode = $scope.editor;
			graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
