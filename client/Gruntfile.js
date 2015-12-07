module.exports = function (grunt) {

    'use strict';

    require('load-grunt-tasks')(grunt);

    var appConfig = {
        app: require('./bower.json').appPath || './src',
        dist: './dist',
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

        connect: {
            options: {
                port: 9000,
                hostname: 'localhost',
                livereload: 35729
            },
            livereload: {
                options: {
                    open: true,
                    middleware: function (connect) {
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
                    '../src/main/webapp/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
                    '../src/main/webapp/app/**/*.{png,jpg,jpeg,gif,webp,svg}'
                ]
            },
            js: {
                src: '../src/main/webapp/scripts/{,*/}*.js'
            },
            css: {
                src: '../src/main/webapp/styles/*.css'
            }
        },

        // Reads HTML for usemin blocks to enable smart builds that automatically
        // concat, minify and revision files. Creates configurations in memory so
        // additional tasks can operate on them
        useminPrepare: {
            html: '<%= flinkVisual.app %>/index.html',
            options: {
                dest: '../src/main/webapp',
            }
        },

        // Performs rewrites based on filerev and the useminPrepare configuration
        usemin: {
            html: [
                '../src/main/webapp/{,*/}*.html'
            ],
            css: [
                '../src/main/webapp/styles/{,*/}*.css',
                '../src/main/webapp/app/**/*.css'
            ],
            options: {
                assetsDirs: [
                    '../src/main/webapp',
                    '../src/main/webapp/images'
                ]
            }
        },

        uglify: {
            dist: {
                files: {
                    '../src/main/webapp/common/scripts.js': [
                        '../src/main/webapp/common/scripts.js'
                    ]
                }
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
                    cwd: '../src/main/webapp',
                    src: ['*.html', 'views/{,*/}*.html', 'app/{,*/}*.html'],
                    dest: '../src/main/webapp'
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
                        '../src/main/webapp/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
                        '../src/main/webapp/app/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
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
                        require('autoprefixer')({browsers: ['last 3 versions']}),
                        require('postcss-discard-comments')(),
                        require('postcss-colormin')(),
                        require('postcss-convert-values')(),
                        require('postcss-ordered-values')(),
                        require('postcss-minify-font-weight')(),
                        require('postcss-merge-longhand')(),
                        require('postcss-merge-rules')(),
                        require('postcss-discard-empty')(),
                        require('perfectionist')({format: 'compressed' })
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
                        require('autoprefixer')({browsers: ['last 3 versions']})
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
                files: [{
                    dot: true,
                    src: [
                        '<%= flinkVisual.tmp %>',
                        '../src/main/webapp/{,*/}*',
                        '!../src/main/webapp/WEB-INF/**',
                        '!../src/main/webapp/.gitignore'
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
                    dest: '../src/main/webapp',
                    src: [
                        '*.{ico,png,txt}',
                        '.htaccess',
                        '*.html',
                        'app/{,*/}*.html',
                        'views/{,*/}*.html',
                        'images/{,*/}*.{webp}',
                        'fonts/{,*/}*.*',
                        '{,*/}*.json'
                    ]
                }, {
                    expand: true,
                    cwd: '<%= flinkVisual.tmp %>/images',
                    dest: '../src/main/webapp/images',
                    src: ['generated/*']
                }, {
                    expand: true,
                    cwd: 'bower_components/components-font-awesome/fonts',
                    dest: '../src/main/webapp/fonts',
                    src: ['*']
                }, {
                    expand: true,
                    cwd: 'bower_components/components-bootstrap/fonts',
                    dest: '../src/main/webapp/fonts',
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

    grunt.registerTask('build', [
        'clean:dist',
        'bower',
        'wiredep',
        'useminPrepare',
        'concat:generated',
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
