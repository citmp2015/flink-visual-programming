(function() {
    'use strict';

    angular
        .module('app')
        .filter('menuSearchFilter', ["$filter", menuSearchFilter]);


    /*@ngInject*/
    function menuSearchFilter($filter) {

        function getValue(element, searchString) {
            if (typeof element === 'undefined' || typeof searchString === 'undefined') {
                return false;
            }

            if (element.hasOwnProperty('subItems') && element['subItems'].length > 0) {
                var subItemIsValid = false;
                element['subItems'].forEach(function(singleEntry) {
                    if (getValue(singleEntry, searchString)) {
                        subItemIsValid = true;
                    }
                });
                return subItemIsValid;
            }

            return element.label.toLowerCase().search(searchString.toLowerCase()) >= 0;
        }

        return function (array, searchObject, target) {
            if (typeof searchObject === 'undefined') {
                return array;
            }

            if (!searchObject.hasOwnProperty('label') || searchObject['label'].length <= 1) {
                return array;
            }

            return $filter('filter')(array, function(item){
                return getValue(item, searchObject['label']);
            });
        }

    }

})();
