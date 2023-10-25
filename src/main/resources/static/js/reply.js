// Axios를 이용하는 코드
// Axios를 이용할때 async/await를 같이 이용하면
// 비동기 처리를 동기화된  코드처럼 작성 할수 있다.


//async : 해당 함수가 비동기 처리를 위한 함수 라는 표시
//await : async 함수 내에서 비동기 호출하는 부분
async function get1(bno){
    const result = await axios.get(`/replies/list/${bno}`)

    // console.log(result)

    return result.data
}

// 댓글 목록 처리
async function getList({bno, page, size, goLast}){
    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})

    console.log(result.data)

    if(goLast){ // 마지막 페이지로 이동 처리
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))

        return getList({bno:bno, page:lastPage, size:size})
    }

    return result.data
}

// 댓글 등록
async function addReply(replyObj){
    const response = await axios.post(`/replies/`, replyObj)
    // return 값 : {'rno':11}
    return response.data
}
