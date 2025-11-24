/**
 * @function 获取min~max的随机数
 * @param min {number}
 * @param max {number}
 * @param decimal {number} 保留小数
 */
export function getRandomNumber(min, max, decimal) {
    return decimal ? (Math.random() * (max - min) + min).toFixed(decimal) * 1 : Math.floor(Math.random() * (max - min + 1) + min);
}

import { ref } from "vue";
import axios from "axios";
import dayjs from "dayjs"
import KDBush from 'kdbush';
import * as geokdbush from 'geokdbush';





// ant 控制未来时间不可选择
export const antDisabledDate = (current) => {
    return current && current > dayjs().endOf('day');
}
export const antDisabledDateTime = (current) => {
    let now = dayjs(),
        nowH = now.get("hour") + 1,
        nowM = now.get("minute") + 1,
        disabledHours = current.isBefore(now, "day") ? [] : Array(24 - nowH).fill(0).map(function (item, index) { return nowH + index; }),
        disabledMinutes = current.isBefore(now, "hour") ? [] : Array(60 - nowM).fill(0).map(function (item, index) { return nowM + index; });
    return {
        disabledHours: () => disabledHours,
        disabledMinutes: () => disabledMinutes,
    };
};
export const antDisabledDateTimeByHours = (current) => {
    let hours = [8, 20],
        disabledHours = Array(24).fill(0).map(function (item, index) { return index; }).filter(item => !hours.includes(item));
    return {
        disabledHours: () => disabledHours
    };
};


/**
 * @function 将点通过IDW插值到一个网格上
 * @param {Array} points    已知点的集合，格式为 [{lon: 经度, lat: 纬度, val: 值}]
 * @param {Object} grid     待插值的网格点，格式为 {startLon: 起始经度, startLat: 起始纬度, lonStep: 经度网格步长, latStep: 纬度网格步长, lonCount: 经度网格数, latCount: 纬度网格数}
 * @param {Number} radius   搜索半径，默认Infinity，单位公里，二选一
 * @param {Number} number   搜索数量，默认Infinity，二选一
 * @param {Object} config   配置 {
 *      decimal : 值保留小数位，默认1
 *      power : 距离的幂次，默认2
 *      def : 无效值，默认0
 *      lonField : points中经度属性，默认lon
 *      latField : points中纬度属性，默认lat
 *      valField : points中值属性，默认val
 * }
 * @returns {Object} 
 */
export const idwToGrid = function (points, grid, radius = Infinity, number = Infinity, config = {}) {
    let decimal = config.decimal || 1,
        power = config.power || 2,
        def = config.def || 0,
        lonField = config.lonField || "lon",
        latField = config.latField || "lat",
        valField = config.valField || "val";
    let startLon = grid.startLon,
        startLat = grid.startLat,
        lonStep = grid.lonStep,
        latStep = grid.latStep,
        lonCount = grid.lonCount,
        latCount = grid.latCount,
        DS = Array.from({ length: latCount }, () => Array(lonCount).fill(def));
    // 创建树
    let kdb = new KDBush(points.length);
    for (const { lon, lat } of points) {
        kdb.add(lon, lat);
    }
    kdb.finish();
    for (let y = 0; y < latCount; y++) {
        for (let x = 0; x < lonCount; x++) {
            let glon = startLon + lonStep * (x + 0.5),
                glat = startLat + latStep * (y + 0.5);
            // 找出网格在树中的近点。nearestIds 按照距离从近到远排序
            let nearestIds = geokdbush.around(kdb, glon, glat, number, radius);
            let nearest = nearestIds.map(id => points[id]);
            // 如果存在近点，则进行插值；如果不存在，则网格值直接使用 def 值
            if (nearest.length > 0) {
                let nominator = 0;
                let denominator = 0;
                for (let i = 0; i < nearest.length; i++) {
                    let point = nearest[i];
                    let dist = geokdbush.distance(glon, glat, point[lonField], point[latField]);
                    // 如果第一个点的距离为 0，则网格值直接使用 point[valField] 值
                    if (i == 0 && dist == 0) {
                        DS[y][x] = point[valField].toFixed(decimal) * 1;
                        break;
                    }
                    let weight = 1 / Math.pow(dist, power); // 权重
                    nominator += weight * point[valField];  // 加权后的值
                    denominator += weight;                  // 权重和                    
                }
                if (denominator != 0) {
                    DS[y][x] = (nominator / denominator).toFixed(decimal) * 1;
                }
            }
        }
    }
    return {
        startLon: grid.startLon,
        startLat: grid.startLat,
        lonStep: grid.lonStep,
        latStep: grid.latStep,
        lonCount: grid.lonCount,
        latCount: grid.latCount,
        DS: DS,
    }
}
// export const idwToPoint = function (points, grid, radius = Infinity, number = Infinity, config = {}) {
//     let decimal = config.decimal || 1,
//         power = config.power || 2,
//         def = config.def || 0,
//         lonField = config.lonField || "lon",
//         latField = config.latField || "lat",
//         valField = config.valField || "val";
//     // let DS = grid.map(v=);Array.from({ length: latCount }, () => Array(lonCount).fill(def));
//     // 创建树
//     let kdb = new KDBush(points.length);
//     for (const { lon, lat } of points) {
//         kdb.add(lon, lat);
//     }
//     kdb.finish();
//     for (let y = 0; y < grid.length; y++) {
//         let item = grid[y];
//         let glon = startLon + lonStep * (x + 0.5),
//             glat = startLat + latStep * (y + 0.5),
//             gval = def;
//         // 找出网格在树中的近点。nearestIds 按照距离从近到远排序
//         let nearestIds = geokdbush.around(kdb, item, glat, number, radius);
//         let nearest = nearestIds.map(id => points[id]);
//         // 如果存在近点，则进行插值；如果不存在，则网格值直接使用 def 值
//         if (nearest.length > 0) {
//             let nominator = 0;
//             let denominator = 0;
//             for (let i = 0; i < nearest.length; i++) {
//                 let point = nearest[i];
//                 let dist = geokdbush.distance(glon, glat, point[lonField], point[latField]);
//                 // 如果第一个点的距离为 0，则网格值直接使用 point[valField] 值
//                 if (i == 0 && dist == 0) {
//                     gval = point[valField].toFixed(decimal) * 1;
//                     break;
//                 }
//                 let weight = 1 / Math.pow(dist, power); // 权重
//                 nominator += weight * point[valField];  // 加权后的值
//                 denominator += weight;                  // 权重和                    
//             }
//             if (denominator != 0) {
//                 gval = (nominator / denominator).toFixed(decimal) * 1;
//             }
//         }
//         item.val = gval;
//     }
//     return grid;
// }



// ---------- 导出文件 ----------
export const exportFile = function (data, name, type) {
    var a = document.createElement('a'),
        t = {
            txt: 'text/plain',
            csv: 'text/csv',
            xls: 'application/vnd.ms-excel',
        }[type],
        s = '';
    if (type == "xls" || type == "csv") {
        for (const w1 of data) { s += w1.join(',') + '\r\n' }
    } else if (type == "txt") {
        for (const w1 of data) { s += w1.join('\t') + '\r\n' }
    }
    a.href = 'data:' + t + ';charset=utf-8,\ufeff' + encodeURIComponent(s);
    a.download = name + '.' + type;
    a.click();
}

// 雷达颜色
export const getRadarColor = function (value) {
    return value < 5 ? "rgb(255,255,255)" :
        value < 10 ? "rgb(64,157,241)" :
            value < 15 ? "rgb(100,231,235)" :
                value < 20 ? "rgb(109,250,61)" :
                    value < 25 ? "rgb(0,216,0)" :
                        value < 30 ? "rgb(0,144,0)" :
                            value < 35 ? "rgb(255,255,0)" :
                                value < 40 ? "rgb(231,192,0)" :
                                    value < 45 ? "rgb(255,145,0)" :
                                        value < 50 ? "rgb(255,0,0)" :
                                            value < 55 ? "rgb(214,0,1)" :
                                                value < 60 ? "rgb(192,0,0)" :
                                                    value < 65 ? "rgb(255,0,240)" :
                                                        value < 70 ? "rgb(150,1,181)" : "rgb(173,144,240)";
}
export const getRadarArrayColor = function (value) {
    return value < 5 ? [255, 255, 255, 1] :
        value < 10 ? [64, 157, 241, 1] :
            value < 15 ? [100, 231, 235, 1] :
                value < 20 ? [109, 250, 61, 1] :
                    value < 25 ? [0, 216, 0, 1] :
                        value < 30 ? [0, 144, 0, 1] :
                            value < 35 ? [255, 255, 0, 1] :
                                value < 40 ? [231, 192, 0, 1] :
                                    value < 45 ? [255, 145, 0, 1] :
                                        value < 50 ? [255, 0, 0, 1] :
                                            value < 55 ? [214, 0, 1, 1] :
                                                value < 60 ? [192, 0, 0, 1] :
                                                    value < 65 ? [255, 0, 240, 1] :
                                                        value < 70 ? [150, 1, 181, 1] : [173, 144, 240, 1];
}
// 降水颜色
export const getPrecipitationArrayColor24 = function (value) {
    return value < 0.1 ? [255, 255, 255, 1] :
        value < 10 ? [163, 242, 143, 1] :
            value < 25 ? [61, 186, 61, 1] :
                value < 50 ? [97, 184, 255, 1] :
                    value < 100 ? [5, 0, 225, 1] :
                        value < 250 ? [250, 0, 250, 1] : [128, 0, 64, 1];
}
export const getPrecipitationArrayColor1 = function (value) {
    return value < 0.1 ? [255, 255, 255, 1] :
        value < 1.6 ? [163, 242, 143, 1] :
            value < 7 ? [61, 186, 61, 1] :
                value < 15 ? [97, 184, 255, 1] :
                    value < 40 ? [5, 0, 225, 1] :
                        value < 50 ? [250, 0, 250, 1] : [128, 0, 64, 1];
}
export const getPrecipitationColor24 = function (value) {
    return value < 0.1 ? "#ffffff" :
        value < 10 ? "#a3f28f" :
            value < 25 ? "#3dba3d" :
                value < 50 ? "#61b8ff" :
                    value < 100 ? "#0500e1" :
                        value < 250 ? "#fa00fa" : "#800040";
}
export const getPrecipitationColor1 = function (value) {
    return value < 0.1 ? "#ffffff" :
        value < 1.6 ? "#a3f28f" :
            value < 7 ? "#3dba3d" :
                value < 15 ? "#61b8ff" :
                    value < 40 ? "#0500e1" :
                        value < 50 ? "#fa00fa" : "#800040";
}



