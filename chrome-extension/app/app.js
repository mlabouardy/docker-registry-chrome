angular.module('dockerRegistry', ['dockerRegistry.components', 'dockerRegistry.stores'])
       .controller('MainCtrl', function($scope){
          $scope.registry = {};
       });
