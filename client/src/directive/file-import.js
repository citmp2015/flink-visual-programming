(function() {

    'use strict';

    angular
        .module('app')
        .directive('fileImport', fileImport);

    /*@ngInject*/
    function fileImport($window, $log) {

        return {
            restrict: 'A',
            require: 'ngModel',
            link: function(scope, el, attr, ctrl) {

                var fileReader = new $window.FileReader();

                fileReader.onload = function() {
                    ctrl.$setViewValue(fileReader.result);
                    if ('onFileLoaded' in attr) {
                        scope.$eval(attr['onFileLoaded']);
                    }
                };

                fileReader.onerror = function() {
                    if ('onFileError' in attr) {
                        scope.$eval(attr['onFileError'], {
                            '$error': fileReader.error
                        });
                    }
                };

                el.bind('change', function(e) {
                    var file = e.target.files[0];
                    fileReader.readAsText(file);
                });

            }
        };
    }

})();
