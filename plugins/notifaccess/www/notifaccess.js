var exec = require('cordova/exec');

var NotifAccess = {
  // buka halaman settings notification access
  requestPermission: function (success, error) {
    exec(success, error, 'NotifAccess', 'requestPermission', []);
  },

  // cek apakah service notification listener di-enable
  isEnabled: function (success, error) {
    exec(success, error, 'NotifAccess', 'isEnabled', []);
  }
};

module.exports = NotifAccess;
