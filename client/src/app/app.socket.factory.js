(function() {

    'use strict';

    angular
        .module('app')
        .factory('webSocket', webSocket);

    /*@ngInject*/
    function webSocket($websocket, graphFactory, $log) {

        var generalSettings = graphFactory.getGeneralSettings();
        var domain = generalSettings.flinkURL + ':' + generalSettings.flinkPort;

        var dataStream = $websocket(domain.replace('http://', 'ws://') + '/ControllerWebSocket');

        dataStream.onMessage(function(message) {
            $log.debug(message);
        });

        dataStream.onOpen(function() {
            $log.debug('ws connection opened');
        });

        dataStream.onClose(function() {
            $log.debug('ws connection closed');
        });

        dataStream.onError(function() {
            $log.debug('ws connection error');
        });

        var methods = {
            get: function() {
                dataStream.send('lala');
            }
        };

        return methods;
    }

})();
