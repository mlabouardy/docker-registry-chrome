var AbstractRegistry = function (Restangular, Server) {

  this.getRepositories = function (resource, data) {
    var server = Server.get();
    return Restangular.oneUrl('routeName', server + resource)
                      .post('', data);
  };

  this.getTags = function (resource, data) {
    var server = Server.get();
    return Restangular.oneUrl('routeName', server + resource)
                      .post('', data);
  };

  this.ping = function (resource, data) {
    var server = Server.get();
    return Restangular.oneUrl('routeName', server + resource)
                      .post('', data);
  };
};

angular.module('dockerRegistry.services')
       .service('AbstractRegistry', AbstractRegistry);
