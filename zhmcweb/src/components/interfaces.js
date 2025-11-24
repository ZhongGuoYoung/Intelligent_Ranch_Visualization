const interfaces = import.meta.env.DEV
    ? {
        serverUrl: "http://localhost:5173/zhmc/public/",    // 服务器接口
        serviceUrl: "http://localhost:8081/",               // 后端服务接口

    }
    : {
        serverUrl: "http://192.168.2.3:8080/wusu/",
        serviceUrl: "http://192.168.2.3:8081/",
        mapUrl: "http://192.168.2.3:8080/",
    }
export default interfaces