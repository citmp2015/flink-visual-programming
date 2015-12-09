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
            subItems: []
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
            }, {
                label: 'String filter'
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
