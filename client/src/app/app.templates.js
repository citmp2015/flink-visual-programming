(function() {

    'use strict';
		
	angular
        .module('app')
        .factory('templateFactory', TemplateFactory);


	
	    /*@ngInject*/
    function TemplateFactory() {
		
		var template = {};
		
		template.createNumberFilterTemplate = function(operator, value) {				
			return `data.filter(new FilterFunction<Integer>() {\n  public boolean filter(Integer value) {\n   return value ${operator} ${value};\n }\n});`;
		};
		
		template.createFlatMapTemplate = function() {				
			return 'public class Tokenizer implements FlatMapFunction<String, String> {\n  @Override\n  public void flatMap(String value, Collector<String> out) {\n    for (String token : value.split("\\W")) {\n      out.collect(token);\n    }\n  }\n}';
		};
		
		return template;
    }

})();