import * as turf from "@turf/turf";
import BianJieFive from "@/assets/GeographicData/石家庄五区边界.json";
import BianJieMax from "@/assets/GeographicData/石家庄五区整体边界.json";

export const previewImage = function (obj) {
    var props = Object.assign(
        {},
        {
            type: "",
            title: "",
            data: {},
            legend: {
                width: 60,
                height: 24,
                color: [],
                level: [],
            },
            // 114.36 114.72 37
            // 37.94 38.18 25
            info: {},
            onePx: 0.0005,
            xAxis: [114.36, 114.71], // 第一个和最后一个不画在坐标轴上
            yAxis: [37.94, 38.17], // 第一个和最后一个不画在坐标轴上
            padding: [80, 220, 80, 80],
            scissors: false,
        },
        obj
    );

    let type = props.type,
        onePx = props.onePx,
        xAxis = props.xAxis,
        yAxis = props.yAxis,
        padding = props.padding,
        title = props.title;

    let width = Math.ceil((xAxis[xAxis.length - 1] - xAxis[0]) / onePx),
        height = Math.ceil((yAxis[yAxis.length - 1] - yAxis[0]) / onePx),
        canvas = document.createElement("canvas"),
        ctx = canvas.getContext("2d");

    canvas.width = padding[1] + padding[3] + width;
    canvas.height = padding[0] + padding[2] + height;
    console.log("预览图尺寸", canvas.width, canvas.height);
    ctx.fillStyle = "#fff";
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // 设置标题
    ctx.fillStyle = "#000";
    ctx.font = "bold 23px 宋体";
    ctx.textAlign = "center";
    ctx.textBaseline = "bottom";
    ctx.fillText(title, canvas.width / 2, padding[0] - 20);

    // 设置制图单位
    ctx.font = "16px 宋体";
    ctx.textAlign = "left";
    ctx.textBaseline = "top";
    ctx.fillText("制图单位：石家庄市气象局", padding[3], padding[0] + height + 20);

    // drawCoordinateAxis(props, ctx);

    if (type == "station") {
        drawGeojson(props, ctx, BianJieFive);
        drawStation(props, ctx);
    } else if (type == "grid") {
        drawLegend(props, ctx, width, height);
        drawGridColor(props, ctx);
        drawGeojson(props, ctx, BianJieFive);
        // drawName(props, ctx);
    } else if (type == "geojson") {
        drawLegend(props, ctx, width, height);
        drawGeojson(props, ctx);
        drawGeojson(props, ctx, BianJieFive);
    }
    else if (type == "road") {
        drawLegend(props, ctx, canvas.height);
        drawGeojson(props, ctx, BianJieFive);
        drawGeojson(props, ctx, props.data);
        // drawName(props, ctx);
    } else if (type == "jiangsStation") {
        drawGeojson(props, ctx, BianJieFive);
        // drawName(props, ctx);
        drawJiangsStation(props, ctx);
    }

    return canvas.toDataURL();
};

// 画站点数据
function drawStation(props, ctx) {
    let data = props.data,
        xAxis = props.xAxis,
        yAxis = props.yAxis,
        onePx = props.onePx,
        padding = props.padding,
        info = props.info;

    // 设置填充颜色为红色用于绘制圆形
    ctx.fillStyle = "red";
    ctx.textAlign = "center";
    ctx.textBaseline = "bottom";
    ctx.font = "bold 18px 宋体";

    // 遍历数据并绘制圆形和文本
    data.forEach((e) => {
        // 设置圆点
        ctx.fillStyle = "red";
        ctx.beginPath();
        ctx.arc(
            padding[3] + (e.lon - xAxis[0]) / onePx,
            padding[0] + (yAxis[yAxis.length - 1] - e.lat) / onePx,
            3.2,
            0,
            2 * Math.PI
        );
        ctx.closePath();
        ctx.fill();

        // 设置文本颜色为黑色（或其他所需颜色）
        ctx.fillStyle = "black";
        ctx.fillText(
            e[info.key],
            padding[3] + (e.lon - xAxis[0]) / onePx,
            padding[0] + (yAxis[yAxis.length - 1] - e.lat) / onePx - 5
        );
    });
}

function drawJiangsStation(props, ctx) {
    let data = props.data,
        xAxis = props.xAxis,
        yAxis = props.yAxis,
        onePx = props.onePx,
        padding = props.padding;
    ctx.fillStyle = "red";
    ctx.textAlign = "center";
    ctx.textBaseline = "bottom";
    ctx.font = "bold 21px 宋体";
    data.forEach((e) => {
        // const textXOffset = 10;
        // const textYOffset = -10;
        ctx.beginPath();
        ctx.arc(
            padding[3] + (e.lon - xAxis[0]) / onePx,
            padding[0] + (yAxis[yAxis.length - 1] - e.lat) / onePx,
            3.2,
            0,
            2 * Math.PI
        );
        ctx.closePath();
        ctx.fill();
        ctx.fillStyle = "black";
        // ctx.fillText(e.totalRainfall, padding[3] + (e.lon - xAxis[0]) / onePx + textXOffset, padding[0] + (yAxis[yAxis.length - 1] - e.lat) / onePx + textYOffset);
        ctx.fillText(
            e.totalRainfall,
            padding[3] + (e.lon - xAxis[0]) / onePx,
            padding[0] + (yAxis[yAxis.length - 1] - e.lat) / onePx
        );
        ctx.fillStyle = "red";
    });
}

function drawGridColor(props, ctx) {
    let data = props.data;
    let ds = data.DS,
        startLon = data.startLon,
        lonStep = data.lonStep,
        startLat = data.startLat,
        latStep = data.latStep,
        lonCount = ds[0].length,
        latCount = ds.length;

    let xAxis = props.xAxis,
        yAxis = props.yAxis,
        onePx = props.onePx,
        padding = props.padding,
        originLon = xAxis[0], // 坐标轴原点经纬度，左上角为原点
        originLat = yAxis[yAxis.length - 1];
    // 一个步长有多少个像素
    let lonStepPx = Math.round(Math.abs(lonStep) / onePx);
    let latStepPx = Math.round(Math.abs(latStep) / onePx);
    let feature = turf.feature(BianJieMax.features[0].geometry);
    for (let y = 0; y < latCount; y++) {
        for (let x = 0; x < lonCount; x++) {
            let dataStartX = Math.round((startLon + lonStep * x - originLon) / onePx);
            let dataStartY = Math.round((originLat - (startLat + latStep * (y + (latStep > 0 ? 1 : 0)))) / onePx);
            ctx.fillStyle = props.color(ds[y][x]);
            for (let gy = 0; gy < latStepPx; gy++) {
                for (let gx = 0; gx < lonStepPx; gx++) {
                    if (props.scissors) {
                        let glon = startLon + lonStep * x + gx * onePx;
                        let glat = startLat + latStep * (y + (latStep > 0 ? 1 : 0)) - gy * onePx;
                        let point = turf.point([glon, glat]);
                        if (turf.booleanPointInPolygon(point, feature)) {
                            ctx.fillRect(padding[3] + dataStartX + gx, padding[0] + dataStartY + gy, 1, 1);
                        }
                    } else {
                        ctx.fillRect(padding[3] + dataStartX + gx, padding[0] + dataStartY + gy, 1, 1);
                    }
                }
            }
        }
    }
}
// 画 geojson
function drawGeojson(props, ctx, geojson) {
    let xAxis = props.xAxis,
        yAxis = props.yAxis,
        onePx = props.onePx,
        padding = props.padding;
    let originLon = xAxis[0], // 坐标轴原点（左上角）经纬度，
        originLat = yAxis[yAxis.length - 1];
    let geo = geojson ? geojson : props.data;

    geo.features.forEach((feature) => {
        let coordinates = feature.geometry.coordinates,
            type = feature.geometry.type,
            properties = feature.properties;
        ctx.strokeStyle = properties.color || "orange";

        if (type === "Polygon" || type === "MultiLineString") {
            coordinates.forEach((e1) => {
                ctx.beginPath();
                // 确定画笔起点。将经纬度坐标映射为画布上的格点。
                ctx.moveTo(
                    padding[3] + (e1[0][0] - originLon) / onePx,
                    padding[0] + (originLat - e1[0][1]) / onePx
                );
                for (let i = 1; i < e1.length; i++) {
                    // 画笔移动路径。
                    ctx.lineTo(
                        padding[3] + (e1[i][0] - originLon) / onePx,
                        padding[0] + (originLat - e1[i][1]) / onePx
                    );
                }
                ctx.closePath();
                // 绘制画笔移动路径。
                ctx.stroke();
            });
        } else if (type === "MultiPolygon") {
            coordinates.forEach((e1) => {
                e1.forEach((e2) => {
                    ctx.beginPath();
                    ctx.moveTo(
                        padding[3] + (e2[0][0] - originLon) / onePx,
                        padding[0] + (originLat - e2[0][1]) / onePx
                    );
                    for (let i = 1; i < e2.length; i++) {
                        ctx.lineTo(
                            padding[3] + (e2[i][0] - originLon) / onePx,
                            padding[0] + (originLat - e2[i][1]) / onePx
                        );
                    }
                    ctx.closePath();
                    ctx.stroke();
                });
            });
        }
    });
}

// 画图例
function drawLegend(props, ctx, cwidth, cheight) {
    let padding = props.padding,
        legend = props.legend;
    let width = legend.width || 60,
        height = legend.height || 22,
        color = legend.color || [],
        level = legend.level || [];
    if (color.length != 0) {
        ctx.font = "16px 宋体";
        ctx.textAlign = "left";
        ctx.textBaseline = "middle";
        if (legend.name)
            ctx.fillText(
                legend.name,
                padding[3] + cwidth,
                padding[0] + cheight - (color.length + 0.8) * height
            );
        if (color.length == level.length) {
            color.forEach((element, index) => {
                ctx.fillStyle = element;
                ctx.fillRect(
                    padding[3] + cwidth,
                    padding[0] + cheight - (color.length - index) * height,
                    width,
                    height
                );
                ctx.strokeStyle = "#000";
                ctx.strokeRect(
                    padding[3] + cwidth,
                    padding[0] + cheight - (color.length - index) * height,
                    width,
                    height
                );
                ctx.fillStyle = "#000";
                ctx.fillText(
                    level[index],
                    padding[3] + cwidth + 10 + width,
                    padding[0] + cheight - (color.length - index - 0.5) * height
                );
            });
        } else if (color.length - 1 == level.length) {
            color.forEach((element, index) => {
                ctx.fillStyle = element;
                ctx.fillRect(
                    padding[3] + cwidth,
                    padding[0] + cheight - (color.length - index) * height,
                    width,
                    height
                );
                ctx.strokeStyle = "#000";
                ctx.strokeRect(
                    padding[3] + cwidth,
                    padding[0] + cheight - (color.length - index) * height,
                    width,
                    height
                );
                ctx.fillStyle = "#000";
                if (index != color.length - 1) {
                    ctx.fillText(
                        level[index],
                        padding[3] + cwidth + 10 + width,
                        padding[0] + cheight - (color.length - index - 1) * height
                    );
                }
            });
        }
    }
}

// 画图例
function drawLegend2(props, ctx, ch) {
    let padding = props.padding,
        legend = props.legend;
    let width = legend.width || 60,
        height = legend.height || 20,
        color = legend.color || [],
        level = legend.level || [];
    if (color.length != 0) {
        ctx.textAlign = "center";
        ctx.textBaseline = "top";
        if (color.length - level.length == 1) {
            color.forEach((element, index) => {
                let x = padding[3] + index * width;
                ctx.fillStyle = element;
                ctx.fillRect(x, ch - padding[2] + 70, width, height);
                ctx.strokeStyle = "#000";
                ctx.strokeRect(x, ch - padding[2] + 70, width, height);
                if (index > 0) {
                    ctx.fillStyle = "#000";
                    ctx.fillText(level[index - 1], x, ch - padding[2] + 96);
                }
            });
        }
    }
}

// 画标注
function drawName(props, ctx) {
    let xAxis = props.xAxis,
        yAxis = props.yAxis,
        onePx = props.onePx,
        padding = props.padding;
    ctx.font = "14px 宋体";
    ctx.fillStyle = "#000";
    ctx.textAlign = "center";
    ctx.textBaseline = "middle";
    [
        { label: "长安区", lon: 114.59, lat: 38.08 },
        { label: "桥西区", lon: 114.45, lat: 38.01 },
        { label: "新华区", lon: 114.44, lat: 38.11 },
        { label: "裕华区", lon: 114.59, lat: 38.01 },
    ].forEach((e) => {
        ctx.fillText(
            e.label,
            padding[3] + (e.lon - xAxis[0]) / onePx,
            padding[0] + (yAxis[yAxis.length - 1] - e.lat) / onePx
        );
    });
}
// 画坐标系
function drawCoordinateAxis(props, ctx) {
    let xAxis = props.xAxis,
        yAxis = props.yAxis,
        onePx = props.onePx,
        padding = props.padding;
    let originLon = xAxis[0], // 坐标轴原点经纬度
        originLat = yAxis[0],
        width = Math.ceil((xAxis[xAxis.length - 1] - xAxis[0]) / onePx), // 画布非留白部分的尺寸
        height = Math.ceil((yAxis[yAxis.length - 1] - yAxis[0]) / onePx);
    // 轴线
    ctx.font = "16px 宋体";
    ctx.lineWidth = 1;
    ctx.strokeStyle = "#000";
    ctx.strokeRect(padding[3], padding[0], width, height);
    // y轴单位
    ctx.fillStyle = "#000";
    ctx.textAlign = "center";
    ctx.textBaseline = "bottom";
    ctx.fillText("°N", padding[3], padding[0] - 15);
    // x轴单位
    ctx.textAlign = "left";
    ctx.textBaseline = "middle";
    ctx.fillText("°E", padding[3] + width + 15, padding[0] + height);
    // y轴刻度
    ctx.textAlign = "right";
    ctx.textBaseline = "middle";
    yAxis.forEach((element, index) => {
        if (index != 0 && index != yAxis.length - 1) {
            let dw = padding[0] + height - (element - originLat) / onePx;
            ctx.beginPath();
            ctx.moveTo(padding[3], dw);
            ctx.lineTo(padding[3] + 10, dw);
            ctx.closePath();
            ctx.stroke();
            ctx.fillText(element, padding[3] - 20, dw);
        }
    });
    // x轴刻度
    ctx.textAlign = "center";
    ctx.textBaseline = "top";
    xAxis.forEach((element, index) => {
        if (index != 0 && index != xAxis.length - 1) {
            let dw = padding[3] + (element - originLon) / onePx;
            ctx.beginPath();
            ctx.moveTo(dw, padding[0] + height);
            ctx.lineTo(dw, padding[0] + height - 10);
            ctx.closePath();
            ctx.stroke();
            ctx.fillText(element, dw, padding[0] + height + 20);
        }
    });
}
