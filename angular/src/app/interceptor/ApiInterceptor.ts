import { HttpInterceptorFn } from "@angular/common/http";

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
    const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
    console.log(csrfToken)


    let newReq;

    if(csrfToken) {
        newReq = req.clone({
            withCredentials: true,
            headers: req.headers.set("X-XSRF-TOKEN", csrfToken)
        })
    } else {
        newReq = req.clone({
            withCredentials: true,
        })        
    }
    
    return next(newReq)
};