(function() {

    'use strict';

    angular
        .module('app')
        .factory('webSocket', webSocket);

    /*@ngInject*/
    function webSocket($websocket, graphFactory, $interval, $log) {

        var dataStream, scope, reconnectIntervalId, sendIntervalId;

        openConnection();

        function openConnection() {

            $log.debug('ws connection try');

            dataStream = $websocket(graphFactory.getGeneralSettings().flinkWsUrl + '/ControllerWebSocket');

            dataStream.onMessage(onMessage);
            dataStream.onOpen(onOpen);
            dataStream.onClose(onClose);
            dataStream.onError(onError);

            if (sendIntervalId) {
                $interval.cancel(sendIntervalId);
                sendIntervalId = -1;
            }
            sendIntervalId = $interval(function() {
                dataStream.send('still alive');
            }, 30000);
        }

        function onMessage(e) {

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

        }

        function onOpen(e) {
            $log.debug('ws connection opened');
            if (reconnectIntervalId) {
                $interval.cancel(reconnectIntervalId);
                reconnectIntervalId = -1;
            }
        }

        function onClose(e) {
            if (sendIntervalId) {
                $interval.cancel(sendIntervalId);
                sendIntervalId = -1;
            }
            if (!reconnectIntervalId) {
                reconnectIntervalId = $interval(openConnection, 1500);
            }
            $log.debug('ws connection closed');
        }

        function onError(e) {
            $log.debug('ws connection error', e);
        }

        return {
            forward: function(thisScope) {
                scope = thisScope;
            }
        };
    }

})();
