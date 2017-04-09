/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "app/utils/viewUtils",
        "app/utils/messageUtils",
        "text!app/template/form/inscription.html",
        "app/model/form/inscriptionModel"], 
function($, _, Utils, ViewUtils, MessageUtils, page, Model) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.el = $("#form");
			this.model = new Model();
			this.parent = parent;
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			this.el.html(template(templateData));
			ViewUtils.verticalCenter();
			
			this.checkEvents();
		};
		
		this.checkEvents = function() {
			var that = this;
			$("#retour").click(function() {
				that.parent.show();
			});
			$("#inscription").click(function() {
				MessageUtils.hide();
				
				var errorMessage = that.model.validate();
				if (errorMessage) {
					MessageUtils.show(errorMessage, "danger");
					return;
				}
				
				that.model.send(function(data) {
					if (data.codeRetour != 0) {
						MessageUtils.show(data.message, "danger");
					}else {
						that.parent.show(data.message);
					}
				});
			});
		};
		
		this.show = function() {
			Utils.load("track", {"where" : "Menu d'inscription"}, function(data) {}, "POST");
			this.render();
		};
		
		this.init(parent);
	};
});