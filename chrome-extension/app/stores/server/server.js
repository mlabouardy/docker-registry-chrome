var Server = function () {
  var self = this;

  var backend = 'http://localhost:8080';

  self.set = function(value){
    backend = value;
  };

  self.get = function(){
    return backend;
  };
};

angular.module('dockerRegistry.stores')
.service('Server', Server);
