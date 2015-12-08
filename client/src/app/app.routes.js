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
        });

    }

})();
