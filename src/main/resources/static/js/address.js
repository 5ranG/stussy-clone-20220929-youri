class AddressApi{
    static #instance = null;
    static getInstance(){
        if(this.#instance == null){
            this.#instance = new AddressApi();
        }
        return this.#instance;
    }

    constructor(){
        this.loadAddressApi();
    }
    #addressApi = null;

    loadAddressApi(){
        this.#addressApi = new daum.Postcode({ // 카카오 api
            oncomplete: function(data) {
                const addressZonecode = document.querySelector(".address-zonecode");
                const addressSido = document.querySelector(".address-sido");
                const addressSigungu = document.querySelector(".address-sigungu");
                const addressAuto = document.querySelector(".address-auto");

                addressZonecode.value = data.zonecode;
                addressSido.value = data.sido;
                addressSigungu.value = data.sigungu;
                addressAuto.value = data.addresss + (data.buildingName != "" ? "(" + data.buildingName + ")" : "");
            }
        });
        
    }
    addAddressButtonEvent(){
        const addressButton = document.querySelector(".address-button");
        addressButton.onclick = () => {
            this.#addressApi.open();
        }

    }    
}
