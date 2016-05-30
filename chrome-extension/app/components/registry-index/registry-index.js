var RegistryIndexCtrl = function ($mdDialog, Store) {
  var self = this;
  self.registries = [];

  self.remove = function (ev, index) {
    var confirm = $mdDialog.confirm()
          .title('Would you like to delete this registry?')
          .ariaLabel('Lucky day')
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
};

var RegistryIndexComponent = {
  templateUrl: 'app/components/registry-index/registry-index.html',
  controller: RegistryIndexCtrl
};

angular.module('dockerRegistry.components')
       .component('registryindex', RegistryIndexComponent);
