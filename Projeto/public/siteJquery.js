$(main);


function main() {
    var user;
    var imagepath;
    var htmlImage;


	// --- PAGE CREATE ACCOUNT ---

    // validate all the fields
	$("#CAsubmitBtn").click(function() {
		validateCA();
	});

    // go back
	$("#CAcancelBtn").click(function() {
    	window.location.replace("login.html");
	});


	// --- PAGE LOGIN ---

    // validate all the fields
	$("#LOGsignBtn").click(function() {
    	validateLOG();
	});
}



function validateCA() {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	// --- NAME ---
	if($("#CAname").val().length == 0) {
        $("#CAname").css("border","5px solid red");
        $("#CAname").attr("placeholder", "REQUIRED FIELD");
    }

    // --- EMAIL ---
	else if($("#CAmail").val().length == 0) {
		// reset do anterior
        $("#CAname").css("border","1px solid black");

        $("#CAmail").css("border","5px solid red");
        $("#CAmail").attr("placeholder", "REQUIRED FIELD");
    }

    else if (regex.test($("#CAmail").val()) == false) {
    	$("#CAmail").css("border","5px solid red");
    	alert("WARNING: Malformed email!");
    }

    // --- PASS ---
	else if($("#CApass").val().length == 0) {
		// reset do anterior
        $("#CAmail").css("border","1px solid black");

        $("#CApass").css("border","5px solid red");
        $("#CApass").attr("placeholder", "REQUIRED FIELD");
    }

    // --- PASS2 ---
	else if($("#CApass2").val() != $("#CApass").val()) {
		$("#CApass").css("border","5px solid red");
        $("#CApass2").css("border","5px solid red");

        $("#CApass").attr("placeholder", "DIFFERENT PASSWORDS");
        $("#CApass2").attr("placeholder", "DIFFERENT PASSWORDS");
    }

    // -- ALL OK ---
    else {
    	window.location.replace("login.html");
    }
}


function validateLOG() {
	// --- USER ---
	if($("#LOGuser").val().length == 0) {
        $("#LOGuser").css("border","5px solid red");
        $("#LOGuser").attr("placeholder", "REQUIRED FIELD");
    }

	// --- PASS ---
	else if($("#LOGpass").val().length == 0) {
        $("#LOGpass").css("border","5px solid red");
        $("#LOGpass").attr("placeholder", "REQUIRED FIELD");
    }
}



// --- PAGE DASHBOARD ---

function showAll (id1, id2, id3) {
    var div1 = document.getElementById(id1);
    var div2 = document.getElementById(id2);
    var div3 = document.getElementById(id3);

    div1.style.display = 'block';
    div2.style.display = 'block';
    div3.style.display = 'block';
}


function toggleContent(elm) {
    var contentContainer = elm.parentNode.getElementsByTagName("div")[1];
    contentContainer.style.display = (contentContainer.style.display == 'block') ? 'none' : 'block';
}


// --- PAGE HISTORY ---

function hideAll (id1, id2, id3) {
    var div1 = document.getElementById(id1);
    var div2 = document.getElementById(id2);
    var div3 = document.getElementById(id3);

    div1.style.display = 'none';
    div2.style.display = 'none';
    div3.style.display = 'none';
}


function toggleContChart(elm) {
    var contentContainer = elm.parentNode.getElementsByTagName("div")[1];
    contentContainer.style.display = (contentContainer.style.display == 'block') ? 'none' : 'block';
    drawCharts();
}



// --------- ADMIN ---------

function goEditUser(mail) {
    localStorage.setItem("userPesMail", mail);
    window.location.replace('admin_user_data.html');
}

function getUserPesMail() {
    mail = localStorage.getItem("userPesMail");
    $("#userMail").html(mail);
}

function clearUserPesMail() {
    localStorage.removeItem("userPesMail");
    window.location.replace('admin_edit_users.html');
}
