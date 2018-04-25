/**
 * Created by Mohammed.Sameen on 24-04-2018.
 */
'use strict';

angular.module('todo').factory('TodoService',['$http','baseURL',function($http,baseURL){

    var todoFactory={};

    todoFactory.createTask=function (name,description,date,status) {
        return $http({
            method:'POST',url :baseURL +'persist',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Accept': 'application/json'
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    if (obj[p] != null) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));

                    }
                }
                return str.join("&");
            },
            data:{'name':name,'description':description,'date':date,'status':status}
        });
    };

    todoFactory.updateTask= function (id,name,description,date,status) {
        return $http({
               method:'PUT',url :baseURL +'update',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Accept': 'application/json'
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    if (obj[p] != null) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));

                    }
                }
                return str.join("&");
            },
            data:{'id':id,'name':name,'description':description,'date':date,'status':status}
        });
    };

    todoFactory.deleteTask =function(id){
        return $http({
            method:'DELETE',url: baseURL +'delete/'+id
          /*  data:{'id':id}*/
        });
    };

    todoFactory.findAll=function () {
        return $http({
            method :'GET',url:baseURL+'findAll'
        });
    };

    return todoFactory;

}]);