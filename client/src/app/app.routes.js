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
            templateUrl: 'view/app.html'
        })

        .state('app.dashboard', {
            url: '/dashboard',
            template: '<div ui-view></div>'
        });

    }

})();
