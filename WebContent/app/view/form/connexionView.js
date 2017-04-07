/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "app/utils/messageUtils",
        "text!app/template/form/connexion.html",
        "app/model/form/connexionModel",
        "app/view/form/inscriptionView"], 
function($, _, Utils, MessageUtils, page, Model, InscriptionView) {
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
			
			this.checkEvents();
		};
		
		this.checkEvents = function() {
			var that = this;
			$("#connexion").click(function() {
				MessageUtils.hideError();
				that.model.send(function(data) {
					if (data.codeRetour != 0) {
						MessageUtils.showError(data.message);
					}else {
						console.log("success");
					}
				});
			});
			$("#inscription").click(function() {
				if (!that.inscriptionView) {
					that.inscriptionView = new InscriptionView(that);
				}
				that.inscriptionView.show();
			});
		};
		
		this.show = function() {
			Utils.load("track", {"where" : "Menu de connexion"}, function(data) {}, "POST");
			this.render();
		};
		
		this.init(parent);
	};
});