(function() {

    'use strict';

    angular
        .module('app.generalsettings')
        .controller('generalsettingsModalCtrl', GeneralsettingsModalCtrl);

    /*@ngInject*/
    function GeneralsettingsModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

        $scope.generalsettings = graphFactory.getGeneralSettings();

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            graphFactory.setGeneralSettings($scope.generalsettings);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
