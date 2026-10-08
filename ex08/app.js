```javascript
var app = angular.module('myApp', []);

app.controller('myCtrl', function($scope) {

    $scope.filteredDept = [];

    $scope.employees = [
        { 'id': 1, 'dept': 'IT', 'sal': 5000, 'name': 'Ramesh' },
        { 'id': 2, 'dept': 'IT', 'sal': 4000, 'name': 'Girish' },
        { 'id': 3, 'dept': 'Management', 'sal': 10000, 'name': 'Kartik' },
        { 'id': 4, 'dept': 'Marketing', 'sal': 8000, 'name': 'Shenoy' },
        { 'id': 5, 'dept': 'Marketing', 'sal': 7000, 'name': 'Darvik' },
        { 'id': 6, 'dept': 'Management', 'sal': 17000, 'name': 'Ravi Kumar' }
    ];

    $scope.empFiltered = $scope.employees;

    $scope.departments = [
        { 'id': 1, 'name': 'IT', 'selected': false },
        { 'id': 2, 'name': 'Management', 'selected': false },
        { 'id': 3, 'name': 'Marketing', 'selected': false }
    ];

    $scope.sortEmp = function(dept_id) {

        $scope.departments.filter(function(e) {

            if (e.id === dept_id) {

                var idx = $scope.filteredDept.indexOf(e.name);

                if (idx === -1) {
                    $scope.filteredDept.push(e.name);
                } else {
                    $scope.filteredDept.splice(idx, 1);
                }

                $scope.filterEmpList();
            }
        });
    };


    $scope.filterEmpList = function() {

        $scope.filteredData = [];

        if ($scope.filteredDept.length !== 0) {

            $scope.empFiltered = [];

            angular.forEach($scope.filteredDept, function(val) {

                $scope.filteredData = $scope.employees.filter(function(e) {

                    if (e.dept === val) {
                        return e;
                    }

                });

                angular.forEach($scope.filteredData, function(v) {
                    $scope.empFiltered.push(v);
                });

            });

        } else {

            $scope.empFiltered = $scope.employees;

        }
    };


    $scope.hikeSal = function() {

        $scope.empFiltered.map(function(e) {

            if (e.hike === undefined) {
                e.hike = 0;
            }

            e.hike = parseInt(e.hike) + $scope.hike;

            e.hikeAmt = e.sal * ($scope.hike * 0.01);

            e.sal += e.sal * ($scope.hike * 0.01);

        });
    };

});
```
