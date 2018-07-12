<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>GetUserMedia</title>
</head>
<body>
    <video id="video" autoplay></video>
    <div id = "eee">
    <video id="video_two" autoplay></video>
    </div>
    <input id="text" type="text"/>
    <button onclick="send()">发送消息</button>
     <div id="message"></div>
    
</body>


<script type="text/javascript">


    
//使用Google的stun服务器
var iceServer = {
    "iceServers": [{
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
var PeerConnection = window.webkitRTCPeerConnection || window.webkitPeerConnection00 || window.PeerConnection || window.mozRTCPeerConnection;
//与后台服务器的WebSocket连接
var socket = new WebSocket("ws://192.168.5.107:8484/websocket");

//创建PeerConnection实例
var pc = new PeerConnection(iceServer);
            
//发送ICE候选到其他客户端
/*
pc.onicecandidate = function(event){
if(event.candidate !== null && event.candidate !== undefined && event.candidate !== '') { 
    socket.send(JSON.stringify({
        "event": "__ice_candidate",
        "data": {
            "candidate": event.candidate
        },
        "type": "1"
    }));
    }
};
*/
//如果检测到媒体流连接到本地，将其绑定到一个video标签上输出
pc.onaddstream = function(event){

        var video_two = document.getElementById('video_two');

        video_two.src = URL.createObjectURL(event.stream);

};

var i =1;


	
	
//处理到来的信令
socket.onmessage = function (event) {
    var json = JSON.parse(event.data);
    
    //如果是一个ICE的候选，则将其加入到PeerConnection中，否则设定对方的session描述为传递过来的描述
    if( json.event === "__ice_candidate" ){
        pc.addIceCandidate(new RTCIceCandidate(json.data.candidate));
    } else if(json.event === "__offer") {
   
	
     if(i == 1){
         pc.setRemoteDescription(new RTCSessionDescription(json.data.sdp));
         
         
         	pc.onicecandidate = function(event){
	if(event.candidate !== null && event.candidate !== undefined && event.candidate !== '') { 
    	socket.send(JSON.stringify({
        	"event": "__ice_candidate",
        	"data": {
            "candidate": event.candidate
        },
        "id":json.id,
        "type": "1"
    	}));
    	}
	};
	
         
         
           var agent = navigator.userAgent.toLowerCase();
        if(agent.indexOf("firefox") > 0)
		{
			pc.createAnswer().then(function(desc){
            pc.setLocalDescription(desc);
            socket.send(JSON.stringify({ 
                "event": "__answer",
                "data": {
                    "sdp": desc
                },
                "id":json.id,
                "type": "1"
            }));
        });
    
		}else
		{
		    pc.createAnswer(function(desc){
            pc.setLocalDescription(desc);
            socket.send(JSON.stringify({ 
                "event": "__answer",
                "data": {
                    "sdp": desc
                },
                "id":json.id,
                "type": "1"
            }));
        },function(eorr){
        alert(eorr);
        });
			
		}
		i++;
		}

    } else{
    	document.getElementById('message').innerHTML += json.data + '<br/>';
    }
};


    //发送消息
    function send() {
        var message = document.getElementById('text').value;
       socket.send(JSON.stringify({ 
                "event": "0",
                "data": message,
                "type": "1"
            }));
    }
</script>


</html>