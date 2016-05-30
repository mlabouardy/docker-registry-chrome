var Repositories = function (Restangular) {
    this.getRepositories = function () {
      return Restangular.oneUrl('routeName','http://frpar-ccvrp-coreos-containers.corp.capgemini.com:5010/v1/search').get();
    };
};

angular.module('dockerRegistry.services')
       .service('Repositories', Repositories);
