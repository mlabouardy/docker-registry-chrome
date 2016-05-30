var RegistryBuilderCtrl = function (Store) {
  var self = this;
  self.registry = {
    api : 'v1',
    protocol: 'http'
  };
  self.protocols = ['http', 'https'];
  self.apis = ['v1', 'v2'];

  var RegistryManager = Store.registryManager;

  self.checkFields = function () {
    var error = !self.registry.hostname ||
                !self.registry.port ||
                !self.registry.api ||
                !self.registry.protocol;
    return !error;
  };

  self.create = function (item) {
    if (self.checkFields())
      RegistryManager.create(angular.copy(item));
  };

};

var RegistryBuilderComponent = {
    templateUrl : 'app/components/registry-builder/registry-builder.html',
    controller : RegistryBuilderCtrl
};

angular.module('dockerRegistry.components')
       .component('registrybuilder', RegistryBuilderComponent);
