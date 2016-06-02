var Cache = function () {
  var self = this;

  var memory = {};

  self.set = function(key, value){
    memory[key] = value;
  };

  self.get = function(key){
    return memory[key];
  };

  


};

angular.module('dockerRegistry.stores')
.service('Cache', Cache);
