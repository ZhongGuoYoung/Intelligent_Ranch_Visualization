<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">
.my-table {
    --el-table-bg-color: transparent;
    --el-table-header-bg-color: transparent;
    --el-table-tr-bg-color: transparent;
    --el-table-border-color: transparent;

    :deep(.el-table__empty-text) {
        color: #fff;
    }

    :deep(.table-header-row) {
        height: 50px;
        color: #0edfd9;
        font-size: 18px;
    }

    :deep(.table-row) {
        height: 50px;
        color: #fff;
        font-size: 16px;
    }

    :deep(.table-cell),
    :deep(.table-header-cell) {
        padding: 1px 0;
        font-weight: normal;
        background-color: rgba(255, 255, 255, 0.16) !important;
        background-clip: content-box;
        border-right: 1px solid rgba(255, 255, 255, 0.5);
    }

    :deep(.table-header-cell) {
        background-color: rgb(205, 229, 246, 0.4) !important;
    }

    :deep(.table-cell:first-child),
    :deep(.table-header-cell:first-child) {
        border-radius: 10px 0 0 10px;
    }

    :deep(.table-cell:last-child),
    :deep(.table-header-cell:last-child) {
        border-radius: 0 10px 10px 0;
        border-right: none;
    }
}

.table-view-container {
    width: 100%;
    height: 100%;
    padding: 10px;
    gap: 10px;

    .view-header {
        background-color: rgba(255, 255, 255, 0.1);
        border-radius: 10px;
        padding: 10px;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .header-left,
        .header-right {
            display: flex;
            align-items: center;
        }

        .header-left {
            gap: 10px;
            flex: 1
        }

        .header-right {
            width: 240px;
            justify-content: right;
        }
    }

    .view-main {
        background-color: rgba(255, 255, 255, 0.1);
        border-radius: 10px;
        padding: 10px;
    }

    .view-footer {
        background-color: rgba(255, 255, 255, 0.1);
        border-radius: 10px;
        padding: 10px;
        display: flex;
        align-items: center;
        justify-content: center;


        :deep(.el-pagination__total),
        :deep(.el-pagination__jump) {
            color: #fff;
            font-size: 18px;
        }
    }

    .table-head {
        width: 30px;
        height: 30px;
        border-radius: 50%;
        overflow: unset;

        :deep(img) {
            border-radius: 50%;
        }
    }
}

.look-view-container {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    padding: 10px;
    gap: 10px;

    .view-close {
        position: absolute;
        top: 0;
        right: 0;
        width: 30px;
        height: 30px;
        background: url(@/assets/images/close.png) center/100% no-repeat;
        cursor: pointer;
        border-radius: 50%;

        &:hover {
            background: rgba(90, 160, 180, 0.4) url(@/assets/images/close.png) center/100% no-repeat;
        }
    }

    .view-footer {
        background-color: rgba(255, 255, 255, 0.2);
        border-radius: 10px;
        padding: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
        font-size: 22px;
        font-weight: bold;
        text-indent: 20px;
        letter-spacing: 20px;

        &:hover {
            background: linear-gradient(to right, rgba(150, 185, 190, 0.4), rgba(90, 160, 180, 0.9), rgba(150, 185, 190, 0.4));
        }
    }

    .view-main {
        background-color: rgba(255, 255, 255, 0.2);
        border-radius: 10px;
        padding: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        gap: 40px;
    }

    .form-wrap {
        width: 500px;

        >div {
            display: flex;
            gap: 10px;
            margin-bottom: 30px;
        }

        :deep(.el-input__inner) {
            height: 50px;
            font-size: 18px;
        }

        :deep(.el-form-item__label-wrap) {
            align-items: center;

            label {
                color: #fff;
                font-size: 20px;
            }
        }

        :deep(.el-form-item__error) {
            font-size: 16px;
        }
    }

    .head-wrap {
        width: 160px;
        height: 160px;
        border-radius: 50%;
        border: 2px solid #fff;
        position: relative;

        img {
            width: 100%;
            height: 100%;
            border-radius: 50%;
        }

        .head-hover {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            border-radius: 50%;
            cursor: pointer;
            // background: rgba(0, 0, 0, 0.5) url(@/assets/images/xtgl/upload-head.png) center/40% no-repeat;
        }
    }

    :deep(.el-select__wrapper) {
        font-size: 18px;
        padding: 19px 12px;
    }
}
</style>

<template>
    <el-container class="table-view-container"
                  v-show="!lookDialog.show">
        <el-header class="view-header"
                   height="50px">
            <div class="header-left">
                <el-col :span="2"><el-input v-model="select_username"
                              placeholder="在此输入名称"
                              clearable></el-input></el-col>
                <el-col :span="2"><el-input v-model="select_cellphone"
                              placeholder="在此输入号码"
                              clearable></el-input></el-col>
                <el-col :span="2">
                    <el-select v-model="select_type"
                               placeholder="请选择账号类型"
                               clearable>
                        <el-option label="普通用户"
                                   value="普通用户" />
                        <el-option label="管理员用户"
                                   value="管理员用户" />
                    </el-select>
                </el-col>
                <el-button :icon="Search"
                           @click="getDataByPaging(1)">查询</el-button>
            </div>
            <div class="header-right">
                <el-button :icon="Plus"
                           @click="openLookDialog()">添加</el-button>
                <el-button :icon="Delete"
                           @click="openDeleteDialog()">
                    {{ "删除" + (Array.isArray(deleteDialog.values) && deleteDialog.values.length > 0 ? "（" + deleteDialog.values.length + "）" : "") }}
                </el-button>
            </div>
        </el-header>

        <el-main class="view-main">
            <el-table ref="refTable"
                      class="my-table"
                      :data="pageInfo.array"
                      empty-text="未查询到数据"
                      header-row-class-name="table-header-row"
                      header-cell-class-name="table-header-cell"
                      row-class-name="table-row"
                      cell-class-name="table-cell"
                      row-key="id"
                      @selection-change="selectionChange">
                <el-table-column align="center"
                                 type="selection"
                                 width="50" />

                <el-table-column align="center"
                                 label="头像"
                                 width="100">
                    <template #default="scope">
                        <el-image v-if="scope.row.head"
                                  class="table-head"
                                  preview-teleported="true"
                                  :src="scope.row.head"
                                  :preview-src-list="[scope.row.head]" />
                        <img v-else
                             class="table-head"
                             src="" />
                    </template>
                </el-table-column>
                <el-table-column align="center"
                                 prop="username"
                                 label="昵称"
                                 width="100" />
                <el-table-column align="center"
                                 prop="cellphone"
                                 label="手机号" />
                <el-table-column align="center"
                                 prop="email"
                                 label="邮箱" />
                <el-table-column align="center"
                                 prop="type"
                                 label="账号类型" />
                <el-table-column align="center"
                                 prop="creation_time"
                                 label="注册时间"
                                 :sortable="true" />

                <el-table-column align="center"
                                 label="操作"
                                 width="200">
                    <template #default="scope">
                        <el-button-group>
                            <el-button :icon="View"
                                       @click="openLookDialog(scope.row)"
                                       title="查看">查看</el-button>
                            <el-button :icon="Delete"
                                       @click="openDeleteDialog(scope.row)"
                                       title="删除">删除</el-button>
                        </el-button-group>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>

        <el-footer class="view-footer"
                   height="50px">
            <el-pagination background
                           layout="total, prev, pager ,next, jumper"
                           :total="pageInfo.totalItem"
                           :page-size="pageInfo.pageSize"
                           @current-change="(page) => { getDataByPaging(page) }" />
        </el-footer>
    </el-container>

    <!-- 删除弹窗 -->
    <el-dialog v-model="deleteDialog.show"
               width="400"
               align-center>
        <div style="text-align: left;line-height: 30px;">
            <div style="font-size: 18px;color: red;">该操作将会删除：</div>
            <div style="font-size: 16px;text-indent: 2em;">
                {{Array.isArray(deleteDialog.values) ? deleteDialog.values.map(obj => obj.username).join("、") : deleteDialog.values.username}}
            </div>
        </div>
        <template #footer>
            <div class="dialog-footer">
                <el-button type="primary"
                           @click="deleteDialogConfirm">删除</el-button>
                <el-button @click="deleteDialog.show = false">取消</el-button>
            </div>
        </template>
    </el-dialog>

    <!-- 查看面板 -->
    <el-container class="look-view-container"
                  v-if="lookDialog.show">
        <div class="view-close"
             @click="colseLookDialog"></div>
        <el-main class="view-main">
            <div class="head-wrap"
                 @mouseenter="headHover = true"
                 @mouseleave="headHover = false">
                <img v-if="lookDialog.value.head"
                     :src="lookDialog.value.head">
                <img v-else
                     src="">
                <div v-if="headHover"
                     class="head-hover"
                     @click="headCropper = true"></div>
            </div>

            <el-form class="form-wrap"
                     ref="refForm"
                     :rules="rules"
                     :model="lookDialog.value"
                     label-width="auto">
                <el-form-item label="昵称:"
                              prop="username">
                    <el-input v-model="lookDialog.value.username"
                              placeholder="请输入昵称"
                              clearable />
                </el-form-item>
                <el-form-item label="密码:"
                              prop="password">
                    <el-input v-model="lookDialog.value.password"
                              placeholder="请输入密码"
                              clearable
                              show-password
                              type="password" />
                </el-form-item>
                <el-form-item label="手机:"
                              prop="cellphone">
                    <el-input v-model="lookDialog.value.cellphone"
                              placeholder="请输入手机号"
                              clearable
                              type="tel" />
                </el-form-item>
                <el-form-item label="邮箱:"
                              prop="email">
                    <el-input v-model="lookDialog.value.email"
                              placeholder="请输入邮箱"
                              clearable
                              type="email" />
                </el-form-item>
                <el-form-item label="类型:"
                              prop="type">
                    <el-select v-model="lookDialog.value.type"
                               placeholder="请选择账号类型">
                        <el-option label="普通用户"
                                   value="普通用户" />
                        <el-option label="管理员用户"
                                   value="管理员用户" />
                    </el-select>
                </el-form-item>
            </el-form>
        </el-main>
        <el-footer class="view-footer"
                   height="50px"
                   @click="lookDialogConfirm">保存</el-footer>
    </el-container>
    <!-- 更换头像面板 -->
    <cropper v-model="headCropper"
             :img="lookDialog.value.head"
             :confirm="cropperDialogConfirm"
             :key="new Date().getTime()" />
</template>

<script setup>
import axios from "axios";
import dayjs from "dayjs";
import { ref, reactive } from "vue";
import { View, Delete, Search, Plus } from "@element-plus/icons-vue";
import interfaces from "@/components/interfaces.js";
// import cropper from "@/components/cropper.vue";

var currentPage = 1;
const
    select_username = ref(""),
    select_cellphone = ref(""),
    select_type = ref(""),
    refTable = ref({}),
    refForm = ref({}),
    pageInfo = ref({}),
    deleteDialog = ref({
        show: false,
        values: []
    }),
    lookDialog = ref({
        show: false,
        value: {}
    }),
    headHover = ref(false),
    headCropper = ref(false);



// -------- 分页获取数据 --------
getDataByPaging(1)
function getDataByPaging(cpage) {
    var where = [];
    if (select_cellphone.value) where.push({ type: "like", field: "cellphone", value: select_cellphone.value, connector: "and" });
    if (select_username.value) where.push({ type: "like", field: "username", value: select_username.value, connector: "and" });
    if (select_type.value) where.push({ type: "=", field: "type", value: select_type.value, connector: "and" });
    var whereJSONString = JSON.stringify(where);
    // 去除 whereJSONString 两端的 []
    whereJSONString = whereJSONString.substring(1, whereJSONString.length - 1);
    axios({
        method: "get",
        url: interfaces.serviceurl + "fxdb/selectByPaging",
        params: {
            table: "t_user",
            page: cpage,                // 当前页码
            pageSize: 13,	            // 页码尺寸
            where: whereJSONString
        }
    }).then(function (response) {
        pageInfo.value = response.data.result;
        currentPage = response.data.result.currentPage;
        // console.log(response.data.result)
    }).catch(function (error) {
        console.log(error);
    })
}


// -------- 打开查看详情页 --------
function openLookDialog(obj) {
    lookDialog.value = { show: true, value: obj || { type: "普通用户" } };
}
// 关闭查看详情页
function colseLookDialog() {
    lookDialog.value = { show: false, value: {} };
}
// 保存修改结果
function lookDialogConfirm() {
    refForm.value.validate((valid, errors) => {
        // 验证通过
        if (valid) {
            var obj = lookDialog.value.value;
            if (obj.id) {
                axios({
                    method: "post",
                    url: interfaces.serviceurl + "fxdb/update",
                    data: Object.assign({
                        table: "t_user",
                        field: "id",
                        value: obj.id,
                    }, obj)
                }).then(function (response) {
                    lookDialog.value = { show: false, value: {} };
                    getDataByPaging(currentPage);
                })
            } else {
                axios({
                    method: "post",
                    url: interfaces.serviceurl + "fxdb/insert",
                    data: Object.assign({
                        table: "t_user",
                        creation_time: dayjs(new Date()).format("YYYY-MM-DD HH:mm:ss")
                    }, obj)
                }).then(function (response) {
                    lookDialog.value = { show: false, value: {} };
                    getDataByPaging(currentPage);
                })
            }
        }
    });
}
// 验证
const rules = reactive({
    username: [
        { required: true, message: "昵称不能为空。", trigger: "change" },
        { validator: usernameDuplicateChecking, message: "该昵称已存在，请重新设置。", trigger: "blur" },
    ],
    password: [
        { required: true, message: "密码不能为空。", trigger: "change" },
    ],
    email: [
        { type: "email", message: "邮箱格式错误。", trigger: "change" },
    ],
})
// 昵称唯一验证
function usernameDuplicateChecking(rule, value, callback) {
    var obj = lookDialog.value.value;
    if (value) {
        var where = [];
        where.push({ type: "=", binary: true, field: "username", value: value, connector: "and" });
        if (obj.id) where.push({ type: "!=", field: "id", value: obj.id, connector: "and" });
        var whereJSONString = JSON.stringify(where);
        whereJSONString = whereJSONString.substring(1, whereJSONString.length - 1);

        axios({
            method: "get",
            url: interfaces.serviceurl + "fxdb/select",
            params: {
                elements: "username",
                table: "t_user",
                where: whereJSONString
            }
        }).then(function (response) {
            if (response.data.result.length != 0) {
                callback(new Error("该昵称已存在，请重新设置。"));
            } else {
                callback();
            }
        })
    }
}


// -------- 打开删除弹窗 --------
function openDeleteDialog(obj) {
    if (obj) {
        refTable.value.clearSelection();
        deleteDialog.value = { show: true, values: obj };
    } else {
        deleteDialog.value.show = Array.isArray(deleteDialog.value.values) && deleteDialog.value.values.length > 0;
    }
}
// 确认删除
function deleteDialogConfirm() {
    axios({
        method: "get",
        url: interfaces.serviceurl + "fxdb/delete",
        params: {
            table: "t_user",
            type: "in",
            field: "id",
            values: Array.isArray(deleteDialog.value.values) ? deleteDialog.value.values.map(obj => obj.id).join(",") : deleteDialog.value.values.id
        }
    }).then(function (response) {
        deleteDialog.value = {
            show: false,
            values: []
        };
        getDataByPaging(currentPage);
    })
}
// 复选框
function selectionChange(array) {
    deleteDialog.value.values = array;
}

// -------- 更换头像 --------
function cropperDialogConfirm(data) {
    headCropper.value = false;
    if (data) lookDialog.value.value.head = data;
}
</script>
