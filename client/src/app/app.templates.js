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
		
		return template;
    }

})();