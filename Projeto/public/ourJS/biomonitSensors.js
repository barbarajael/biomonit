// Variables
var client = null;
var hostname = "localhost";
var port = "8000";
var clientId = "00-00-00-00-00-00-00-E0-biomonit";
var heartrateTopic = "biomonit/health/heartrate";
var brightnessTopic = "biomonit/home/brightness";
var temperatureTopic = "biomonit/home/temperature";


// Called after the webpage (biomonitSensors.html) is completely loaded
function connect()
{
	// Set up the client
	client = new Paho.MQTT.Client(hostname, Number(port), clientId);
	console.info('Connecting to Server: Hostname: ', hostname, '. Port: ', port, '. Client ID: ', clientId);
	
	// set callback handlers
	client.onConnectionLost = onConnectionLost;
	client.onMessageArrived = onMessageArrived;

	// see client class docs for all the options
	var options = 
	{
		onSuccess: onConnect, // after connected, subscribes
		onFailure: onFail     // useful for logging / debugging
	};
	
	// connect the client
	client.connect(options);
	console.info('Connecting...');
}

function onConnect(context) 
{
	console.log("Client Connected");
    
    // And subscribe to the topics
	options = {qos:0, onSuccess:function(context){ console.log("subscribed"); } }
	client.subscribe(temperatureTopic, options);
	client.subscribe(heartrateTopic, options);
	client.subscribe(brightnessTopic, options);
}

function onFail(context) 
{
	console.log("Failed to connect");
}

function onConnectionLost(responseObject) 
{
	console.log("Connection Lost: " + responseObject.errorMessage);
	connect(); // Connect again	
}

function onMessageArrived(message) 
{	
	console.log(message.destinationName, message.payloadString);

	// Update element depending on which topic's data came in
	if (message.destinationName == heartrateTopic)
	{ 
		var heartrateTopic_heading = document.getElementById("heartrateTopic_display");
		heartrateTopic_heading.innerHTML = "Current measurement: " + message.payloadString;
	} 
	else if (message.destinationName == brightnessTopic) 
	{
		var brightnessTopic_heading = document.getElementById("brightnessTopic_display");
		brightnessTopic_heading.innerHTML = "Current measurement: " + message.payloadString;
	} 
	else if (message.destinationName == temperatureTopic) 
	{
		var temperatureTopic_heading = document.getElementById("temperatureTopic_display");
		temperatureTopic_heading.innerHTML = "Current measurement: " + message.payloadString;
	}
}
