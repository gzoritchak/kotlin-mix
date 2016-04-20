var app = angular.module('hotelApp', []);
app.controller('HotelsCtrl',

    function($scope, $http, $log) {
        $log.debug('Chargement de tous les hotels.');

        $scope.hotels = [];

        $http.get('/api/hotels')
            .success(function(data){
                $scope.hotels = data;
            })
            .error(function () {
                $scope.hotels = [
                    {
                        id: 1,
                        name: 'Hotel de Paris',
                        address: '16, rue de la Platière',
                        zip: '69001',
                        city: 'Lyon',
                        country: 'France'
                    },
                    {
                        id:2,
                        name: 'Hotel de Paris',
                        address: '16, rue de la Platière',
                        zip: '69001',
                        city: 'Lyon',
                        country: 'France'
                    }
                ]
            })
        ;

        $scope.selectedHotel = {};
        $scope.showEdit = false;

        $scope.changeName = function (hotel) {
            $http.put('/api/hotels/' + hotel.id, {name: hotel.name});
            hotel.edit = false;
        }

    });
