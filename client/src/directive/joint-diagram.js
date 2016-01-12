(function() {
    'use strict';

    angular
        .module('app')
        .directive('jointDiagram', jointDiagram);


    /*@ngInject*/
    function jointDiagram($state, $log, graphFactory) {

        function link(scope, element, attrs) {
            /**
             * Assumes that the portname is in either inPorts or outPorts, but not both
             *
             * @param view view of the cell the port belongs to
             * @param magnet port of the view
             * @return {'IN'|'OUT'} depending on the type of the port
             */
            function getPortType(view, magnet) {
                var portName = magnet.getAttribute('port');
                var inIndex = view.model.get('inPorts').indexOf(portName);
                if (inIndex > -1) return 'IN';
                var outIndex = view.model.get('outPorts').indexOf(portName);
                if (outIndex > -1) return 'OUT';
            }

            /**
             * Returns true if the magnet/port of the cellView should be usable to start
             * creating a link. The current intention is to only allow links to start from
             * out ports, so that cycle detection is easier.
             *
             * @param cellView
             * @param magnet
             * @returns {boolean}
             */
            function isMagnetUsable(cellView, magnet) {
                var portType = getPortType(cellView, magnet);
                return portType === 'OUT';
            }

            /**
             * Adapted from: http://stackoverflow.com/questions/30223776/in-jointjs-how-can-i-restrict-the-number-of-connections-to-each-input-to-just-o
             *
             * Tests if the port given is in use.
             * Ignores the link that is currently being created, so that is doesnt block itself
             *
             * @param graph
             * @param cellView
             * @param magnet
             * @param linkView the link to ignore
             * @returns {boolean}
             */
            function isPortInUse(graph, cellView, magnet, linkView) {
                var links = graph.getLinks(cellView);
                for (var i = 0; i < links.length; i++) {
                    if (linkView && linkView === links[i].findView(paper)) continue;
                    if ((( cellView.model.id === links[i].get('source').id ) && ( magnet.getAttribute('port') === links[i].get('source').port) ) ||
                        (( cellView.model.id === links[i].get('target').id ) && ( magnet.getAttribute('port') === links[i].get('target').port) ))
                        return true;
                }
                return false;
            }

            /**
             * Adds the link that would be created by sourceView and targetView to the graph,
             * and then checks if the resulting graph has a cycle.
             *
             * @param graph
             * @param sourceView
             * @param targetView
             * @returns {boolean}
             */
            function hasCycle(graph, sourceView, targetView) {
                var glGraph = graph.toGraphLib();
                var sourceId = sourceView.model.id;
                var targetId = targetView.model.id;
                if (!sourceId || !targetId) {
                    return false;
                }
                glGraph.setEdge(sourceId, targetId);
                return !graphlib.alg.isAcyclic(glGraph);
            }

            /**
             * Returns true if the connection that is currently being created would still
             * leave the graph in a valid state.
             * Since our links always start at an out port we only need to disallow a link if:
             *  - the target is an out port
             *  - the (in) port of the targetView already in use
             *  - the newly created link would create a cycle
             *
             * @param sourceView
             * @param sourceMagnet
             * @param targetView
             * @param targetMagnet
             * @param end
             * @param linkView
             * @returns {boolean}
             */
            function isValidConnection(sourceView, sourceMagnet, targetView, targetMagnet, end, linkView) {
                if (getPortType(targetView, targetMagnet) === 'OUT') return false;
                if (isPortInUse(scope.graph, targetView, targetMagnet, linkView)) return false;
                if (sourceView === targetView) return false; // already a cycle
                if (hasCycle(scope.graph, sourceView, targetView)) return false;

                return true;
            }

            var paper = new joint.dia.Paper({
                el: element[0],
                gridSize: scope.gridSize,
                linkPinning: false,
                defaultLink: new graphFactory.Link(),
                model: scope.graph,
                snapLinks: { radius: 75 },
                validateMagnet: isMagnetUsable,
                validateConnection: isValidConnection
            });
            paper.setDimensions('100%', '100%');

            paper.on('cell:pointerdblclick', function(cellView, evt, x, y) {
                $log.log('cell:pointerdblclick', cellView.model);
                if (cellView.model.attributes.data && cellView.model.attributes.data.modalController) {
                    $state.go('app.component', {
                        id: cellView.model.id,
                        modalController: cellView.model.attributes.data ? cellView.model.attributes.data.modalController : undefined,
                        modalTemplateUrl: cellView.model.attributes.data ? cellView.model.attributes.data.modalTemplateUrl : undefined
                    });
                }
            });

            paper.on('link:options', function(evt, cellView, x, y) {
                // your logic here: e.g. select a link by its options tool
            });

            paper.on('cell:contextmenu', function(cellView, evt) {
                cellView.model.remove();
                evt.preventDefault();
            });

            scope.graph.trigger('paper:ready');
        }

        return {
            link: link,
            restrict: 'E',
            scope: {
                gridSize: '=',
                graph: '='
            }
        };

    }

})();
