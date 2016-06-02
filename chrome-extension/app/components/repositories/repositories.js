var RepositoriesCtrl = function (RegistryV1, RegistryV2, Cache, Store) {
  var self = this;

  self.repositories = [];
  self.api = Store.selectedRegistry.api;
  self.tags = [];
  self.emptyRepository = false;

  var Repositories = Store.selectedRegistry.api === 'v2' ?
                     RegistryV2 : RegistryV1;

  Repositories.getRepositories(Store.selectedRegistry).then(function (response) {
    self.repositories = Store.selectedRegistry.api === 'v2' ?
                        response.plain().repositories : response.plain().results;

    self.emptyRepository = self.repositories.length === 0;

    if (self.repositories.length > 0) {
      var repoName = Store.selectedRegistry.api === 'v1' ?
                     self.repositories[0].name : self.repositories[0];

      var data = {
        registry: Store.selectedRegistry,
        repoName: repoName
      };

      Repositories.getTags(data).then(function (response) {
        self.tags = self.api === 'v2' ?
                    response.plain().tags : response.plain();
        Cache.set(repoName, self.tags);
      });
    }
  });

  self.onSelectTab = function (repo) {
    self.tags = [];
    var data = {
      registry: Store.selectedRegistry,
      repoName: repo
    };
    if (!Cache.get(repo)) {
      Repositories.getTags(data).then(function (response) {
        self.tags = self.api === 'v2' ?
                    response.plain().tags : response.plain();
        Cache.set(repo, self.tags);
      });
    } else {
      self.tags = Cache.get(repo);
    }
  };
};

var RepositoriesComponent = {
  templateUrl: 'app/components/repositories/repositories.html',
  controller: RepositoriesCtrl
};

angular.module('dockerRegistry.components')
       .component('repositories', RepositoriesComponent);
