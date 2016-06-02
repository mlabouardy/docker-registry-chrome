var RegistryV2 = function (AbstractRegistry) {
  var url = '/v2/repositories';

  this.getRepositories = function (data) {
    return AbstractRegistry.getRepositories(url, data);
  };

  this.getTags = function (data) {
    return AbstractRegistry.getRepositories(url + '/tags', data);
  };

  this.ping = function (data) {
    return AbstractRegistry.getRepositories(url + '/ping', data);
  };
};

angular.module('dockerRegistry.services')
       .service('RegistryV2', RegistryV2);
