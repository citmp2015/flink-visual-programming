(function() {
    'use strict';

    angular
        .module('app')
        .directive('jointDiagram', jointDiagram);


    /*@ngInject*/
    function jointDiagram($state, $log) {

        function link(scope, element, attrs) {

            function getPortType(view, magnet) {
                var portName = magnet.getAttribute('port');
                var inIndex = view.model.get('inPorts').indexOf(portName);
                if (inIndex > -1) return 'IN';
                var outIndex = view.model.get('outPorts').indexOf(portName);
                if (outIndex > -1) return 'OUT';
            }

            function isMagnetUsable(cellView, magnet) {
                var portType = getPortType(cellView, magnet);
                return portType == 'OUT';
            }

            function isPortInUse(graph, cellView, magnet, linkView) {
                var links = graph.getLinks(cellView);
                for (var i = 0; i < links.length; i++) {
                    if (linkView && linkView == links[i].findView(paper)) continue;
                    if ((( cellView.model.id == links[i].get('source').id ) && ( magnet.getAttribute('port') == links[i].get('source').port) ) ||
                        (( cellView.model.id == links[i].get('target').id ) && ( magnet.getAttribute('port') == links[i].get('target').port) ))
                        return true
                }
                return false;
            }

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

            function isValidConnection(sourceView, sourceMagnet, targetView, targetMagnet, end, linkView) {
                if (getPortType(targetView, targetMagnet) == 'OUT') return false;
                if (isPortInUse(scope.graph, targetView, targetMagnet, linkView)) return false;
                if (sourceView == targetView) return false; // already a cycle
                if (hasCycle(scope.graph, sourceView, targetView)) return false;

                return true;
            }

            var paper = new joint.dia.Paper({
                el: element[0],
                width: angular.element(element[0])[0].scrollWidth,
                height: angular.element(element[0])[0].scrollHeight,
                gridSize: scope.gridSize,
                model: scope.graph,
                snapLinks: { radius: 75 },
                validateMagnet: isMagnetUsable,
                validateConnection: isValidConnection
            });

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
