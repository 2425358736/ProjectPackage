<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>GetUserMedia</title>
</head>
<body>
    <video id="video" autoplay></video>
    
    <video id="video_two" autoplay></video>
    
    <input id="text" type="text"/>
    <button onclick="send()">发送消息</button>
     <div id="message"></div>
    
</body>


<script type="text/javascript">



    
//使用Google的stun服务器
var iceServer = {
    "iceServers": [ {
        "url": "stun:stun.l.google.com:19302"
    },{
                "url": "turn:numb.viagenie.ca",
                "username": "webrtc@live.com",
                "credential": "muazkh"
            }]
};
//兼容浏览器的getUserMedia写法
var getUserMedia = navigator.getUserMedia || navigator.mozGetUserMedia ||  navigator.webkitGetUserMedia || navigator.msGetUserMedia;
//兼容浏览器的PeerConnection写法
var PeerConnection = window.webkitRTCPeerConnection ||
                    window.PeerConnection ||
                    window.webkitPeerConnection00 ||
                    window.RTCPeerConnection ||
                    window.mozRTCPeerConnection;
//与后台服务器的WebSocket连接
var socket = new WebSocket("ws://192.168.5.107:8484/websocket");

var stream_two;

//获取本地的媒体流，并绑定到一个video标签上输出，并且发送这个媒体流给其他客户端
getUserMedia.call(navigator, {
    "audio": true,
    "video": true
}, function(stream){
    //发送offer和answer的函数，发送本地session描述
 		stream_two = stream;
        var video = document.getElementById('video');
        video.src = URL.createObjectURL(stream);
            socket.send(JSON.stringify({
                            "event": 1,
                            "data": "我是主播",
                            "type": "0"
                        }));
    //向PeerConnection中加入需要发送的流
    //如果是发送方则发送一个offer信令，否则发送一个answer信令
}, function(error){
    //处理媒体流创建失败错误
    alert("处理媒体流创建失败错误");
});


var pc =  {};
socket.onmessage = function (event) {
//有人进来
var json = JSON.parse(event.data);
	if(json.event == "1"){
	pc[json.id] = new PeerConnection(iceServer);
	pc[json.id].onicecandidate = function(event){
	if(event.candidate !== null && event.candidate !== undefined && event.candidate !== '') { 
    	socket.send(JSON.stringify({
        	"event": "__ice_candidate",
        	"data": {
            "candidate": event.candidate
        },
        "id":json.id,
        "type": "0"
    	}));
    	}
	};

	if (typeof(stream_two) != 'undefined') {
	    pc[json.id].addStream(stream_two);
	}

    var agent = navigator.userAgent.toLowerCase();
		pc[json.id].createOffer().then(function(desc){
            pc[json.id].setLocalDescription(desc);
            socket.send(JSON.stringify({ 
                "event": "__offer",
                "data": {
                    "sdp": desc
                },
                "id":json.id,
                "type": "0"
            }));
        });
	}else{

    
    //如果是一个ICE的候选，则将其加入到PeerConnection中，否则设定对方的session描述为传递过来的描述
    if( json.event === "__ice_candidate" ){
    	
        pc[json.id].addIceCandidate(new RTCIceCandidate(json.data.candidate));
        
    }else if(json.event === "__answer") {
         pc[json.id].setRemoteDescription(new RTCSessionDescription(json.data.sdp));
    }else{
    	document.getElementById('message').innerHTML += json.data + '<br/>';
    }
    }
};
    //发送消息
    function send() {
        var message = document.getElementById('text').value;
       socket.send(JSON.stringify({ 
                "event": "0",
                "data": message,
                "type": "0"
            }));
    }

</script>


</html>