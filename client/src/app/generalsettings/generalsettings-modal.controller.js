(function() {

    'use strict';

    angular
        .module('app.generalsettings')
        .controller('generalsettingsModalCtrl', GeneralsettingsModalCtrl);

    /*@ngInject*/
    function GeneralsettingsModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, localStorageService, $log) {

        var config = localStorageService.get('config') || {};
        $scope.generalsettings = {
            flinkURL: config.flinkURL || '',
            flinkPort: config.flinkPort || ''
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            localStorageService.set('config', $scope.generalsettings);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
