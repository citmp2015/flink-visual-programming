'use strict';

var graphApp = graphApp || {
    initialize: function() {
        var self = this;

        self.paperElement = $('#paper');

        self.initializeGraphs();
        self.initializeDragAndDrop();

        self.components.numberFilter.initialize();
        self.components.stringFilter.initialize();
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

        self.paperElement.droppable({
            drop: function(event, ui) {
                console.log(event);
                console.log(ui);
                var elementOffset = $(this).offset(),
                    posX = ui.offset.left - elementOffset.left,
                    posY = ui.offset.top - elementOffset.top,
                    rect = self.components[ui.draggable[0].id].get(posX, posY);
                self.graph.addCells([rect]);
            }
        });
    }
};

$(document).ready(function() {
    graphApp.initialize();
});
