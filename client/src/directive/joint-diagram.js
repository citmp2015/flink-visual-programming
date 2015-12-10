(function() {
    'use strict';

    angular
        .module('app')
        .directive('jointDiagram', jointDiagram);


    /*@ngInject*/
    function jointDiagram() {

        function link(scope, element, attrs) {

            var diagram = newDiagram(scope, scope.height, scope.width, scope.gridSize, element[0]);

            //add event handlers to interact with the diagram
            diagram.on('cell:pointerclick', function(cellView, evt, x, y) {
                //your logic here e.g. select the element
            });

            diagram.on('blank:pointerclick', function(evt, x, y) {
                // your logic here e.g. unselect the element by clicking on a blank part of the diagram
            });

            diagram.on('link:options', function(evt, cellView, x, y) {
                // your logic here: e.g. select a link by its options tool
            });

        }

        function newDiagram(scope, height, width, gridSize, targetElement) {

            var paper = new joint.dia.Paper({
                el: targetElement,
                width: angular.element(targetElement)[0].scrollWidth,
                height: angular.element(targetElement)[0].scrollHeight,
                gridSize: gridSize,
                model: scope.graph,
            });
			
            return paper;
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
