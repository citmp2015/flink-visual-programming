(function() {

    'use strict';

    angular
        .module('app')
        .factory('parsing', parsing);

    /*@ngInject*/
    function parsing(jsonBuilder) {
        var parse = {};

        parse.parseTypeParameters = function(code) {
            var regex = /class\s+[\w]+\s+implements\s+[\w]+\s*<([\w\s<>,]+)>\s*\{/igm;
            // this gives us whats inside the diamond of the FlatMapFunction
            // FlatMapFunction<Tuple3<String, Integer, Float>, Tuple2<Integer, Float>>
            // -> Tuple3<String, Integer, Float>, Tuple2<Integer, Float>
            // so now we need to find the correct comma
            var matched = regex.exec(code)[1];
            var splitIndex = this.findCommaNotInDiamond(matched);

            var inType = matched.substring(0, splitIndex).trim();
            var outType = matched.substring(splitIndex + 1).trim(); // + 1, so we skip the comma
            return {inType: inType, outType: outType};
        };

        parse.findCommaNotInDiamond = function(string) {
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
        };

        parse.parseClassName = function(code) {
            var regex = /class\s+([\w]+)\s+implements\s+[\w]+/igm;
            var result = regex.exec(code);
            return result[1];
        };

        parse.replaceClassName = function(code) {
            var uuid = jsonBuilder.fixId(joint.util.uuid());
            var regex = /(class\s+)([\w]+)(\s+implements\s+[\w]+)/igm;
            return {code: code.replace(regex, '$1' + uuid + '$3'),
                    functionName: uuid };
        };

        return parse;
    }

})();
