function FileInfoCard({file}){

    if(!file){
        return null;
    }

    return(

        <div
            style={{

                marginTop:"20px",

                padding:"15px",

                borderRadius:"10px",

                background:"#334155"

            }}
        >

            <p>

                📄 {file.name}

            </p>

            <p>

                Size:

                {" "}

                {(file.size/1024/1024)
                .toFixed(2)}

                MB

            </p>

        </div>

    );

}

export default FileInfoCard;