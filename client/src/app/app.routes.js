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
        })

        .state('app.dashboard', {
            url: '/dashboard',
            template: '<div ui-view></div>'
        })

        .state('app.datasource', {
            abstract: true,
            url: '/datasource',
        })

        .state('app.datasource.add', {
            url: '/add',
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: '/app/datasource/datasource-modal.tpl.html',
                    controller: 'DatasourceModalCtrl'
                }).result.finally(function() {
                    $state.go('app.dashboard');
                });
            }]
        });

    }

})();
