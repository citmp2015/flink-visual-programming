(function() {

    'use strict';

    angular
        .module('app')
        .factory('webSocket', webSocket);

    /*@ngInject*/
    function webSocket($websocket, graphFactory, $log) {

        var dataStream = $websocket(graphFactory.getGeneralSettings().flinkWsUrl + '/ControllerWebSocket');
        var scope;

        dataStream.onMessage(function(e) {
            var data, eventKey = e.data;
            if (eventKey.indexOf(' ') !== -1) {
                eventKey = eventKey.substr(0, eventKey.indexOf(' '));
                data = e.data.substr(e.data.indexOf(' '));
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
