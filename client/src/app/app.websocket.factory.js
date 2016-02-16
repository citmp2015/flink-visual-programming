(function() {

    'use strict';

    angular
        .module('app')
        .factory('webSocket', webSocket);

    /*@ngInject*/
    function webSocket($websocket, graphFactory, $interval, $log) {

        var dataStream = $websocket(graphFactory.getGeneralSettings().flinkWsUrl + '/ControllerWebSocket');
        var scope;

        $interval(function() {
            dataStream.send('still alive');
        }, 30000);

        dataStream.onMessage(function(e) {

            var events = [],
                data, message = e.data;
            var m, uuid, eventKey, regEx = /((graph):(.*):(\w+))/g;

            while ((m = regEx.exec(message)) !== null) {
                if (m.index === regEx.lastIndex) {
                    regEx.lastIndex++;
                }
                if (m.length > 4) {
                    uuid = m[3];
                    eventKey = m[2] + ':' + m[4];
                }
            }
            if (message.indexOf(' ') !== -1) {
                data = e.data.substr(e.data.indexOf(' '));
            }

            if (uuid && eventKey) {
                events.push({
                    key: eventKey,
                    data: [uuid, data]
                });
            }

            events.push({
                key: message.indexOf(' ') !== -1 ? message.substr(0, message.indexOf(' ')) : message,
                data: [data]
            });

            if (scope && scope.$broadcast) {
                angular.forEach(events, function(event) {
                    $log.debug(event.key, event.data[0], event.data[1]);
                    scope.$broadcast(event.key, event.data[0], event.data[1]);
                });
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
