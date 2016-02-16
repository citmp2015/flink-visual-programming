(function() {

    'use strict';

    angular
        .module('app')
        .factory('Socket', Socket);

    /*@ngInject*/
    function Socket(socketFactory, graphFactory, $log) {

        var generalSettings = graphFactory.getGeneralSettings();
        var domain = generalSettings.flinkURL + ':' + generalSettings.flinkPort;

        var myIoSocket = io.connect(domain, {
            path: '/ControllerWebSocket',
            query: ''
        });

        var mySocket = socketFactory({
            ioSocket: myIoSocket
        });
        mySocket.forward();
        return mySocket;
    }

})();
