
var taskManagerModule = angular.module('ToDoList', []);

 taskManagerModule.controller("ToDoListCtrl", function ($scope,$http) {
	var urlBase="http://localhost:8080"; 		
	$scope.tasks = [];
	$scope.taskStatus = undefined; //task show: all, has done, has't done.  (undefined, true, false)
 	$http.defaults.headers.post["Content-Type"] = "application/json; charset=utf-8";

 	//pagination
 	$scope.currentPage = 0;
 	$scope.tasksPerPage = 5;	

 	//Count task by value taskStatus
 	$scope.countTasksByStatus = function () {
 		if ($scope.taskStatus == undefined)
 			return $scope.tasks.length;

 		var cout = 0;
 		for (i = 0; i < $scope.tasks.length; ++i) {
 			if ($scope.tasks[i].hasdone == $scope.taskStatus){
 				cout++;				
 			}
 		}
 		return cout;
 	}
 	
 	$scope.firstPage = function() {
 		return $scope.currentPage == 0;
 	}

 	$scope.lastPage = function() {  	    	
    	var lastPageNum = Math.ceil($scope.countTasksByStatus() / $scope.tasksPerPage - 1);
    	return $scope.currentPage == lastPageNum;
    }
    $scope.numberOfPages = function(){    	
    	return Math.ceil($scope.countTasksByStatus() / $scope.tasksPerPage);
    }   

    $scope.startingTask = function() {
    	return $scope.currentPage * $scope.tasksPerPage;
    }

    $scope.pageBack = function() {
    	if ($scope.currentPage > 0) 
    		$scope.currentPage = $scope.currentPage - 1;
    }

    $scope.pageForward = function() {
    	if ($scope.currentPage < $scope.numberOfPages()-1)
    		$scope.currentPage = $scope.currentPage + 1;    	
    } 	

 
 	//Get all tasks
 	$http({
 				method: "GET",
				url: urlBase + "/tasks",
				headers: {"Content-Type":"application/json; charset=utf-8"}				
			}).then(function successCallback(response) {
				$scope.tasks = response.data;
			}, function errorCallback(response) { 
	});

    //ADD new task
    //Example {"description":"This is task","id":0,"date":1493212282000,"hasdone":false}
	$scope.addNewTask = function addTask() {
  		if($scope.form_add_description==""){
   			return;
   		}
	  	
  		if ($scope.form_add_chk == undefined)
  			$scope.form_add_chk = false
  		
  		var newTask = {	  			       
        	"description": $scope.form_add_description,
        	"id": 0,
        	"date": new Date().getTime(),
        	"hasdone": $scope.form_add_chk,      	
    	}
    	
    	$http({
				method: "POST",
				url: urlBase + "/task",
				headers: {"Content-Type":"application/json; charset=utf-8"},
				data: newTask
			}).then(function successCallback(response) {
				$scope.tasks.push (response.data); //Get Added Task from Server with right ID. Now, we know ID!
	    		$scope.currentPage = $scope.numberOfPages()-1; //Set last page as current
	    		$scope.form_add_description = "";
	    		$scope.form_add_chk = false;	    		

			}, function errorCallback(response) { 
		});
	}

	//Remove Task
	$scope.removeTask = function removeTask(task) {
		$http.delete(urlBase + "/task/" + task.id, []).
	    		then(function successCallback(response) {	    			   			
	    			var index = $scope.tasks.indexOf (task);	    			
	    			$scope.tasks.splice (index, 1);
	     		}, function errorCallback(response) {
	     });
	}

	//Update Task
	$scope.updateTask = function updateTask(el_type, id) {
		var index  = -1;		

    	for (i = 0; i < $scope.tasks.length; ++i) {
    		if ($scope.tasks[i].id == id){
    			index = i;
    			break;
    		}
    	}
    	if (index == -1)
    		return;

    	if (el_type == 'checkbox')
    		$scope.tasks[index].hasdone = $scope.tasks[index].hasdone ? false : true;

		$http.put(urlBase + "/task/" + id, $scope.tasks[index]).
	     		then(function successCallback(response) {	    			   				    			
	  			},function errorCallback(response) {
	     });
	}

}); //taskManagerModule.controller

//Filtr pagination
taskManagerModule.filter('startFrom', function(){
	return function(input, start){
		start = +start;
		return input.slice(start);
	}
})