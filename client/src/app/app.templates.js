(function() {

    'use strict';

    angular
        .module('app')
        .factory('templateFactory', TemplateFactory);



    /*@ngInject*/
    function TemplateFactory() {

        var template = {};

        template.createCSVDatasourceTemplate = function(columns, path) {
            var types = columns[0].type.label;
            for (var index = 1; index < columns.length; index++) {
                types = types + ',' + columns[index].type.label;
            }
            return 'DataSet<' + types + '> text = env.readTextFile("' + path + '");';
        };

        template.createTextDatasourceTemplate = function(path) {
            return 'DataSet<String> text = env.readTextFile("' + path + '");';
        };

        template.createNumberFilterTemplate = function(operator, value) {
            return 'data.filter(new FilterFunction<Integer>() {\n  public boolean filter(Integer value) {\n   return value ${operator} ${value};\n }\n});';
        };

        template.createFlatMapTemplate = function() {
            return 'public class Tokenizer implements FlatMapFunction<String, String> {\n  @Override\n  public void flatMap(String value, Collector<String> out) {\n    for (String token : value.split("\\W")) {\n      out.collect(token);\n    }\n  }\n}';
        };

        return template;
    }

})();
