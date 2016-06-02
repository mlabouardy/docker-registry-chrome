angular.module('dockerRegistry', ['dockerRegistry.controllers', 'dockerRegistry.components', 'dockerRegistry.stores', 'ngRoute'])
       .config(function ($routeProvider) {
         $routeProvider
          .when('/', {
            templateUrl: 'app/controllers/home/home.html',
            controller : 'HomeCtrl'
          })
          .when('/repositories', {
            templateUrl: 'app/controllers/repositories/repositories.html',
            controller : 'RepositoriesCtrl'
          })
          .otherwise({
            redirectTo:'/'
          });
       });
