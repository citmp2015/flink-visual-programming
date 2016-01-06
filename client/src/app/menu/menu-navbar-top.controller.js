(function() {

    'use strict';

    angular
        .module('app.menu')
        .controller('MenuNavbarTopCtrl', MenuNavbarTopCtrl);

    /*@ngInject*/
    function MenuNavbarTopCtrl($scope, $rootScope, graphFactory) {

        $scope.clearGraph = clearGraph;
        $scope.dumpGraph = dumpGraph;

        function clearGraph() {
            graphFactory.clearGraph($rootScope.graph);
        }

        function dumpGraph() {
            console.log(JSON.stringify($rootScope.graph.toJSON()));
        }

    }

})();
