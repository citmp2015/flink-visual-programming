'use strict';

var graphApp = graphApp || {
    initialize: function() {
        var self = this;

        self.numberFilter = $('#numberFilter');
        self.paperElement = $('#paper');

        self.initializeGraphs();
        self.initializeDragAndDrop();
    },

    initializeGraphs: function() {
        var self = this;

        self.graph = new joint.dia.Graph();

        self.paper = new joint.dia.Paper({
            el: self.paperElement,
            width: 1500,
            height: 1000,
            model: self.graph
        });

        var rect = new joint.shapes.basic.Rect({
            position: { x: 100, y: 30 },
            size: { width: 100, height: 30 },
            attrs: { rect: { fill: 'blue' }, text: { text: 'my box', fill: 'white' } }
        });

        var rect2 = rect.clone();

        rect2.translate(300);

        var link = new joint.dia.Link({
            source: { id: rect.id },
            target: { id: rect2.id }
        });

        self.graph.addCells([rect, rect2, link]);
    },

    initializeDragAndDrop: function() {
        var self = this;

        self.numberFilter.draggable({
            cursor: 'move',
            revert: 'invalid',
            opacity: 0.7,
            snap: self.paperElement,
            snapMode: 'inner',
            helper: 'clone'
        });

        self.paperElement.droppable({
            accept: self.numberFilter,
            drop: function(event, ui) {
                var position = $(this).position()
                var rect = new joint.shapes.basic.Rect({
                    position: { x: position.left, y: position.top },
                    size: { width: 100, height: 30 },
                    attrs: { rect: { fill: 'green' }, text: { text: 'my box', fill: 'white' } }
                });
                self.graph.addCells([rect]);
            }
        });
    }
};

$(document).ready(function() {
    graphApp.initialize();
});
