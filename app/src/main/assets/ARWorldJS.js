var World = {
	loaded: false,

	init: function initFn() {
		this.createOverlays();
	},

	createOverlays: function createOverlaysFn() {
		/*
			First an AR.ClientTracker needs to be created in order to start the recognition engine. It is initialized with a URL specific to the target collection. Optional parameters are passed as object in the last argument. In this case a callback function for the onLoaded trigger is set. Once the tracker is fully loaded the function worldLoaded() is called.

			Important: If you replace the tracker file with your own, make sure to change the target name accordingly.
			Use a specific target name to respond only to a certain target or use a wildcard to respond to any or a certain group of targets.
		*/
		
		var tracker;
		var imgButton;
		
		
		this.tracker = new AR.ClientTracker("mycoll.wtc", {
			onLoaded: this.worldLoaded
		});

		/*
			The next step is to create the augmentation. In this example an image resource is created and passed to the AR.ImageDrawable. A drawable is a visual component that can be connected to an AR target (AR.Trackable2DObject) or a geolocated object (AR.GeoObject). The AR.ImageDrawable is initialized by the image and its size. Optional parameters allow for position it relative to the recognized target.
		*/

		/* Create overlay for page one */
		/*var imgOne = new AR.ImageResource("overlay1.png");
		var overlayOne = new AR.ImageDrawable(imgOne, 1, {
			offsetX: -0.15,
			offsetY: 0
		});*/

		/*
			The last line combines everything by creating an AR.Trackable2DObject with the previously created tracker, the name of the image target and the drawable that should augment the recognized image.
			Please note that in this case the target name is a wildcard. Wildcards can be used to respond to any target defined in the target collection. If you want to respond to a certain target only for a particular AR.Trackable2DObject simply provide the target name as specified in the target collection.
		*/
		
		this.imgButton = new AR.ImageResource("youtube.png");
		
		/*
			For each target an AR.ImageDrawable for the button is created by utilizing the helper function createWwwButton(url, options). The returned drawable is then added to the drawables.cam array on creation of the AR.Trackable2DObject.
		*/
		var pageOneButton = this.createWwwButton("https://www.youtube.com/watch?v=g6krjPi4Osc", 0.1, {
			offsetX: -0.25,
			offsetY: -0.25,
			zOrder: 1
		});
		
		
		var pageTwoButton = this.createWwwButton("https://www.youtube.com/watch?v=U-tKdWUPJ-w", 0.1, {
			offsetX: -0.25,
			offsetY: -0.25,
			zOrder: 1
		});
		
		
		
		var pageOne = new AR.Trackable2DObject(this.tracker, "target1", {
			drawables: {
				cam: pageOneButton
			}
		});
		
		var pageTwo = new AR.Trackable2DObject(this.tracker, "target2", {
			drawables: {
				cam: pageTwoButton
			}
		});
	},

	
	createWwwButton: function createWwwButtonFn(url, size, options) {
		/*
			As the button should be clickable the onClick trigger is defined in the options passed to the AR.ImageDrawable. In general each drawable can be made clickable by defining its onClick trigger. The function assigned to the click trigger calls AR.context.openInBrowser with the specified URL, which opens the URL in the browser.
		*/
		options.onClick = function() {
			AR.context.openInBrowser(url);
		};
		return new AR.ImageDrawable(this.imgButton, size, options);
	},


	worldLoaded: function worldLoadedFn() {
		var cssDivLeft = " style='display: table-cell;vertical-align: middle; text-align: right; width: 50%; padding-right: 15px;'";
		var cssDivRight = " style='display: table-cell;vertical-align: middle; text-align: left;'";
		document.getElementById('loadingMessage').innerHTML =
			"<div" + cssDivLeft + ">Scan Target &#35;1 (surfer):</div>" +
			"<div" + cssDivRight + "><img src='target1.png'></img></div>";

		// Remove Scan target message after 10 sec.
		setTimeout(function() {
			var e = document.getElementById('loadingMessage');
			e.parentElement.removeChild(e);
		}, 10000);
	}
};

World.init();
