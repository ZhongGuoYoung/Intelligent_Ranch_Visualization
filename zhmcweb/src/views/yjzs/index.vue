<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">
.view {
    width: 100%;
    height: 100%;
    padding: 40px;
    box-sizing: border-box;

    .wrap {
        width: 100%;
        height: 100%;
        background: url(@/assets/images/应激指数/table-bg.png) center/100% 100% no-repeat;
        display: flex;
        flex-direction: column;

    }

    .table-box {
        flex: 1;
        width: 100%;
        color: #fff;
        border: 1px solid #000;

        .header-tem,
        .header-rhu,
        .header-level {
            color: #02FFFF;
            background-color: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(6px);
        }

        .header-rhu {
            padding: 5px;
        }

        .header-level,
        .header-tem {
            width: 150px;
        }

        .level-0 {
            background-color: #2c7942;
        }

        .level-1 {
            background-color: #83a643;
        }

        .level-2 {
            background-color: #50381c;
        }

        .level-3 {
            background-color: #941b29;
        }

        .level-4 {
            background-color: #361415;
        }

        .header-rhu-tem {
            position: relative;
            background: linear-gradient(to bottom left, #0068b7 50%, #60a3cc 50%);
            line-height: 2em;

            span:first-child {
                display: block;
                position: absolute;
                top: 0;
                right: 0;
                width: 50%;
            }

            span:last-child {
                display: block;
                position: absolute;
                bottom: 0;
                left: 0;
                width: 50%;
            }
        }
    }


    .level-box {
        width: 100%;
        height: 100px;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 30px;
        color: #02FFFF;

        div {
            display: grid;
            grid: 30px/80px 80px;
            line-height: 30px;
        }
    }
}
</style>

<template>
    <div class="view">
        <div class="wrap">
            <table class="table-box"
                   border>
                <tr>
                    <td class="header-rhu-tem"><span>湿度%</span><span>温度℃</span></td>
                    <td class="header-rhu">10%</td>
                    <td class="header-rhu">20%</td>
                    <td class="header-rhu">30%</td>
                    <td class="header-rhu">40%</td>
                    <td class="header-rhu">50%</td>
                    <td class="header-rhu">60%</td>
                    <td class="header-rhu">70%</td>
                    <td class="header-rhu">80%</td>
                    <td class="header-rhu">90%</td>
                    <td class="header-rhu">100%</td>
                    <td class="header-rhu"></td>
                </tr>
                <tr>
                    <td class="header-tem">20</td>
                    <td class="level-0">63</td>
                    <td class="level-0">63.5</td>
                    <td class="level-0">64.1</td>
                    <td class="level-0">64.7</td>
                    <td class="level-0">65.3</td>
                    <td class="level-0">65.8</td>
                    <td class="level-0">66.4</td>
                    <td class="level-0">67</td>
                    <td class="level-0">67.5</td>
                    <td class="level-1">68.1</td>
                    <td class="header-level"
                        rowspan="3">轻度应激</td>
                </tr>
                <tr>
                    <td class="header-tem">21</td>
                    <td class="level-0">63.9</td>
                    <td class="level-0">64.5</td>
                    <td class="level-0">65.2</td>
                    <td class="level-0">65.9</td>
                    <td class="level-0">66.6</td>
                    <td class="level-0">67.2</td>
                    <td class="level-1">67.9</td>
                    <td class="level-1">68.6</td>
                    <td class="level-1">69.2</td>
                    <td class="level-1">69.9</td>
                </tr>
                <tr>
                    <td class="header-tem">22</td>
                    <td class="level-0">64.8</td>
                    <td class="level-0">65.5</td>
                    <td class="level-0">66.3</td>
                    <td class="level-0">67.1</td>
                    <td class="level-1">67.9</td>
                    <td class="level-1">68.6</td>
                    <td class="level-1">69.4</td>
                    <td class="level-1">70.2</td>
                    <td class="level-1">70.9</td>
                    <td class="level-1">71.7</td>
                </tr>
                <tr>
                    <td class="header-tem">23</td>
                    <td class="level-0">65.7</td>
                    <td class="level-0">66.5</td>
                    <td class="level-0">67.4</td>
                    <td class="level-1">68.3</td>
                    <td class="level-1">69.2</td>
                    <td class="level-1">70</td>
                    <td class="level-1">70.9</td>
                    <td class="level-1">71.8</td>
                    <td class="level-2">72.6</td>
                    <td class="level-2">73.5</td>
                    <td class="header-level"
                        rowspan="4">中度应激</td>
                </tr>
                <tr>
                    <td class="header-tem">24</td>
                    <td class="level-0">66.6</td>
                    <td class="level-0">67.5</td>
                    <td class="level-1">68.5</td>
                    <td class="level-1">69.5</td>
                    <td class="level-1">70.5</td>
                    <td class="level-1">71.4</td>
                    <td class="level-2">72.4</td>
                    <td class="level-2">73.4</td>
                    <td class="level-2">74.3</td>
                    <td class="level-2">75.3</td>
                </tr>
                <tr>
                    <td class="header-tem">25</td>
                    <td class="level-0">67.5</td>
                    <td class="level-1">68.5</td>
                    <td class="level-1">69.6</td>
                    <td class="level-1">70.7</td>
                    <td class="level-1">71.8</td>
                    <td class="level-2">72.8</td>
                    <td class="level-2">73.9</td>
                    <td class="level-2">75</td>
                    <td class="level-2">76</td>
                    <td class="level-2">77.1</td>
                </tr>
                <tr>
                    <td class="header-tem">26</td>
                    <td class="level-1">68.4</td>
                    <td class="level-1">69.5</td>
                    <td class="level-1">70.7</td>
                    <td class="level-1">71.9</td>
                    <td class="level-2">73.1</td>
                    <td class="level-2">74.2</td>
                    <td class="level-2">75.4</td>
                    <td class="level-2">76.6</td>
                    <td class="level-2">77.7</td>
                    <td class="level-2">78.9</td>
                </tr>
                <tr>
                    <td class="header-tem">27</td>
                    <td class="level-1">69.3</td>
                    <td class="level-1">70.5</td>
                    <td class="level-1">71.8</td>
                    <td class="level-2">73.1</td>
                    <td class="level-2">74.4</td>
                    <td class="level-2">75.6</td>
                    <td class="level-2">76.9</td>
                    <td class="level-2">78.2</td>
                    <td class="level-2">79.4</td>
                    <td class="level-3">80.7</td>
                    <td class="header-level"
                        rowspan="6">重度应激</td>
                </tr>
                <tr>
                    <td class="header-tem">28</td>
                    <td class="level-1">70.2</td>
                    <td class="level-1">71.5</td>
                    <td class="level-2">72.9</td>
                    <td class="level-2">74.3</td>
                    <td class="level-2">75.7</td>
                    <td class="level-2">77</td>
                    <td class="level-2">78.4</td>
                    <td class="level-2">79.8</td>
                    <td class="level-3">81.1</td>
                    <td class="level-3">82.5</td>
                </tr>
                <tr>
                    <td class="header-tem">29</td>
                    <td class="level-1">71.1</td>
                    <td class="level-2">72.5</td>
                    <td class="level-2">74</td>
                    <td class="level-2">75.5</td>
                    <td class="level-2">77</td>
                    <td class="level-2">78.4</td>
                    <td class="level-2">79.9</td>
                    <td class="level-3">81.4</td>
                    <td class="level-3">82.8</td>
                    <td class="level-3">84.3</td>
                </tr>
                <tr>
                    <td class="header-tem">30</td>
                    <td class="level-2">72</td>
                    <td class="level-2">73.5</td>
                    <td class="level-2">75.1</td>
                    <td class="level-2">76.7</td>
                    <td class="level-2">78.3</td>
                    <td class="level-2">79.8</td>
                    <td class="level-3">81.4</td>
                    <td class="level-3">83</td>
                    <td class="level-3">84.5</td>
                    <td class="level-3">86.1</td>
                </tr>
                <tr>
                    <td class="header-tem">31</td>
                    <td class="level-2">72.9</td>
                    <td class="level-2">74.5</td>
                    <td class="level-2">76.2</td>
                    <td class="level-2">77.9</td>
                    <td class="level-2">79.6</td>
                    <td class="level-3">81.2</td>
                    <td class="level-3">82.9</td>
                    <td class="level-3">84.6</td>
                    <td class="level-3">86.2</td>
                    <td class="level-3">87.9</td>
                </tr>
                <tr>
                    <td class="header-tem">32</td>
                    <td class="level-2">73.8</td>
                    <td class="level-2">75.5</td>
                    <td class="level-2">77.3</td>
                    <td class="level-2">79.1</td>
                    <td class="level-3">80.9</td>
                    <td class="level-3">82.6</td>
                    <td class="level-3">84.4</td>
                    <td class="level-3">86.2</td>
                    <td class="level-3">87.9</td>
                    <td class="level-3">89.7</td>
                </tr>
                <tr>
                    <td class="header-tem">33</td>
                    <td class="level-2">74.7</td>
                    <td class="level-2">76.5</td>
                    <td class="level-2">78.4</td>
                    <td class="level-3">80.3</td>
                    <td class="level-3">82.2</td>
                    <td class="level-3">84</td>
                    <td class="level-3">85.9</td>
                    <td class="level-3">87.8</td>
                    <td class="level-3">89.6</td>
                    <td class="level-4">91.5</td>
                    <td class="header-level"
                        rowspan="10">严重应激</td>
                </tr>
                <tr>
                    <td class="header-tem">34</td>
                    <td class="level-2">75.6</td>
                    <td class="level-2">77.5</td>
                    <td class="level-2">79.5</td>
                    <td class="level-3">81.5</td>
                    <td class="level-3">83.5</td>
                    <td class="level-3">85.4</td>
                    <td class="level-3">87.4</td>
                    <td class="level-3">89.4</td>
                    <td class="level-4">91.3</td>
                    <td class="level-4">93.3</td>
                </tr>
                <tr>
                    <td class="header-tem">35</td>
                    <td class="level-2">76.5</td>
                    <td class="level-2">78.5</td>
                    <td class="level-3">80.6</td>
                    <td class="level-3">82.7</td>
                    <td class="level-3">84.8</td>
                    <td class="level-3">86.8</td>
                    <td class="level-3">88.9</td>
                    <td class="level-4">91.9</td>
                    <td class="level-4">93</td>
                    <td class="level-4">95.1</td>
                </tr>
                <tr>
                    <td class="header-tem">36</td>
                    <td class="level-2">77.4</td>
                    <td class="level-2">79.5</td>
                    <td class="level-3">81.7</td>
                    <td class="level-3">83.9</td>
                    <td class="level-3">86.1</td>
                    <td class="level-3">88.2</td>
                    <td class="level-4">90.4</td>
                    <td class="level-4">92.6</td>
                    <td class="level-4">94.7</td>
                    <td class="level-4">96.9</td>
                </tr>
                <tr>
                    <td class="header-tem">37</td>
                    <td class="level-2">78.3</td>
                    <td class="level-3">80.5</td>
                    <td class="level-3">82.8</td>
                    <td class="level-3">85.1</td>
                    <td class="level-3">87.4</td>
                    <td class="level-3">89.6</td>
                    <td class="level-4">94.9</td>
                    <td class="level-4">94.2</td>
                    <td class="level-4">96.4</td>
                    <td class="level-4">98.7</td>
                </tr>
                <tr>
                    <td class="header-tem">38</td>
                    <td class="level-2">79.2</td>
                    <td class="level-3">81.5</td>
                    <td class="level-3">83.9</td>
                    <td class="level-3">86.3</td>
                    <td class="level-3">88.7</td>
                    <td class="level-4">91</td>
                    <td class="level-4">96.4</td>
                    <td class="level-4">95.8</td>
                    <td class="level-4">98.1</td>
                    <td class="level-4">100.5</td>
                </tr>
                <tr>
                    <td class="header-tem">39</td>
                    <td class="level-3">80.1</td>
                    <td class="level-3">82.5</td>
                    <td class="level-3">85</td>
                    <td class="level-3">87.5</td>
                    <td class="level-4">90</td>
                    <td class="level-4">92.4</td>
                    <td class="level-4">94.9</td>
                    <td class="level-4">97.4</td>
                    <td class="level-4">99.8</td>
                    <td class="level-4">102.3</td>
                </tr>
                <tr>
                    <td class="header-tem">40</td>
                    <td class="level-3">81</td>
                    <td class="level-3">83.5</td>
                    <td class="level-3">86.1</td>
                    <td class="level-3">88.7</td>
                    <td class="level-4">91.3</td>
                    <td class="level-4">93.8</td>
                    <td class="level-4">96.4</td>
                    <td class="level-4">99</td>
                    <td class="level-4">101.5</td>
                    <td class="level-4">104.1</td>
                </tr>
                <tr>
                    <td class="header-tem">41</td>
                    <td class="level-3">81.9</td>
                    <td class="level-3">84.5</td>
                    <td class="level-3">87.2</td>
                    <td class="level-3">89.9</td>
                    <td class="level-4">92.6</td>
                    <td class="level-4">95.2</td>
                    <td class="level-4">97.9</td>
                    <td class="level-4">100.5</td>
                    <td class="level-4">103.2</td>
                    <td class="level-4">105.9</td>
                </tr>
                <tr>
                    <td class="header-tem">42</td>
                    <td class="level-3">82.8</td>
                    <td class="level-3">85.5</td>
                    <td class="level-3">88.3</td>
                    <td class="level-4">91.1</td>
                    <td class="level-4">93.9</td>
                    <td class="level-4">96.6</td>
                    <td class="level-4">99.4</td>
                    <td class="level-4">102.2</td>
                    <td class="level-4">104.9</td>
                    <td class="level-4">107.7</td>
                </tr>
            </table>
            <div class="level-box">
                <div><span style="background:#2c7942"></span><span>无应激</span></div>
                <div><span style="background:#83a643"></span><span>轻度应激</span></div>
                <div><span style="background:#50381c"></span><span>中度应激</span></div>
                <div><span style="background:#941b29"></span><span>重度应激</span></div>
                <div><span style="background:#361415"></span><span>严重应激</span></div>
            </div>
        </div>
    </div>
</template>

<script setup>


</script>