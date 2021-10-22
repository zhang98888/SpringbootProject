import request from "../utils/requst";

const URL ={
    captcha: '/captcha'
}

export function getCaptcha(){
    return request({
        url: URL.captcha,
        method: 'get',
    })
}