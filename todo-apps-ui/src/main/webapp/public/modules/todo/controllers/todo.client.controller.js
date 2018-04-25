/**
 * Created by Mohammed.Sameen on 24-04-2018.
 */
'use strict';

angular.module('todo').controller('TodoController', ['$rootScope', '$scope','TodoService','$window',
    function ($rootScope, $scope,TodoService, $window) {


        $scope.createUpdateItem=function () {
            if($scope.id){
                $scope.updateTask();
            }else{
                $scope.createTask();
            }
        };

        //To insert new Task
        $scope.createTask= function () {
            TodoService.createTask($scope.name,$scope.description,$scope.completionDate,$scope.statusValue.value).
            then(function(response){
                $scope.findAll();
            },function(err){
                console.log(err);
            });
        };

        //To update the existing Task
        $scope.updateTask=function () {
            TodoService.updateTask($scope.id,$scope.name,$scope.description,$scope.completionDate,$scope.statusValue.value).
            then(function(response){
                    $scope.findAll();
            },function (err) {
                    console.log(err);
            }
            );

        };

        $scope.deleteTask=function (id) {
          if ($window.confirm('are you sure want to delete?')) {
            TodoService.deleteTask(id).then(
                function (response) {
                    $scope.findAll();
                }, function (err) {
                    console.log(err);
                }
            );
        }
        };

        $scope.findAll=function () {
            TodoService.findAll().then(function(response){
                $scope.task = {};

                $scope.tasks=response.data;

                $scope.totalItems = $scope.tasks.length;
                $scope.viewby = 10;
                $scope.currentPage = 1;
                $scope.itemsPerPage = $scope.viewby;
                $scope.maxSize = 5;
                $scope.reset();

            },function(err){
                console.log(err);
            });

        };

        function editedValue(task) {
            $scope.id=task.id;
            $scope.name=task.name;
            $scope.description=task.description;
            $scope.completionDate=new Date(task.date);
            $scope.statusValue=task.status;
        }

        $scope.editTask=function(task){
                editedValue(task);
        };

        $scope.resetBtn = function() {
            if($scope.userForm.$dirty){
                if ($window.confirm('Are you sure want to Reset the form?')) {
                    $scope.id="";
                    $scope.name="";
                    $scope.description="";
                    $scope.completionDate="";
                    $scope.statusValue="";
                }
            }
        }
        $scope.reset = function() {
            $scope.id="";
            $scope.name="";
            $scope.description="";
            $scope.completionDate="";
            $scope.statusValue="";
        };

        //Render the Status in Select Item
        $scope.status=[{id:1,value:"completed"},{id:2,value:"pending"}];

        $scope.setPage = function (pageNo) {
            $scope.currentPage = pageNo;
        };

        $scope.pageChanged = function() {
            console.log('Page changed to: ' + $scope.currentPage);
        };

        $scope.setItemsPerPage = function(num) {
            $scope.itemsPerPage = num;
            $scope.currentPage = 1; //reset to first page

        }

    }
]);