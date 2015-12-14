(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuCtrl', MenuCtrl);

    /*@ngInject*/
    function MenuCtrl($scope, $rootScope) {

        $scope.menuItems = [{
            label: 'Datasource',
            faIconClass: 'fa-table',
            open: false,
            subItems: [{
                label: 'CSV Datasoucrce',
                dragData: {
                    type: 'csvDatasource'
                }
            }]
        },{
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
            label: 'Reduce',
            faIconClass: 'fa-edit',
            open: false,
            subItems: []
        }];

    }

})();
