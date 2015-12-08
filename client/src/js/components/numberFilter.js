'use strict';

var graphApp = graphApp || {};
    graphApp.components = graphApp.components || {},
    graphApp.components.numberFilter = graphApp.components.numberFilter || {

    initialize: function() {
        var self = this;

        self.numberFilter = $('#numberFilter');
        self.paperElement = $('#paper');

        self.numberFilter.draggable({
            cursor: 'move',
            revert: 'invalid',
            opacity: 0.7,
            snap: self.paperElement,
            snapMode: 'inner',
            helper: 'clone'
        });
    },

    get: function(posX, posY) {
        var rect = new joint.shapes.basic.Rect({
            position: { x: posX, y: posY },
            size: { width: 100, height: 30 },
            attrs: {
                rect: { fill: 'blue' },
                text: { text: 'Number Filter', fill: 'white' } }
        });
        return rect;
    }
};
