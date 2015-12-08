'use strict';

var graphApp = graphApp || {};
    graphApp.components = graphApp.components || {},
    graphApp.components.stringFilter = graphApp.components.stringFilter || {

    initialize: function() {
        var self = this;

        self.stringFilter = $('#stringFilter');
        self.paperElement = $('#paper');

        self.stringFilter.draggable({
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
                rect: { fill: 'green' },
                text: { text: 'String Filter', fill: 'white' } }
        });
        return rect;
    }
};
