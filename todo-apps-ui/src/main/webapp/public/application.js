'use strict';

//Start by defining the main module and adding the module dependencies
angular.module(ApplicationConfiguration.applicationModuleName, ApplicationConfiguration.applicationModuleVendorDependencies);

//Then define the init function for starting up the application
angular.element(document).ready(function() {
	angular.bootstrap(document, [ApplicationConfiguration.applicationModuleName]);
});