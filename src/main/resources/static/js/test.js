const user = {
    username: "gildong",
    printUsername: function() {
        console.log("printUsername: " + this.username);
        // this는 바로위의 익명을 참고
        // () => 화살표함수는 자기가 호출한 시점의 전역함수가됨
        const testPrint = () => {
            console.log("testPrint: " + this.username);
        }
        testPrint();
    }
}

var username = "test";
//let username = "test"; // let은 지역함수라서 참조 X
user.printUsername();