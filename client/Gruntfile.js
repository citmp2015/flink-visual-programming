module.exports = function(grunt) {

    'use strict';

    require('load-grunt-tasks')(grunt);

    var appConfig = {
        app: require('./bower.json').appPath || './src',
        dist: '../src/main/webapp',
        tmp: './.tmp'
    };


    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),
        flinkVisual: appConfig,

        watch: {
            bower: {
                files: ['bower.json'],
                tasks: ['bower', 'wiredep']
            },
            json: {
                files: ['<%= flinkVisual.app %>/app/{,*/}*.json'],
                options: {
                    livereload: '<%= connect.options.livereload %>'
                },
            },
            js: {
                files: ['<%= flinkVisual.app %>/app/{,*/}*.js'],
                tasks: ['jshint'],
                options: {
                    livereload: '<%= connect.options.livereload %>',
                    spawn: false
                },
            },
            styles: {
                files: ['<%= flinkVisual.app %>/styles/{,*/}*.css', '<%= flinkVisual.app %>/app/{,*/}*.css'],
                tasks: ['newer:copy:styles', 'postcss:serve'],
                options: {
                    livereload: '<%= connect.options.livereload %>',
                    spawn: false
                }
            },
            images: {
                options: {
                    livereload: '<%= connect.options.livereload %>'
                },
                files: [
                    '<%= flinkVisual.app %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
                    '<%= flinkVisual.app %>/app/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
                ]
            },
            html: {
                options: {
                    livereload: '<%= connect.options.livereload %>'
                },
                files: [
                    '<%= flinkVisual.app %>/**/*.html',
                ]
            },
            gruntfile: {
                files: [
                    './Gruntfile.js'
                ],
                tasks: ['jshint']
            },
            npm: {
                files: [
                    './package.json'
                ],
                tasks: []
            }
        },

        jshint: {
            options: {
                jshintrc: '.jshintrc',
                reporter: require('jshint-stylish')
            },
            all: {
                src: [
                    'Gruntfile.js',
                    '<%= flinkVisual.app %>/app/**/*.js'
                ]
            }
        },

        protractor: {
            options: {
                configFile: 'test/protractor.conf.js',
            },
            test: {
                options: {
                    args: {
                        baseUrl: 'http://localhost:9001',
                    }
                }
            }
        },

        connect: {
            options: {
                livereload: 35729,
                hostname: 'localhost'
            },
            livereload: {
                options: {
                    port: 9000,
                    open: true,
                    middleware: function(connect) {
                        var serveStatic = require('serve-static');
                        return [
                            serveStatic('<%= flinkVisual.tmp %>'),
                            connect().use(
                                '/bower_components',
                                serveStatic('./bower_components')
                            ),
                            connect().use(
                                '/node_modules',
                                serveStatic('./node_modules')
                            ),
                            serveStatic(appConfig.app)
                        ];
                    }
                }
            },
            test: {
                options: {
                    port: 9001,
                    livereload: false,
                    middleware: function(connect) {
                        var serveStatic = require('serve-static');
                        return [
                            serveStatic('<%= flinkVisual.tmp %>'),
                            connect().use(
                                '/bower_components',
                                serveStatic('./bower_components')
                            ),
                            connect().use(
                                '/node_modules',
                                serveStatic('./node_modules')
                            ),
                            serveStatic(appConfig.app)
                        ];
                    }
                }
            }
        },

        bower: {
            install: {
                options: {
                    targetDir: './bower_components'
                }
            }
        },

        // Automatically inject Bower components into the app
        wiredep: {
            app: {
                src: ['<%= flinkVisual.app %>/index.html'],
                exclude: [
                    'bower_components/underscore/underscore.js'
                ]
            }
        },

        // Renames files for browser caching purposes
        filerev: {
            options: {
                algorithm: 'md5',
                length: 8
            },
            images: {
                src: [
                    '<%= flinkVisual.dist %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
                    '<%= flinkVisual.dist %>/app/**/*.{png,jpg,jpeg,gif,webp,svg}'
                ]
            },
            js: {
                src: '<%= flinkVisual.dist %>/scripts/{,*/}*.js'
            },
            css: {
                src: '<%= flinkVisual.dist %>/styles/*.css'
            }
        },

        // Reads HTML for usemin blocks to enable smart builds that automatically
        // concat, minify and revision files. Creates configurations in memory so
        // additional tasks can operate on them
        useminPrepare: {
            html: '<%= flinkVisual.app %>/index.html',
            options: {
                dest: '<%= flinkVisual.dist %>',
            }
        },

        // Performs rewrites based on filerev and the useminPrepare configuration
        usemin: {
            html: [
                '<%= flinkVisual.dist %>/{,*/}*.html'
            ],
            css: [
                '<%= flinkVisual.dist %>/styles/{,*/}*.css',
                '<%= flinkVisual.dist %>/app/**/*.css'
            ],
            options: {
                assetsDirs: [
                    '<%= flinkVisual.dist %>',
                    '<%= flinkVisual.dist %>/images'
                ]
            }
        },

        uglify: {
            dist: {
                files: {
                    '<%= flinkVisual.dist %>/common/scripts.js': [
                        '<%= flinkVisual.dist %>/common/scripts.js'
                    ]
                }
            }
        },

        ngAnnotate: {
            dist: {
                files: [{
                    expand: true,
                    cwd: '<%= flinkVisual.tmp %>/concat/scripts',
                    src: ['*.js', '!oldieshim.js'],
                    dest: '<%= flinkVisual.tmp %>/concat/scripts'
                }]
            }
        },

        htmlmin: {
            dist: {
                options: {
                    collapseWhitespace: true,
                    conservativeCollapse: true,
                    collapseBooleanAttributes: true,
                    removeCommentsFromCDATA: true,
                    removeOptionalTags: true
                },
                files: [{
                    expand: true,
                    cwd: '<%= flinkVisual.dist %>',
                    src: ['*.html', 'views/{,*/}*.html', 'app/{,*/}*.html'],
                    dest: '<%= flinkVisual.dist %>'
                }]
            }
        },


        imagemin: {
            dist: {
                options: {
                    optimizationLevel: 3,
                    svgoPlugins: [{
                        removeViewBox: false,
                        removeUselessStrokeAndFill: true,
                        removeEmptyAttrs: true
                    }]
                },
                files: [{
                    expand: true,
                    cwd: '',
                    src: [
                        '<%= flinkVisual.dist %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
                        '<%= flinkVisual.dist %>/app/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
                    ],
                    dest: ''
                }]
            }
        },

        postcss: {
            dist: {
                options: {
                    map: false,
                    processors: [
                        require('pixrem')(),
                        require('postcss-color-rgba-fallback')(),
                        require('postcss-gradientfixer')(),
                        require('postcss-flexboxfixer')(),
                        require('autoprefixer')({
                            browsers: ['last 3 versions']
                        }),
                        require('postcss-discard-comments')(),
                        require('postcss-colormin')(),
                        require('postcss-convert-values')(),
                        require('postcss-ordered-values')(),
                        require('postcss-minify-font-weight')(),
                        require('postcss-merge-longhand')(),
                        require('postcss-merge-rules')(),
                        require('postcss-discard-empty')(),
                        require('perfectionist')({
                            format: 'compressed'
                        })
                    ]
                },
                src: [
                    '<%= flinkVisual.tmp %>/concat/styles/*.css',
                    '!<%= flinkVisual.tmp %>/concat/styles/vendor.css'
                ]
            },
            serve: {
                options: {
                    map: false,
                    processors: [
                        require('pixrem')(),
                        require('postcss-color-rgba-fallback')(),
                        require('postcss-gradientfixer')(),
                        require('postcss-flexboxfixer')(),
                        require('autoprefixer')({
                            browsers: ['last 3 versions']
                        })
                    ]
                },
                src: [
                    '<%= flinkVisual.app %>/styles/{,*/}*.css',
                    '<%= flinkVisual.app %>/app/{,*/}*.css'
                ]
            }
        },


        // Empties folders to start fresh
        clean: {
            dist: {
                options: {
                    force: true
                },
                files: [{
                    dot: true,
                    src: [
                        '<%= flinkVisual.tmp %>',
                        '<%= flinkVisual.dist %>/{,*/}*',
                        '!<%= flinkVisual.dist %>/WEB-INF/**',
                        '!<%= flinkVisual.dist %>/.gitignore'
                    ]
                }]
            },
            server: {
                files: [{
                    dot: true,
                    src: [
                        '<%= flinkVisual.tmp %>',
                        './docular_generated/*'
                    ]
                }]
            }
        },

        // Copies remaining files to places other tasks can use
        copy: {
            dist: {
                files: [{
                    expand: true,
                    dot: true,
                    cwd: '<%= flinkVisual.app %>',
                    dest: '<%= flinkVisual.dist %>',
                    src: [
                        '*.{ico,png,txt}',
                        '.htaccess',
                        '*.html',
                        'app/{,*/}*.html',
                        'view/{,*/}*.html',
                        'fonts/{,*/}*.*',
                        '{,*/}*.json'
                    ]
                }, {
                    expand: true,
                    cwd: '<%= flinkVisual.tmp %>/images',
                    dest: '<%= flinkVisual.dist %>/images',
                    src: ['generated/*']
                }, {
                    expand: true,
                    cwd: 'bower_components/components-font-awesome/fonts',
                    dest: '<%= flinkVisual.dist %>/fonts',
                    src: ['*']
                }, {
                    expand: true,
                    cwd: 'bower_components/components-bootstrap/fonts',
                    dest: '<%= flinkVisual.dist %>/fonts',
                    src: ['*']
                }, {
                    expand: true,
                    cwd: '<%= flinkVisual.app %>/dummydata',
                    dest: '<%= flinkVisual.dist %>/dummydata',
                    src: ['*']
                }]
            },
            styles: {
                expand: true,
                cwd: '<%= flinkVisual.app %>/styles',
                dest: '<%= flinkVisual.tmp %>/styles/',
                src: '{,*/}*.css'
            }
        }

    });

    grunt.event.on('watch', function(action, filepath) {
        grunt.config('jshint.all.src', filepath);
    });

    grunt.registerTask('serve', [
        'clean:server',
        'bower',
        'wiredep',
        'copy:styles',
        'postcss:serve',
        'connect:livereload',
        'watch'
    ]);

    grunt.registerTask('test', [
        'clean:server',
        'bower',
        'wiredep',
        'copy:styles',
        'postcss:serve',
        'connect:test',
        'jshint:all',
        'protractor:test'
    ]);

    grunt.registerTask('build', [
        'clean:dist',
        'bower',
        'wiredep',
        'useminPrepare',
        'concat:generated',
        'ngAnnotate',
        'postcss:dist',
        'cssmin:generated',
        'uglify:generated',
        'copy:dist',
        'imagemin:dist',
        'filerev',
        'usemin',
        'htmlmin'
    ]);

};
