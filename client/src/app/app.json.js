(function() {

    'use strict';

    angular
        .module('app')
        .factory('jsonBuilder', jsonBuilder);

    /*@ngInject*/
    function jsonBuilder() {
        var builder = {};

        // make sure the id starts with a character and only contains alphanumeric characters
        builder.fixId = function(id) {
            return ('a' + id).replace(/-/g, '');
        };

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
                var id = this.fixId(cell.id);
                if (cell.type === 'flink.Model') { // component
                    json.processes[id] = {
                        component: cell.componentType || 'unknown',
                        data: cell.formdata
                    };
                } else if (cell.type === 'devs.Link') { // link
                    json.connections.push({
                        id: id,
                        src: this.fixId(cell.source.id),
                        tgt: this.fixId(cell.target.id)
                    });

                }
            }
            return json;
        };

        return builder;
    }

})();
