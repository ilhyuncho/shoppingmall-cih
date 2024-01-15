async function uploadToServer(formObj){
    // 파일 업로드
    console.log("upload to server")
    console.log(formObj)

    const response = await axios({
        method:'post',
        url:'/upload',
        data:formObj,
        headers:{
            'Content-Type':'multipart/form-data',
        },
    });
    return response.data
}

async function removeFileToServer(uuid, fileName){
    const response = await axios.delete(`/remove/${uuid}_${fileName}`)

    return response.data
}