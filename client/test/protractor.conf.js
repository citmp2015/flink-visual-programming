exports.config = {

    multiCapabilities: [{
        'browserName': 'phantomjs',
        'phantomjs.binary.path': require('phantomjs-prebuilt').path,
    }],

    specs: ['spec/e2e/*.js'],

    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 30000
    }

};
