class ImportApi{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null){
            this.#instance = new ImportApi();
        }
        return this.#instance;
    }

    IMP = null;
    
    importPayPrarams = {
        pg: "kakaopay",
        pay_method: "card",
        merchant_uid: "product-" + new Date().getTime(),
        name: "노르웨이 회전 의자",
        amount: 1,
        buyer_email: "gildong@gmail.com",
        buyer_name: "홍길동",
        buyer_tel: "010-4242-4242",
        buyer_addr: "서울특별시 강남구 신사동",
        buyer_postcode: "01181"
    };

    impInfo = {
        impUid : null,
        restApiKey: null,
        restApiSecret: null
    }

    constructor(){
        this.IMP = window.IMP; // 생략 가능
        this.impInfo.impUid = "imp04038076";
        this.impInfo.restApiKey = "4650674113508888";
        this.impInfo.restApiSecret = "hEMbPN0UyyZGXXoMyKoAwUqaAcfkM3DELmuaG1LKcqtKCJ8GxglF1kpi6LWL0giwkRiNxLqVB09VpV1i"
   
        this.IMP.init(this.impInfo.impUid); // 예: imp00000000
    }

    requestPay(){
        this.IMP.request_pay(this.importPayPrarams, this.responsePay);
    }

    reponsePay(resp){
        if(resp.success){
            alert("결제 성공!");
            this.requestPayDetails();
        }else{
            alert("결제 실패!");
        }
    }

    requestImpAccessToken(){
        const accessToken = null;

        $.ajax({
            async: false,
            type: "post",
            url: "https://api.iamport.kr/users/getToken",
            contentType: "application/json",
            data:JSON.stringify({
                imp_key: this.imptnfo.restApiKey,
                imp_secret: this.impInfo.restApiSecret
            }),
            dataType: "json",
            success: (response) => {
                accessToken = response;
            },
            error: (error) => {
                console.log(error);
            }
        });
        return accessToken;
    }

    requestPayDetails(){
        const accessToken = this.requestImpAccessToken();
        console.log(accessToken);
    }
}

class Order{
    constructor(){
        this.addPaymentButtonEvent();
    }

    addPaymentButtonEvent(){
        const paymentButton = document.querySelector(".payment-button");
        paymentButton.onclick = () => {
            ImportApi.getInstance().requestPay();
        }
    }
}

window.onload = () => {
    AddressApi.getInstance().addAddressButtonEvent();
    new Order();
}