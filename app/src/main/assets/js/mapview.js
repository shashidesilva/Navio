/**
 * @author ShashiDeSilva
 */
 var map;
    var start;
    var end;
    var directionsDisplay;
	var directionsService = new google.maps.DirectionsService();
	var stepDisplay;
	var markerArray = [];
	
	if(navigator.geolocation) {
    		navigator.geolocation.getCurrentPosition(function(position) {
      			start = new google.maps.LatLng(position.coords.latitude,
                                       position.coords.longitude);
                                       
                end = new google.maps.LatLng(World.markerLat,
                                       World.markerLong);

      			var marker = new google.maps.Marker({
      				position: start,
      				map: map,
      				title: 'I am here',
      				
  				});
  				
  		//		var endmarker = new google.maps.Marker({
      	//			position: end,
      	//			map: map,
      	//			title: 'My destination'
  			//	});

      			map.setCenter(start);
    		}, function() {
      			handleNoGeolocation(true);
    			});
  		} else {
    		// Browser doesn't support Geolocation
    			handleNoGeolocation(false);
  			}
  		
    
      function initialize() {
      	
      	// Create a map and center it on Colombo.
  		var colombo = new google.maps.LatLng(6.9215479, 79.8563022);
        var mapOptions = {
          zoom: 16,
          center: colombo
        };
        
        map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

  		// Instantiate a directions service.
  		directionsService = new google.maps.DirectionsService();
  		// Create a renderer for directions and bind it to the map.
  		var rendererOptions = {
    		map: map
  		};
  		directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);

  		// Instantiate an info window to hold step text.
  		stepDisplay = new google.maps.InfoWindow(); 
  			  
      }
      
      function calcRoute() {
  		var selectedMode = document.getElementById('mode').value;
  		
  		var request = {
      		origin:start,
      		destination:end,
      		travelMode: google.maps.TravelMode[selectedMode]
  		};
  			directionsService.route(request, function(response, status) {
    	if (status == google.maps.DirectionsStatus.OK) {
      		directionsDisplay.setDirections(response);
    		}
  			});
		}

      google.maps.event.addDomListener(window, 'load', initialize);
      
      
      /////////////////////////////
     /* alert(getDistanceFromLatLonInKm(59.3293371,13.4877472,59.3225525,13.4619422).toFixed(1));



	function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
  		var R = 6371; // Radius of the earth in km
  		var dLat = deg2rad(lat2-lat1);  // deg2rad below
  		var dLon = deg2rad(lon2-lon1); 
  		var a = 
    			Math.sin(dLat/2) * Math.sin(dLat/2) +
    			Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
    			Math.sin(dLon/2) * Math.sin(dLon/2)
    				; 
  			var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
  			var d = R * c; // Distance in km
  		return d;
	}

	function deg2rad(deg) {
  		return deg * (Math.PI/180);
	}
	*/
      //////////////////////////////