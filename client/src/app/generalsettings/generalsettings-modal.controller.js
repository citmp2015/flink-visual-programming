(function() {

    'use strict';

    angular
        .module('app.generalsettings')
        .controller('generalsettingsModalCtrl', GeneralsettingsModalCtrl);

    /*@ngInject*/
    function GeneralsettingsModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
