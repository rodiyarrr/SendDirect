import { useState, useEffect } from "react";

import FileDropZone
from "./components/FileDropZone";

import FileInfoCard
from "./components/FileInfoCard";

import useSocket
from "./hooks/useSocket";

import useSession
from "./hooks/useSession";


function App(){

const [selectedFile,setSelectedFile]
=
useState(null);

const [shareCode,setShareCode]
=
useState(null);



const {

connect

}
=
useSocket();



const {

createSession,
joinSession

}
=
useSession();



useEffect(

()=>{

    connect();

},

[]

);



async function handleCreateSession(){

    const data=
    await createSession();

    setShareCode(
        data.shareCode
    );

}



async function handleJoinSession(){

    const code=
    prompt(
        "Enter Share Code"
    );

    if(
        !code
    ){
        return;
    }


    await joinSession(
        code
    );


    setShareCode(
        code
    );

}



return(

<div
style={{

display:"flex",

justifyContent:"center",

alignItems:"center",

minHeight:"100vh",

background:"#0f172a",

color:"white"

}}
>

<div
style={{

width:"450px",

padding:"30px",

borderRadius:"20px",

background:"#1e293b",

textAlign:"center"

}}
>

<h1>

SendDirect ⚡

</h1>


<p>

Fast Peer-to-Peer File Sharing

</p>



<FileDropZone

setSelectedFile={
setSelectedFile
}

/>



<FileInfoCard

file={selectedFile}

/>



<button

onClick={
handleCreateSession
}

style={{

padding:"10px",

margin:"10px",

borderRadius:"10px",

border:"none",

cursor:"pointer"

}}

>

Send Files

</button>



<button

onClick={
handleJoinSession
}

style={{

padding:"10px",

margin:"10px",

borderRadius:"10px",

border:"none",

cursor:"pointer"

}}

>

Receive Files

</button>



{

shareCode &&

<p
style={{

marginTop:"20px",

fontWeight:"bold"

}}
>

Share Code:

{" "}

{shareCode}

</p>

}

</div>

</div>

);

}

export default App;