var Store = function() {
  var self = this;

  self.registries = [];

  var callbackComponents = [];

  self.registryManager = {
    create: function(item){
        self.registries.push(item);
        self.notifyComponents();
    },
    delete: function(index){
      self.registries.splice(index, 1);
      self.notifyComponents();
    }
  };

  self.notifyComponents = function(){
    for (var i = 0; i < callbackComponents.length; i++){
      callbackComponents[i](self.registries);
    }
  };

  self.subscribe = function(callback){
    callbackComponents.push(callback);
  };

};

angular.module('dockerRegistry.stores')
.service('Store', Store);
