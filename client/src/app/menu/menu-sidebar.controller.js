(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuSidebarCtrl', MenuSidebarCtrl);

    /*@ngInject*/
    function MenuSidebarCtrl($scope, $rootScope) {

        $scope.menuItems = [{
            label: 'Datasources',
            faIconClass: 'fa-database',
            open: false,
            subItems: [{
                label: 'CSV Datasource',
                faIconClass: 'fa-table',
                dragData: {
                    type: 'csvDatasource'
                }
            }, {
                label: 'Text Datasource',
                faIconClass: 'fa-file-text',
                dragData: {
                    type: 'textDatasource'
                }
            }]
        }, {
            label: 'Join',
            faIconClass: 'fa-share-alt fa-rotate-270',
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
                label: 'Number Filter',
                dragData: {
                    type: 'numberFilter'
                }
            }, {
                label: 'String Filter',
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
            faIconClass: 'fa-folder',
            open: false,
            dragData: {
                type: 'group'
            },
            subItems: []
        }, {
            label: 'Map',
            faIconClass: 'fa-clone',
            open: false,
            dragData: {
                type: 'map'
            },
            subItems: []
        }, {
            label: 'FlatMap',
            faIconClass: 'fa-sitemap',
            open: false,
            dragData: {
                type: 'flatmap'
            },
            subItems: []
        }, {
            label: 'Reduce',
            faIconClass: 'fa-code-fork',
            open: false,
            dragData: {
                type: 'reduce'
            },
            subItems: []
        }, {
            label: 'Sinks',
            faIconClass: 'fa-sign-out',
            open: false,
            subItems: [{
                label: 'CSV Datasink',
                faIconClass: 'fa-table',
                dragData: {
                    type: 'csvDatasink'
                }
            }]
        }];

    }

})();
