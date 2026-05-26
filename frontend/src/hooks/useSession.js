function useSession(){

async function createSession(){

const response=
await fetch(

"http://localhost:8080/api/session/create",

{
method:"POST"
}

);

return await response.json();

}


async function joinSession(

shareCode

){

const response=
await fetch(

"http://localhost:8080/api/session/join/"
+
shareCode

);

return await response.json();

}


return{

createSession,
joinSession

};

}

export default useSession;