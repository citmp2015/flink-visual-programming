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

            var json = {
                processes: {},
                connections: []
            };

            if (!initialJson.cells) {
                return json;
            }

            for (var i = 0; i < initialJson.cells.length; i++) {
                var cell = initialJson.cells[i];
                var id = cell.id;
                if (cell.type === 'devs.Atomic') { // component
                    json.processes[id] = {
                        component: cell.componentType || 'unknown',
                        data: cell.formdata
                    };
                } else if (cell.type === 'devs.Link') { // link
                    json.connections.push({
                        id: id,
                        src: cell.source.id,
                        tgt: cell.target.id
                    });

                }
            }
            return json;
        };

        return builder;
    }

})();
