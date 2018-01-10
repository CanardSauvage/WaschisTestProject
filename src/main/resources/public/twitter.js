angular.module('versions', ['ngResource', 'ngSanitize'])
    .service('twitterService', function () {
        var vm = this;

        vm.tweetAuthor = function (tweet) {
            if (tweet.retweeted_status != undefined) {
                return tweet.retweeted_status.user;
            }
            return tweet.user;
        };

        vm.isRetweet = function (tweet) {
            return !(tweet.retweeted_status == undefined);
        };

        vm.tweetSourceUrl = function (tweet) {
            // eg. https://twitter.com/AS_ideAS/status/563709681483677696
            return 'https://twitter.com/' + tweet.user.id_str + '/status/' + tweet.id_str;
        };

        // returns eg: 'Mon Nov 24'
        vm.tweetDateTimeString = function (tweet) {
            // eg. 'Fri Feb 06 14:43:55 +0000 2015'
            var charArray = tweet.created_at.split(' ');
            var timeArray = charArray[3].split(':');
            return '· ' + charArray[2] + '. ' + charArray[1] + ' | ' + timeArray[0] + ':' + timeArray[1];
        };


        vm.hasImage = function (tweet) {
            return !(tweet.extended_entities == undefined);
        };

        vm.getImageUrl = function (tweet) {
            return tweet.extended_entities.media[0].media_url;
        };


        return vm;
    })
    .controller('twitterController', function ($scope, $resource, $interval, twitterService) {
        $scope.twitterService = twitterService;

        // infos rest resource
        var twitterResource = $resource('rest/twitter');


        $scope.loadTwitter = function () {
            // Do not store the result of query() into the $scope directly.
            // The rest call may take some time and query() returns an empty resource immediately and updates it later.
            // This leads to flickering
            var promise = twitterResource.get().$promise;
            promise.then(function (result) {
                console.info(result);
                $scope.tweets = result;
            });
            promise.catch(function (error) {
                console.error(error);
            });
        };

        // execute loadTwitter every 120 seconds
        $interval($scope.loadTwitter, 120 * 1000);
        // load Twitter initially
        $scope.loadTwitter();

    });