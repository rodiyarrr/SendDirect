function FileDropZone({

    setSelectedFile

}){

    function handleChange(event){

        const file=
            event.target.files[0];

        setSelectedFile(
            file
        );

    }


    function handleDrop(event){

        event.preventDefault();

        const file=
            event.dataTransfer.files[0];

        setSelectedFile(
            file
        );

    }


    function handleDragOver(event){

        event.preventDefault();

    }


    return(

        <div>

            <input

                type="file"

                id="fileInput"

                hidden

                onChange={
                    handleChange
                }

            />

            <div

                onClick={()=>{

                    document
                    .getElementById(
                        "fileInput"
                    )
                    .click();

                }}

                onDrop={
                    handleDrop
                }

                onDragOver={
                    handleDragOver
                }

                style={{

                    border:
                    "2px dashed #64748b",

                    padding:
                    "40px",

                    borderRadius:
                    "15px",

                    marginTop:
                    "20px",

                    cursor:
                    "pointer"

                }}

            >

                📁 Drag & Drop Files Here

                <br/><br/>

                <span>

                    or click to browse

                </span>

            </div>

        </div>

    );

}

export default FileDropZone;