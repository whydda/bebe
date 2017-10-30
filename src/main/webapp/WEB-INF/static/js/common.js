/**
 * Created by whydda on 2017-10-30.
 * File Ko Name :
 */
/**
 * ajax 통신 공통 함수
 * @param url
 * @param data
 * @param callback
 * @param contentsType
 * @param methodType
 * @param formObj
 */
function comSendAjax(url, data, callback, methodType, contentsType, formObj){
    $('.wrap-loading').removeClass('display-none');
    // 1. Ajax 전송
    if($formCheck.isNullStr(formObj)){
        contentsType = $formCheck.isNullStr(contentsType) ? 'application/x-www-form-urlencoded' : contentsType;
        methodType = $formCheck.isNullStr(methodType) ? 'POST' : methodType;

        //타입을 체크해서 JSON 타입으로 만들어 준다.
        if(typeof data == "object"){
            data = {params : JSON.stringify(data)}
        }

        var options = {
            url : url,
            type : methodType,
            data : data,
            contentType : contentsType,
            success : function(data, msg, res){
                $('.wrap-loading').addClass('display-none');
                if(callback != null){
                    callback(data);
                } else{
                    return true;
                }
            },
            error : function(request,error){
                $('.wrap-loading').addClass('display-none');
                if(request.status == 0 || request.status == 200){
                    return false;
                } else if(request.status == 404){
                    if($formCheck.isNullStr(request.responseText)){
                        comShowPopup(1, "", "알 수 없는 요청입니다.");
                    }else{
                        comShowPopup(1, "", JSON.parse(request.responseText).message);
                    }
                } else if(request.status == 401){
                    if($formCheck.isNullStr(request.responseText)){
                        comShowPopup(1, "", "사용자 권한이 없습니다.");
                    }else{
                        comShowPopup(1, "", JSON.parse(request.responseText).message);
                    }
                } else if(request.status == 500){
                    if($formCheck.isNullStr(request.responseText)){
                        comShowPopup(1, "", "서버와 통신 중 오류가 발생하였습니다.</br>다시 시도해주십시오.");
                    }else{
                        comShowPopup(1, "", JSON.parse(request.responseText).message);
                    }
                } else if(error == 'timeout'){
                    if($formCheck.isNullStr(request.responseText)){
                        comShowPopup(1, "", "요청 시간이 초과되었습니다.</br>다시 시도해 주세요");
                    }else{
                        comShowPopup(1, "", JSON.parse(request.responseText).message);
                    }
                } else{
                    comShowPopup(1, "", "시스템오류가 발생하였습니다.</br>관리자에게 문의해 주십시오.");
                }

                $('.wrap-loading').addClass('display-none');
            }
        };
        $.ajax(options);

        // 2. AjaxForm 전송
    }else{
        var AjaxFrm = formObj;

        if(url != null){
            AjaxFrm.attr('action', url);
        }

        if(methodType != undefined || methodType != null){
            AjaxFrm.attr('method', methodType);
        }else{
            AjaxFrm.attr('method','POST');
        }

        if(callback != undefined || callback != null ){
            AjaxFrm.ajaxForm({
                success : function(data){
                    callback(data);
                },
                error : function(request,error){
                    if(request.status == 0 || request.status == 200){
                        return false;
                    } else if(request.status == 404){
                        if($formCheck.isNullStr(request.responseText)){
                            comShowPopup(1, "", "알 수 없는 요청입니다.");
                        }else{
                            comShowPopup(1, "", JSON.parse(request.responseText).message);
                        }
                    } else if(request.status == 401){
                        if($formCheck.isNullStr(request.responseText)){
                            comShowPopup(1, "", "사용자 권한이 없습니다.");
                        }else{
                            comShowPopup(1, "", JSON.parse(request.responseText).message);
                        }
                    } else if(request.status == 500){
                        if($formCheck.isNullStr(request.responseText)){
                            comShowPopup(1, "", "서버와 통신 중 오류가 발생하였습니다.</br>다시 시도해주십시오.");
                        }else{
                            comShowPopup(1, "", JSON.parse(request.responseText).message);
                        }
                    } else if(error == 'timeout'){
                        if($formCheck.isNullStr(request.responseText)){
                            comShowPopup(1, "", "요청 시간이 초과되었습니다.</br>다시 시도해 주세요");
                        }else{
                            comShowPopup(1, "", JSON.parse(request.responseText).message);
                        }
                    } else{
                        comShowPopup(1, "", "시스템오류가 발생하였습니다.</br>관리자에게 문의해 주십시오.");
                    }
                }
            });
        }
        AjaxFrm.submit();
    }
}
