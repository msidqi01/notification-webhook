/* global cordova */
var exec = require('cordova/exec');

exports.requestPermission = function (success, error) {
  exec(success, error, 'NotifAccess', 'requestPermission', []);
};

exports.isEnabled = function (success, error) {
  exec(success, error, 'NotifAccess', 'isEnabled', []);
};
