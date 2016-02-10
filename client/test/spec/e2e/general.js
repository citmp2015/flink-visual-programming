'use strict';

var appDomain = 'http://localhost:9001';

describe('Flink Visual App', function() {

    it('should have a title', function() {
        browser.get(appDomain);
        expect(browser.getTitle()).not.toBe(undefined);
    });

});
