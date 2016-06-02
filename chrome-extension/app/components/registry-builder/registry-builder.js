var RegistryBuilderCtrl = function ($mdDialog, $mdMedia, Server, Store, RegistryV1, RegistryV2, $window) {
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

  self.onRegistryAuthentication = function () {
    if (!self.registry.protected) {
      self.registry.username = '';
      self.registry.password = '';
    }
  };

  self.create = function (item) {
    if (self.checkFields()) {
      item.online = false;
      var Repositories = item.api === 'v2' ?
                         RegistryV2 : RegistryV1;

      Repositories.ping(item).then(function (response) {
        item.online = true;
        RegistryManager.create(angular.copy(item));
      }, function () {
        RegistryManager.create(angular.copy(item));
      });
    }
  };

  function ConfigController($scope, $mdDialog){
      $scope.hide = function() {
        $mdDialog.hide();
      };
      $scope.cancel = function() {
        $mdDialog.cancel();
      };
      $scope.answer = function(answer) {
        $mdDialog.hide(answer);
      };
  };

  self.configServer = function (ev) {
    $mdDialog.show({
      controller: ConfigController,
      templateUrl: 'app/templates/config.tmpl.html',
      parent: angular.element(document.body),
      targetEvent: ev,
      clickOutsideToClose:true
    })
    .then(function (server) {
      if(server){
          Server.set(server);
      }
    }, function() {
    });
  };

  self.help = function () {
    $window.open('https://github.com/mlabouardy/docker-registry-chrome', '_blank');
  };

};

var RegistryBuilderComponent = {
    templateUrl : 'app/components/registry-builder/registry-builder.html',
    controller : RegistryBuilderCtrl
};

angular.module('dockerRegistry.components')
       .component('registrybuilder', RegistryBuilderComponent);
