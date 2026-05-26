import SockJS from "sockjs-client/dist/sockjs";
import Stomp from "stompjs";

import { useRef } from "react";

function useSocket(){

    const stompClient=
        useRef(null);


    function connect(){

        const socket=

        new SockJS(

            "http://localhost:8080/ws"

        );


        stompClient.current=

        Stomp.over(
            socket
        );


        stompClient.current.connect(

            {},

            ()=>{

                console.log(
                    "Connected"
                );

            },

            (error)=>{

                console.log(
                    error
                );

            }

        );

    }



    function subscribe(

        topic,

        callback

    ){

        stompClient.current.subscribe(

            topic,

            callback

        );

    }



    function sendMessage(

        destination,

        message

    ){

        stompClient.current.send(

            destination,

            {},

            JSON.stringify(
                message
            )

        );

    }


    return{

        connect,

        subscribe,

        sendMessage

    };

}


export default useSocket;