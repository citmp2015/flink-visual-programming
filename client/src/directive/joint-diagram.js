(function() {
    'use strict';

    angular
        .module('app')
        .directive('jointDiagram', jointDiagram);


    /*@ngInject*/
    function jointDiagram($state, $log) {

        function link(scope, element, attrs) {

            function isMagnetUsable(cellView, magnet) {
                return true;
            }

            function isValidConnection(sourceView, sourceMagnet, targetView, targetMagnet, end, linkView) {
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
