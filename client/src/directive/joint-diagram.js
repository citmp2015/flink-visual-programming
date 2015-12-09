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

            var rect = new joint.shapes.basic.Rect({
                position: {
                    x: 100,
                    y: 30
                },
                size: {
                    width: 100,
                    height: 30
                },
                attrs: {
                    rect: {
                        fill: 'blue'
                    },
                    text: {
                        text: 'my box',
                        fill: 'white'
                    }
                }
            });

            var rect2 = rect.clone();
            rect2.translate(300);

            var link = new joint.dia.Link({
                source: {
                    id: rect.id
                },
                target: {
                    id: rect2.id
                }
            });

            scope.graph.addCells([rect, rect2, link]);

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
