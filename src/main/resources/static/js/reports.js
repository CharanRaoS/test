 $(document).ready(function () {
	 	RunReport(reportName, bookMarkState);
	 console.log('page loaded');
    });


function RunReport(reportName, bookMarkState) {
	models = window['powerbi-client'].models;

	reportContainer = $("#report-container").get(0);

	// Initialize iframe for embedding report
//	powerbi.bootstrap(reportContainer, {
//		type : "report"
//	});

	// Request to get embed details
	$.ajax({
		type : "POST",
		data : {
			'reportName' : reportName
			},
		url : "/getembedinfo",
		dataType : "json",
		success : function(embedData) {
			reportLoadConfig = {
				type : "report",
				tokenType : models.TokenType.Embed,
				accessToken : embedData.embedToken,

				// Use other embed report config based on the requirement. We
				// have used the first one for demo purpose
				embedUrl : embedData.embedReports[0].embedUrl,
			/*
			 * // Enable this setting to remove gray shoulders from embedded
			 * report settings: { background: models.BackgroundType.Transparent }
			 */
			};

			// Use the token expiry to regenerate Embed token for seamless end
			// user experience
			// Refer https://aka.ms/RefreshEmbedToken
			tokenExpiry = embedData["tokenExpiry"]

			// Embed Power BI report when Access token and Embed URL are
			// available
			report = powerbi.embed(reportContainer, reportLoadConfig);
			
			report.on("loaded", function () {
		            // Apply the bookmark state
		        if (bookMarkState.trim().length > 0 && bookMarkState.trim() != 'N') {
		        	bookMarkState = bookMarkState.replaceAll(/\s/g, '+');
		        	report.bookmarksManager.applyState(bookMarkState);
		        }
		        report.off("loaded");
		    });
			
			// Triggers when a report schema is successfully loaded
//			report.on("loaded", function() {
//				console.log("Report load successful");
//			});

			// Triggers when a report is successfully embedded in UI
			report.on("rendered", function() {
				console.log("Report render successful");
			});

			// Clear any other error handler event
			report.off("error");

			// Below patch of code is for handling errors that occur during
			// embedding
			report.on("error", function(event) {
				errorMsg = event.detail;

				// Use errorMsg variable to log error in any destination of
				// choice
				console.error(errorMsg);
				return;
			});
		},
		error : function(err) {

			// Show error container
			$(".embed-container").hide();
			var errorContainer = $(".error-container");
			errorContainer.show();

			// Format error message
			var errMessageHtml = "<strong> Error Details: </strong> <br/>"
			errMessageHtml += err.responseText.split("\n").join("<br/>")

			// Show error message on UI
			errorContainer.html(errMessageHtml);
		}
	});
}