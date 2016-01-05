(function() {

    'use strict';

    var module = angular.module('app');

    if (window.location.hostname === 'localhost') {
        module.constant('ENDPOINT', 'http://localhost:8080');
    } else {
        module.constant('ENDPOINT', 'http://asok16.cit.tu-berlin.de:8081');
    }

})();
