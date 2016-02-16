// Karma configuration
// http://karma-runner.github.io/0.12/config/configuration-file.html
// Generated on 2015-02-03 using
// generator-karma 0.9.0

module.exports = function(config) {
    'use strict';

    config.set({
        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,

        // base path, that will be used to resolve files and exclude
        basePath: '../',

        // testing framework to use (jasmine/mocha/qunit/...)
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        files: [

            // start bower dependencies
            'bower_components/jquery/jquery.js',
            'bower_components/lodash/lodash.js',
            'bower_components/backbone/backbone.js',
            'bower_components/graphlib/dist/graphlib.core.js',
            'bower_components/dagre/dist/dagre.core.js',
            'bower_components/dagre/dist/dagre.core.min.js',
            'bower_components/jointjs/dist/joint.js',
            'bower_components/components-bootstrap/js/bootstrap.js',
            'bower_components/angular/angular.js',
            'bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
            'bower_components/angular-ui-router/release/angular-ui-router.js',
            'bower_components/moment/moment.js',
            'bower_components/angular-moment/angular-moment.js',
            'bower_components/angular-touch/angular-touch.js',
            'bower_components/angular-animate/angular-animate.js',
            'bower_components/ngDraggable/ngDraggable.js',
            'bower_components/angular-local-storage/dist/angular-local-storage.js',
            'bower_components/codemirror/lib/codemirror.js',
            'bower_components/codemirror/mode/clike/clike.js',
            'bower_components/angular-ui-codemirror/ui-codemirror.js',
            'bower_components/angular-sanitize/angular-sanitize.js',
            'bower_components/nanoscroller/bin/javascripts/jquery.nanoscroller.js',
            // end bower dependencies


            // start bower dev dependencies
            'bower_components/angular-mocks/angular-mocks.js',
            // end bower dev dependencies


            // start app files
            'src/app/app.module.js',
            'src/app/app.config.js',
            'src/app/app.controller.js',
            'src/app/app.routes.js',
            'src/app/app.factory.js',
            'src/app/app.templates.js',
            'src/app/app.json.js',

            'src/app/menu/menu.module.js',
            'src/app/menu/menu.controller.js',
            'src/app/menu/menu-sidebar.controller.js',
            'src/app/menu/menu-navbar-top.controller.js',

            'src/app/console/console.module.js',
            'src/app/console/console.controller.js',

            'src/app/datasource/datasource.module.js',
            'src/app/datasource/datasource-modal.controller.js',

            'src/app/filter/filter.module.js',
            'src/app/filter/filter-modal.controller.js',

            'src/app/group/group.module.js',
            'src/app/group/group-modal.controller.js',

            'src/app/sum/sum.module.js',
            'src/app/sum/sum-modal.controller.js',

            'src/app/flatmap/flatmap.module.js',
            'src/app/flatmap/flatmap-modal.controller.js',

            'src/app/map/map.module.js',
            'src/app/map/map-modal.controller.js',

            'src/app/reduce/reduce.module.js',
            'src/app/reduce/reduce-modal.controller.js',

            'src/app/generalsettings/generalsettings.module.js',
            'src/app/generalsettings/generalsettings-modal.controller.js',

            'src/app/loadingmodal/loadingmodal.module.js',
            'src/app/loadingmodal/loadingmodal.controller.js',

            'src/directive/joint-diagram.js',
            'src/directive/file-import.js',

            'src/filter/menu-search.js',
            // end app files


            // start test files
            'test/mock/{,*/}*.js',
            'test/spec/unit/{,*/}*.js'
            //end test files

        ],

        // list of files / patterns to exclude
        exclude: [],

        // web server port
        port: 8080,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: [
            'PhantomJS'
        ],

        // Which plugins to enable
        plugins: [
            'karma-phantomjs-launcher',
            'karma-chrome-launcher',
            'karma-jasmine'
        ],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: true,

        colors: true,

        // level of logging
        // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
        logLevel: config.LOG_INFO,

        // Uncomment the following lines if you are using grunt's server to run the tests
        // proxies: {
        //   '/': 'http://localhost:9000/'
        // },
        // URL root prevent conflicts with the site root
        // urlRoot: '_karma_'
    });
};
