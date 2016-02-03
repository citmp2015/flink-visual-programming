(function() {

    'use strict';

    var module = angular.module('app');

    if (window.location.hostname === 'localhost') {
        module.constant('ENDPOINT', '//localhost:8080');
    } else {
        module.constant('ENDPOINT', '');
    }

})();
