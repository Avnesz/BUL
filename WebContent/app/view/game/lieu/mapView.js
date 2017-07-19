/*global define */
define(["jquery",
        'underscore',
        "text!app/template/game/lieu/map.html",
        "app/model/game/mouseModel",
        "app/model/game/cameraModel",
        "app/view/game/lieu/terrainView",
        "app/model/game/refreshMapModel",
        "jquery-mousewheel"],
function($, _, page, MouseModel, CameraModel, Terrain, RefreshMapModel) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.parent = parent;
			this.player = parent.player;
			this.player.addToInventory("pelle");
			this.player.pickItem("pelle");
			
			this.el = $("#map");
			
			this.camera = new CameraModel();
			this.mouse = new MouseModel();
			this.refreshMapModel = new RefreshMapModel();
			
			this.terrain = new Terrain(this);
			
			this.render();
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			
			var content = template(templateData).replace(/>\s*</g, '><');
			this.el.html(content);
			
			this.terrain.load();
			
			this.makeEvents();
			this.loopEvents();
		};
		
		this.makeEvents = function() {
		    var that = this;
		    
		    this.el.mousewheel(function(event){
		        that.camera.zoom(event.deltaY*0.25);
		    });
		};
		
		this.loopEvents = function() {
		    var that = this;

		    this.refreshMapModel.send(null, this.player.terrain, function(data) {
		        if (data.codeRetour == 0) that.terrain.refresh(data.newTerrain);
		    });
		    
		    setTimeout(function() {
		        that.loopEvents();
		    }, 3000);
		};
		
		this.init(parent);
	};
});