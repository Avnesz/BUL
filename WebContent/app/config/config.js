require.config({
    baseUrl: "",
    paths: {
        "jquery": "lib/jquery-3.1.1.min",
        "jquery-mousewheel": "lib/jquery.mousewheel.min",
        "underscore": "lib/underscore.min",
        "text": "lib/text.min",
        "sha": "lib/js-sha256.min"
    },
    urlArgs: "version=" + (new Date()).getTime()
});