(function() {
    'use strict';

    angular
        .module('app')
        .directive('numberFilter', numberFilter);


    /*@ngInject*/
    function numberFilter() {
        function link(scope, element, attrs) {

            scope.renderNumberFilter = newNumberFilter();

        }

        function newNumberFilter() {
            var rect = new joint.shapes.basic.Rect({
                position: { x: posX, y: posY },
                size: { width: 100, height: 30 },
                attrs: {
                    rect: { fill: 'blue' },
                    text: { text: 'Number Filter', fill: 'white' } }
            });

            return rect;

        }

        return {
            link: link,
            restrict: 'A'
        };

    }

})();
