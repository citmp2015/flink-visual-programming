(function() {

    'use strict';

    angular
        .module('app')
        .factory('verification', verification);

    /*@ngInject*/
    function verification() {
        var verify = {};

        verify.verifyClassNames = function(graph) {
            var classes = JSON.stringify(graph).match(/class\s+([\w]+)\s+implements\s+[\w]+/g);
            
            if(classes===null){
                return true;
            }
            
            var classNames = [];
            for (var i = 0; i < classes.length; i++) {
                var regex = /class\s+([\w]+)\s+implements\s+[\w]+/igm;
                var result = regex.exec(classes[i]);
                classNames[i] = result[1];
            }

             // test for duplicates
            for (var i = 0; i < classNames.length; i++) {
                for (var j = i + 1; j < classNames.length; j++) {
                    if (classNames[i] === classNames[j]) {
                       console.log('Duplicate Class Name Found! Class "'+classNames[i]+'" must be unique!');
                       return false;
                    }
                }
            }
            
            return true;
        };

        return verify;
    }

})();
