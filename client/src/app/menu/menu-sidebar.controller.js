(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuSidebarCtrl', MenuSidebarCtrl);

    /*@ngInject*/
    function MenuSidebarCtrl($scope, $rootScope) {

        $scope.menuItems = [{
            label: 'Datasources',
            faIconClass: 'fa-table',
            open: false,
            subItems: [{
                label: 'CSV Datasource',
                dragData: {
                    type: 'csvDatasource'
                }
            }, {
                label: 'Text Datasource',
                dragData: {
                    type: 'textDatasource'
                }
            }]
        }, {
            label: 'Join',
            faIconClass: 'fa-link',
            open: false,
            dragData: {
                type: 'join'
            },
            subItems: []
        }, {
            label: 'Filter',
            faIconClass: 'fa-filter',
            open: false,
            subItems: [{
                label: 'Number filter',
                dragData: {
                    type: 'numberFilter'
                }
            }, {
                label: 'String filter',
                dragData: {
                    type: 'stringFilter'
                }
            }]
        }, {
            label: 'Sum',
            faIconClass: 'fa-plus',
            open: false,
            dragData: {
                type: 'sum'
            },
            subItems: []
        }, {
            label: 'Group',
            faIconClass: 'fa-list',
            open: false,
            dragData: {
                type: 'group'
            },
            subItems: []
        }, {
            label: 'Map',
            faIconClass: 'fa-edit',
            open: false,
            dragData: {
                type: 'map'
            },
            subItems: []
        }, {
            label: 'FlatMap',
            faIconClass: 'fa-edit',
            open: false,
            dragData: {
                type: 'flatmap'
            },
            subItems: []
        }, {
            label: 'Reduce',
            faIconClass: 'fa-edit',
            open: false,
            dragData: {
                type: 'reduce'
            },
            subItems: []
        }, {
            label: 'Sinks',
            faIconClass: 'fa-table',
            open: false,
            subItems: [{
                label: 'CSV Datasink',
                dragData: {
                    type: 'csvDatasink'
                }
            }]
        }];

    }

})();
