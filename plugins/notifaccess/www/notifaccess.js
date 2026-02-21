var exec = require('cordova/exec');

module.exports = {
  isEnabled: function (success, fail) {
    exec(success, fail, 'NotifAccess', 'isEnabled', []);
  },
  openSettings: function (success, fail) {
    exec(success, fail, 'NotifAccess', 'openSettings', []);
  }
};
