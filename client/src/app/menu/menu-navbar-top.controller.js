(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuNavbarTopCtrl', MenuNavbarTopCtrl);

    /*@ngInject*/
    function MenuNavbarTopCtrl($scope, $rootScope, graphFactory) {

        $scope.clearGraph = clearGraph;

        function clearGraph() {
            graphFactory.clearGraph($rootScope.graph);
        }

    }

})();
