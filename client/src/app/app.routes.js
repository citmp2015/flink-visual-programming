(function() {

    'use strict';

    angular
        .module('app')
        .config(configRoutes);

    /*@ngInject*/
    function configRoutes($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/dashboard');

        $stateProvider.state('app', {
            abstract: true,
            url: '',
            templateUrl: 'view/app.html',
            controller: 'AppCtrl'
        })

        .state('app.dashboard', {
            url: '/dashboard',
            template: '<div ui-view></div>'
        })

        .state('app.component', {
            url: '/component/:id',
            params: {
                modalController: null,
                modalTemplateUrl: null
            },
            onEnter: ['$stateParams', '$state', '$uibModal', '$log', '$rootScope', function($stateParams, $state, $uibModal, $log, $rootScope) {

                if (!$stateParams.modalController || !$stateParams.modalTemplateUrl) {
                    $state.go('app.dashboard');
                    var param = !$stateParams.modalController ? 'modalController' : 'modalTemplateUrl';
                    return $log.error('Param "%s" is missing', param);
                }

                if (!$rootScope.graph.getCell($stateParams.id)) {
                    $state.go('app.dashboard');
                    return $log.error('Cell %s does not exists', $stateParams.id);
                }

                $uibModal.open({
                    templateUrl: $stateParams.modalTemplateUrl,
                    controller: $stateParams.modalController,
                    backdrop: 'static'
                }).result.finally(function() {
                    $state.go('app.dashboard');
                });

            }]
        });

    }

})();
