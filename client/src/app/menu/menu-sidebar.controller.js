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
            openForFilter: false,
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
            openForFilter: false,
            dragData: {
                type: 'join'
            },
            subItems: []
        }, {
            label: 'Filter',
            faIconClass: 'fa-filter',
            open: false,
            openForFilter: false,
            subItems: [{
                label: 'Number Filter',
                faIconClass: 'fa-filter',
                dragData: {
                    type: 'numberFilter'
                }
            }, {
                label: 'String Filter',
                faIconClass: 'fa-file-text',
                dragData: {
                    type: 'stringFilter'
                }
            }, {
                label: 'Custom Filter',
                faIconClass: 'fa-asterisk',
                dragData: {
                    type: 'customFilter'
                }
            }]
        }, {
            label: 'Sum',
            faIconClass: 'fa-plus',
            open: false,
            openForFilter: false,
            dragData: {
                type: 'sum'
            },
            subItems: []
        }, {
            label: 'Group',
            faIconClass: 'fa-folder',
            open: false,
            openForFilter: false,
            dragData: {
                type: 'group'
            },
            subItems: []
        }, {
            label: 'Map',
            faIconClass: 'fa-clone',
            open: false,
            openForFilter: false,
            dragData: {
                type: 'map'
            },
            subItems: []
        }, {
            label: 'FlatMap',
            faIconClass: 'fa-sitemap',
            open: false,
            openForFilter: false,
            dragData: {
                type: 'flatmap'
            },
            subItems: []
        }, {
            label: 'Reduce',
            faIconClass: 'fa-code-fork',
            open: false,
            openForFilter: false,
            dragData: {
                type: 'reduce'
            },
            subItems: []
        }, {
            label: 'Sinks',
            faIconClass: 'fa-sign-out',
            open: false,
            openForFilter: false,
            subItems: [{
                label: 'CSV Datasink',
                faIconClass: 'fa-table',
                dragData: {
                    type: 'csvDatasink'
                }
            }]
        }];

        $scope.$watch('search.label', function(searchLabel) {
            var openForFilter = false,
                menuItemLength = $scope.menuItems.length;
            $scope.showClearSearchButton = false;

            if (typeof searchLabel !== 'undefined' && searchLabel.length > 1) {
                $scope.showClearSearchButton = true;
                openForFilter = true;
            }

            for (var index = 0; index < menuItemLength; index++) {
                $scope.menuItems[index].openForFilter = openForFilter;
            }
        });

        $scope.clearSearch = function() {
            $scope.search.label = '';
        };
    }

})();
