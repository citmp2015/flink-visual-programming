(function() {

    'use strict';

    angular
        .module('app')
        .config(AppConfig);

    /*@ngInject*/
    function AppConfig(localStorageServiceProvider, $compileProvider) {

          localStorageServiceProvider.setPrefix('fvp');

          $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|data):/);

    }

})();
