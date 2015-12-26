(function() {

    'use strict';

    angular
        .module('app')
        .config(AppConfig);

    /*@ngInject*/
    function AppConfig(localStorageServiceProvider) {

          localStorageServiceProvider.setPrefix('fvp');

    }

})();
