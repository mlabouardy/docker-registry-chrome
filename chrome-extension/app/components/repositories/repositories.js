var RepositoriesCtrl = function (Repositories) {
  console.log(Repositories.getRepositories());
};

var RepositoriesComponent = {
  templateUrl: 'app/components/repositories/repositories.html',
  controller: RepositoriesCtrl
};

angular.module('dockerRegistry.components')
       .component('repositories', RepositoriesComponent);
