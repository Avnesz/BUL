/*global define */
define(["app/utils/utils"], 
function(Utils) {
	'use strict';

	return function() {
		this.data = {
				"login" : "",
				"mdp" : ""
		};
		
		this.validate = function() {
			var login = $(".menu-page #login").val();
			var mdp = $(".menu-page #mdp").val();
			if (!login || !mdp) {
				return "Veuillez indiquer un Identifiant et un Mot de passe de connexion.";
			}
		};

		this.send = function(success) {
			Utils.load("connect", this.data, success, "POST");
		};
		
		
	};
});