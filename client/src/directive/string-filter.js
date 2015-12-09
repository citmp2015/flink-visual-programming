(function() {
    'use strict';

    angular
        .module('app')
        .directive('stringFilter', stringFilter);


    /*@ngInject*/
    function stringFilter() {
        function link(scope, element, attrs) {

            scope.renderStringFilter = newStringFilter;

        }

        function newStringFilter(posX, posY) {
            var rect = new joint.shapes.basic.Rect({
                position: { x: posX, y: posY },
                size: { width: 100, height: 30 },
                attrs: {
                    rect: { fill: 'green' },
                    text: { text: 'String Filter', fill: 'white' } }
            });

            return rect;

        }

        return {
            link: link,
            restrict: 'A'
        };

    }

})();
