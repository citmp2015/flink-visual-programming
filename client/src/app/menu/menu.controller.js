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
            label: 'Map',
            faIconClass: 'fa-edit',
            open: false,
            subItems: []
        }, {
            label: 'Reduce',
            faIconClass: 'fa-edit',
            open: false,
            subItems: []
        }];

    }

})();
