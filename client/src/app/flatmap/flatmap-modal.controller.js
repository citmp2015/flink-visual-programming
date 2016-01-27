(function() {

    'use strict';

    angular
        .module('app.flatmap')
        .controller('flatmapModalCtrl', FlatmapModalCtrl);

    /*@ngInject*/
    function FlatmapModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log) {

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
            cell.attributes.formdata.functionName = getClassName(cell.attributes.formdata.javaSourceCode);
            var types = getTypes(cell.attributes.formdata.javaSourceCode);
            cell.attributes.formdata.input_type = types.inType;
            cell.attributes.formdata.output_type = types.outType;
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function getTypes(code) {
            var regex = /public\s+class\s+[\w]+\s+implements\s+FlatMapFunction\s*<([\w\s<>,]+)>\s*\{/igm;
            // this gives us whats inside the diamond of the FlatMapFunction
            // FlatMapFunction<Tuple3<String, Integer, Float>, Tuple2<Integer, Float>>
            // -> Tuple3<String, Integer, Float>, Tuple2<Integer, Float>
            // so now we need to find the correct comma
            var matched = regex.exec(code)[1];
            var splitIndex = findCommaNotInDiamond(matched);

            var inType = matched.substring(0, splitIndex).trim();
            var outType = matched.substring(splitIndex + 1).trim(); // + 1, so we skip the comma
            return {inType: inType, outType: outType};
        }

        function findCommaNotInDiamond(string) {
            var openCount = 0;
            for (var i = 0; i < string.length; i++) {
                switch (string[i]) {
                    case '<':
                        openCount++;
                        break;
                    case '>':
                        openCount--;
                        break;
                    case ',':
                        if (openCount === 0) {
                            return i;
                        }
                        break;
                    default:
                        // ignore other chars
                }
            }
        }

        function getClassName(code) {
            var regex = /public\s+class\s+([\w]+)\s+implements/igm;
            return regex.exec(code)[1];
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

})();
