(function() {

    'use strict';

    angular
        .module('app.filter')
        .controller('NumberfilterModalCtrl', NumberfilterModalCtrl)
        .controller('StringfilterModalCtrl', StringfilterModalCtrl)
        .controller('CustomfilterModalCtrl', CustomfilterModalCtrl);

    /*@ngInject*/
    function NumberfilterModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, templateFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.operationTypes = [{
            label: '=',
            key: '=='
        }, {
            label: '>',
            key: '>'
        }, {
            label: '≥',
            key: '>='
        }, {
            label: '<',
            key: '<'
        }, {
            label: '≤',
            key: '<='
        }];

        $scope.numberfilter = {
            tupleIndex: cell.attributes.formdata.tupleIndex,
            operationType: cell.attributes.formdata.operationType,
            compareValue: cell.attributes.formdata.compareValue,
			javaSourceCode: cell.attributes.formdata.javaSourceCode
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.formdata.tupleIndex = $scope.numberfilter.tupleIndex;
            cell.attributes.formdata.operationType = $scope.numberfilter.operationType;
            cell.attributes.formdata.compareValue = $scope.numberfilter.compareValue;
			cell.attributes.formdata.javaSourceCode = templateFactory.createNumberFilterTemplate($scope.numberfilter.operationType.key, $scope.numberfilter.compareValue);
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

    /*@ngInject*/
    function StringfilterModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, templateFactory, $log) {

        var cell = $rootScope.graph.getCell($stateParams.id);

        $scope.operationTypes = [{
            label: 'Equals',
            key: 'Equals'
        }, {
            label: 'Not Equals',
            key: 'NotEquals'
        }, {
            label: 'Contains',
            key: 'Contains'
        }, {
            label: 'Not Contains',
            key: 'NotContains'
        }, {
            label: 'Starts With',
            key: 'StartsWith'
        }, {
            label: 'Ends With',
            key: 'EndsWith'
        }, {
            label: 'Matches (RegEx Pattern)',
            key: 'Matches'
        }];

        $scope.stringfilter = {
            tupleIndex: cell.attributes.formdata.tupleIndex,
            operationType: cell.attributes.formdata.operationType,
            compareValue: cell.attributes.formdata.compareValue,
			javaSourceCode: cell.attributes.formdata.javaSourceCode
        };

        $scope.save = save;
        $scope.cancel = cancel;

        function save() {
            cell.attributes.formdata.tupleIndex = $scope.stringfilter.tupleIndex;
            cell.attributes.formdata.operationType = $scope.stringfilter.operationType;
            cell.attributes.formdata.compareValue = $scope.stringfilter.compareValue;
			cell.attributes.formdata.javaSourceCode = templateFactory.createStringFilterTemplate($scope.stringfilter.operationType.key, $scope.stringfilter.compareValue);
            graphFactory.saveToLocalStorage($rootScope.graph);
            $uibModalInstance.close();
        }

        function cancel() {
            $uibModalInstance.close();
        }

    }

    /*@ngInject*/
    function CustomfilterModalCtrl($scope, $rootScope, $uibModalInstance, $stateParams, $timeout, graphFactory, $log, parsing, verification) {

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
