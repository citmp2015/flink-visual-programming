SVGElement.prototype.getTransformToElement = SVGElement.prototype.getTransformToElement || function(toElement) { //jshint ignore:line
        return toElement.getScreenCTM().inverse().multiply(this.getScreenCTM());
};
