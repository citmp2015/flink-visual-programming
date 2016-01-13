exports.config = {

    multiCapabilities: [{
        'browserName': 'phantomjs',
        'phantomjs.binary.path': require('phantomjs').path,
    }],

    specs: ['spec/e2e/*.js'],

    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 30000
    }

};
