(function() {

    'use strict';

    angular
        .module('app')
        .factory('jsonBuilder', jsonBuilder);



    /*@ngInject*/
    function jsonBuilder() {
        var builder = {};

        builder.buildJson = function(graph) {
            var initialJson = graph.toJSON();

            // do some stuff
            var json = initialJson;

            return json;
        };

        return builder;
    }

})();
