/**
 * Created by Mohammed.Sameen on 24-04-2018.
 */
'use strict';

// Setting up route
var app = angular.module('todo');
app.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        // Redirect to main view when route not found
        $urlRouterProvider.otherwise('/main');

        // main state routing
        $stateProvider.state('main', {
            url: '/main',
            templateUrl: 'public/modules/todo/views/todo.client.view.html',
            controller: 'TodoController'
        });
    }
]);