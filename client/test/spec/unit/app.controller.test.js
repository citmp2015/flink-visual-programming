'use strict';

describe('AppCtrl', function() {

    beforeEach(module('app'));

    var $controller;

    beforeEach(inject(function(_$controller_) {
        $controller = _$controller_;
    }));

    describe('$rootScope.graph', function() {
        it('should be defined', function() {
            var $scope = {}, $rootScope = {};
            var controller = $controller('AppCtrl', {
                $scope: $scope,
                $rootScope: $rootScope
            });
            expect($rootScope.graph).toBeDefined();
        });
    });

});
