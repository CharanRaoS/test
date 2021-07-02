function login(){
	$('#login_form').attr('action','home').submit();
}

function deleteBookMark(bookMarkName, bookMarkdate, userName) {
	$.ajax({
        url: "/deleteBookMark",  // Change the relative path if needed 
        type: "POST",// This is optional and defaults to GET as well
        data : {
			'bookMarkName' : bookMarkName,
			'bookMarkDate' : bookMarkdate,
			'userName' : userName
		},
        error : function(){ 
            console.log('Error in the AJAX call'); 
        },
        success: function(msg){  
        		location.reload();
                console.log(msg);
        }
	});
}

function saveReport(isEdit, bookMarkName) {

		// Element clicked animation
		// elementClicked('#btnCaptureBookmark');

		// Capture the report's current state
	report.bookmarksManager.capture().then(
				function(capturedBookmark) {
					/*var bookname = prompt("Please enter Bookmark name");
					if (bookname == null) {
						bookname = "";
					}*/
					var bookname
					if ( isEdit == 'N' && bookMarkName == 'N'){
						bookname = $("#bookname").val();
					}else{
						bookname = bookMarkName;
					}
					 
					if (bookname != "") {
						let bookmark = {
							name : bookname,
							displayname : bookname,
							state : capturedBookmark.state
						}

						// Add the new bookmark to the HTML list
						/*$('#bookmarksList').append(
								buildBookmarkElement(bookmark));*/

						// Set the captured bookmark as active
						// setBookmarkActive($('#bookmark_' +
						// BookmarkShowcaseState.nextBookmarkId));
						// setBookmarkActive(bookmark.name);
						// Add the bookmark to the bookmarks array and increase
						// the bookmarks number counter
								//report.bookmarksArray.push(bookmark);
								//report.nextBookmarkId++;
						let bookmarks = {
							name : capturedBookmark.name,
							displayName : bookmark.displayname,
							state : capturedBookmark.state,
							userId : $("#userName").val(),
							//reportId : "e2a8b141-40c2-436f-9ff8-d2cc1f8e7509"
						}
						$.ajax({
							type : "POST",
							url : "/saveReport",
							data : {
								'json' : JSON.stringify(bookmarks)
							},
//							contentType : "application/json; charset=utf-8",
							dataType : "json",
							success : function(data) {
								alert("Bookmark Saved Successfully");
							},
							failure : function() {
								//alert(response.responseText);
							},
							error : function(data) {
								alert("Error While Saving Bookmark");
							}
						});
					}

				});
	}