/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "text!app/template/game/lieu/terrain.html"],
function($, _, Utils, page) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.parent = parent;
			this.el = "#terrain";
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {
			        couche1 : this.tileMap
			};
			
			var content = template(templateData).replace(/>\s*</g, '><');
			
			console.log(content);
			$(this.el).html(content);
		};
		
		this.load = function() {
		    this.tileMap = [
                ["eau", "eau", "eau"],
                ["eau", "", "eau"],
                ["eau", "eau", "eau"]
            ];
		    
		    this.render();
		};
		
		this.init(parent);
	};
});