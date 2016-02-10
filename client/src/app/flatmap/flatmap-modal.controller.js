(function() {

    'use strict';

    angular
        .module('app.flatmap')
        .controller('flatmapModalCtrl', FlatmapModalCtrl);

    /*@ngInject*/
    function FlatmapModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log, parsing) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.editor = cell.attributes.formdata.javaSourceCode;

        $scope.save = save;
        $scope.cancel = cancel;

        // Refresh to make source code visible in modal
        $scope.refreshEditor = true;
        $timeout(function () {
            $scope.refreshEditor = false;
        }, 100);

        function save() {
            var classAndName = parsing.replaceClassName($scope.editor);
            cell.attributes.formdata.javaSourceCode = classAndName.code;
            cell.attributes.formdata.functionName = classAndName.functionName;
            var types = parsing.parseTypeParameters(classAndName.code);
            /* jshint ignore:start */
            cell.attributes.formdata.input_type = types.inType;
            cell.attributes.formdata.output_type = types.outType;
            /* jshint ignore:end */
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
