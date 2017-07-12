/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "text!app/template/game/lieu/map.html",
        "app/model/game/mouseModel",
        "app/model/game/cameraModel",
        "app/view/game/lieu/terrainView",
        "jquery-mousewheel"],
function($, _, Utils, page, MouseModel, CameraModel, Terrain) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.parent = parent;
			this.el = $("#map");
			
			this.camera = new CameraModel();
			this.mouse = new MouseModel();
			
			this.terrain = new Terrain(this);
			
			this.render();
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			
			var content = template(templateData).replace(/>\s*</g, '><');
			this.el.html(content);
			
			this.terrain.load()
			
			this.makeEvents();
			this.loopEvents();
		};
		
		this.makeEvents = function() {
		    var that = this;
		    
		    this.el.mousewheel(function(event){
		        that.camera.zoom(event.deltaY*0.125);
		    });
		};
		
		this.loopEvents = function() {
		    var that = this;

		    
		    setTimeout(function() {
		        that.loopEvents();
		    }, 45)
		};
		
		this.init(parent);
	};
});