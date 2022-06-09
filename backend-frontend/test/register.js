const axios = require('axios')

for (let n = 11; n <= 100; n++) {
    let registerInfo = {
        userName:`test${n}`,
        password:"123",
        address:"Test Address"
    }
    axios.post('http://localhost:8080/GFTB/api/user', registerInfo)
        .then(res => {
            console.log(res.data.msg);
        })
}