const getList = async (bno, page, size, goLast) => {

    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}});

    if(goLast) {
        const total = result.data.total;
        const lastPage = parseInt(Math.ceil(total/size));
        return getList(bno, lastPage, size);
    }

    return result.data;
}

const addReply = async (replyObj) => {
    const response = await axios.post(`/replies/`, replyObj);
    return response.data;
}

const getReply = async (rno) => {
    const response = await axios.get(`/replies/${rno}`);
    return response.data;
}

const modifyReply = async (replyObj) => {
    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj);
    return response.data;
}

const removeReply = async (rno) => {
    const response = await axios.delete(`/replies/${rno}`);
    return response.data;
}