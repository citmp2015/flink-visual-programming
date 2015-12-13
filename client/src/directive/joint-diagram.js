(function() {
    'use strict';

    angular
        .module('app')
        .directive('jointDiagram', jointDiagram);


    /*@ngInject*/
    function jointDiagram($state, $log) {

        function link(scope, element, attrs) {

            var paper = new joint.dia.Paper({
                el: element[0],
                gridSize: scope.gridSize,
                linkPinning: false,
                model: scope.graph,
				snapLinks: { radius: 75 },
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
