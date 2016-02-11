(function() {

    'use strict';

    angular
        .module('app.reduce')
        .controller('ReduceModalCtrl', ReduceModalCtrl);

    /*@ngInject*/
    function ReduceModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log, parsing, verification) {

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
            cell.attributes.formdata.javaSourceCode = $scope.editor;
            cell.attributes.formdata.functionName = parsing.parseClassName($scope.editor);
            var types = parsing.parseTypeParameters($scope.editor);
            /* jshint ignore:start */
            cell.attributes.formdata.input_type = types.inType;
            cell.attributes.formdata.output_type = types.outType;
            /* jshint ignore:end */
            if(!verification.verifyClassNames($rootScope.graph)){
                return;
            }
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
