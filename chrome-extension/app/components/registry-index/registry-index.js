var RegistryIndexCtrl = function ($mdDialog, Store, $location, $scope) {
  var self = this;
  self.registries = [];
  self.registries = Store.registries;

  self.remove = function (ev, index) {
    var confirm = $mdDialog.confirm()
          .title('Would you like to delete this registry?')
          .targetEvent(ev)
          .ok('Yes')
          .cancel('No');
    $mdDialog.show(confirm).then(function () {
      self.registries.splice(index, 1);
    });
  };

  self.registryChanged = function (values) {
    self.registries = values;
  };

  Store.subscribe(self.registryChanged);

  self.view = function (registry) {
    Store.selectedRegistry = registry;
    $location.path('/repositories');
  };
};

var RegistryIndexComponent = {
  templateUrl: 'app/components/registry-index/registry-index.html',
  controller: RegistryIndexCtrl
};

angular.module('dockerRegistry.components')
       .component('registryindex', RegistryIndexComponent);
