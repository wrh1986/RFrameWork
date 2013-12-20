onmessage = handle;
function handle(event) {
	var result = event.data;
	for(var i = 0; i < 10000000; i ++) {
		result = result + '\n' + i;
	}
	postMessage("received:"+ event.data);
}