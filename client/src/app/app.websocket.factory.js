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
        var scope;

        dataStream.onMessage(function(message) {
            var eventKey = message;
            var data = null;
            if (eventKey.indexOf(' ')) {
                eventKey = eventKey.substr(0, eventKey.indexOf(' '));
                data = message.substr(message.indexOf(' '));
            }
            $log.debug(eventKey, data);
            if (scope && scope.$broadcast) {
                scope.$broadcast(eventKey, data);
            }
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

        return {
            forward: function(thisScope) {
                scope = thisScope;
            }
        };
    }

})();
