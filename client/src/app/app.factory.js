(function() {

    'use strict';

    angular
        .module('app')
        .factory('graphFactory', GraphFactory);

    /*@ngInject*/
    function GraphFactory(localStorageService, templateFactory) {

        var flink = {};
        joint.shapes.flink = {};

        flink.getGeneralSettings = function() {
            return localStorageService.get('config');
        };

        flink.setGeneralSettings = function(extConfig) {
            var config = extConfig || {};
            localStorageService.set('config', {
                flinkURL: config.flinkURL || 'http://localhost',
                flinkPort: config.flinkPort || '8080'
            });
        };

        flink.saveToLocalStorage = function(graph) {
            localStorageService.set('graph', graph.toJSON());
        };

        flink.loadFromLocalStorage = function() {
            return localStorageService.get('graph');
        };

        flink.clearGraph = function(graph) {
            graph.clear();
            localStorageService.remove('graph');
        };

        flink.renderNumberFilter = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'Number Filter'
                    }
                },
                componentType: 'numberfilter',
                data: {
                    modalController: 'NumberfilterModalCtrl',
                    modalTemplateUrl: '/app/filter/numberfilter-modal.tpl.html'
                },
                formdata: {
                    tupleIndex: 0,
                    operationType: {
                        label: '=',
                        key: '=='
                    },
                    compareValue: 0,
                    javaSourceCode: ''
                }
            });
            
            return flink.Element;
        };

        flink.renderStringFilter = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'String Filter'
                    }
                },
                componentType: 'stringfilter',
                data: {
                    modalController: 'StringfilterModalCtrl',
                    modalTemplateUrl: '/app/filter/stringfilter-modal.tpl.html'
                },
                formdata: {
                    tupleIndex: 0,
                    operationType: {
                        label: 'Equals',
                        key: 'Equals'
                    },
                    compareValue: '',
                    javaSourceCode: ''
                }
            });
            return flink.Element;
        };

        flink.renderCustomFilter = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'Custom Filter'
                    }
                },
                componentType: 'customfilter',
                data: {
                    modalController: 'CustomfilterModalCtrl',
                    modalTemplateUrl: '/app/filter/customfilter-modal.tpl.html'
                },
                formdata: {
                    javaSourceCode: templateFactory.createCustomFilterTemplate()
                }
            });
            return flink.Element;
        };

        flink.renderMap = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'Map'
                    }
                },
                componentType: 'map',
                data: {
                    modalController: 'MapModalCtrl',
                    modalTemplateUrl: '/app/map/map-modal.tpl.html'
                },
                formdata: {
                    javaSourceCode: templateFactory.createMapTemplate()
                }
            });
            return flink.Element;
        };

        flink.renderFlatMap = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'FlatMap'
                    }
                },
                componentType: 'flatmap',
                data: {
                    modalController: 'flatmapModalCtrl',
                    modalTemplateUrl: '/app/flatmap/flatmap-modal.tpl.html'
                },
                formdata: {
                    javaSourceCode: templateFactory.createFlatMapTemplate()
                }
            });
            return flink.Element;
        };

        flink.renderSum = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'Sum'
                    }
                },
                componentType: 'sum',
                data: {
                    modalController: 'sumModalCtrl',
                    modalTemplateUrl: '/app/sum/sum-modal.tpl.html'
                },
                formdata: {
                    tupleIndex: 0
                }
            });
            return flink.Element;
        };

        flink.renderJoin = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0','IN1'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'Join'
                    }
                },
                componentType: 'join',
                data: {
                    modalController: '',
                    modalTemplateUrl: ''
                }
            });
            return flink.Element;
        };

        flink.renderGroup = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'Group'
                    }
                },
                componentType: 'groupBy',
                data: {
                    modalController: 'groupModalCtrl',
                    modalTemplateUrl: '/app/group/group-modal.tpl.html'
                },
                formdata: {
                    tupleIndex: 0
                }
            });
            return flink.Element;
        };

        flink.renderReduce = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green'
                    },
                    '.label': {
                        text: 'Reduce'
                    }
                },
                componentType: 'reduce',
                data: {
                    modalController: 'ReduceModalCtrl',
                    modalTemplateUrl: '/app/reduce/reduce-modal.tpl.html'
                },
                formdata: {
                    javaSourceCode: templateFactory.createReduceTemplate()
                }
            });
            return flink.Element;
        };

        flink.renderCsvDatasource = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green',
                        class: 'body component-source'
                    },
                    '.label': {
                        text: 'CSV Datasource'
                    }
                },
                componentType: 'csvdatasource',
                data: {
                    modalController: 'CSVDatasourceModalCtrl',
                    modalTemplateUrl: '/app/datasource/csv-datasource-modal.tpl.html',
                },
                formdata: {
                    filePath: null,
                    countColumns: 2,
                    columns: []
                }

            });
            return flink.Element;
        };

        flink.renderTextDatasource = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                outPorts: ['OUT0'],
                attrs: {
                    rect: {
                        fill: 'green',
                        class: 'body component-source'
                    },
                    '.label': {
                        text: 'Text Datasource'
                    }
                },
                componentType: 'textdatasource',
                data: {
                    modalController: 'TextDatasourceModalCtrl',
                    modalTemplateUrl: '/app/datasource/text-datasource-modal.tpl.html'
                },
                formdata: {
                    filePath: null
                }
            });
            return flink.Element;
        };

        flink.renderCsvDatasink = function(posX, posY, $state) {
            flink.Element=new joint.shapes.flink.Model({
                position: {
                    x: posX,
                    y: posY
                },
                size: {
                    width: 140,
                    height: 60
                },
                inPorts: ['IN0'],
                attrs: {
                    rect: {
                        fill: 'green',
                        class: 'body component-sink'
                    },
                    '.label': {
                        text: 'CSV Datasink'
                    }
                },
                componentType: 'csvdatasink',
                data: {
                    modalController: '',
                    modalTemplateUrl: ''
                },
                formdata: {
                    filePath: null
                }
            });
            return flink.Element;
        };
        joint.shapes.flink.Model = joint.shapes.basic.Generic.extend(_.extend({}, joint.shapes.basic.PortsModelInterface, {

            markup: '<g class="rotatable"><g class="scalable"><rect class="body"/></g><text class="label"/><g class="inPorts"/><g class="outPorts"/></g>',
            portMarkup: '<g class="port port<%= id %>"><circle class="port-body"/></g>',

            defaults: joint.util.deepSupplement({

                type: 'flink.Model',
                size: {
                    width: 1,
                    height: 1
                },
                componentType: '',
                data: {},
                formdata: {},
                inPorts: [],
                outPorts: [],

                attrs: {
                    '.': {
                        magnet: false
                    },
                    '.body': {
                        width: 150,
                        height: 250,
                        stroke: '#000000'
                    },
                    '.port-body': {
                        r: 10,
                        magnet: true,
                        stroke: '#000000'
                    },
                    text: {
                        'pointer-events': 'none'
                    },
                    '.label': {
                        text: 'Model',
                        'ref-x': 0.5,
                        'ref-y': 45,
                        ref: '.body',
                        'text-anchor': 'middle',
                        fill: '#000000'
                    }
                }

            }, joint.shapes.basic.Generic.prototype.defaults),

            getPortAttrs: function(portName, index, total, selector, type) {

                var attrs = {};

                var portClass = 'port' + index;
                var portSelector = selector + '>.' + portClass;
                var portLabelSelector = portSelector + '>.port-label';
                var portBodySelector = portSelector + '>.port-body';

                attrs[portLabelSelector] = {
                    text: portName
                };
                attrs[portBodySelector] = {
                    port: {
                        id: portName || _.uniqueId(type),
                        type: type
                    }
                };
                attrs[portSelector] = {
                    ref: '.body',
                    'ref-y': (index + 0.5) * (1 / total)
                };

                if (selector === '.outPorts') {
                    attrs[portSelector]['ref-dx'] = 0;
                }

                return attrs;
            }
        }));
        

        // TODO add arrow for direction?
        flink.Link = new joint.dia.Link({
        attrs: {
                '.marker-target': { d: 'M 10 0 L 0 5 L 10 10 z' }}
    });

        joint.shapes.flink.ModelView = joint.dia.ElementView.extend(joint.shapes.basic.PortsViewInterface);

        return flink;
    }

})();
