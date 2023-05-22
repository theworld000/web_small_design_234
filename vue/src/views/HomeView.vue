<template>
  <el-container style="min-height: 100vh; ">
    <el-aside :width="sideWidth + 'px'"
              style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0, 21, 41, 0.35)">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
               background-color="rgb(48, 65, 86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" alt=""
               style="width: 20px; position: relative; top:4px; left: 3px;margin-right: 6px">
          <b style="color: white" v-show="logoTextShow">课程管理系统</b>
        </div>
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>
            <span slot="title">课程管理</span>
          </template>
          <el-menu-item-group>
            <!--            <template slot="title">分组一</template>-->
            <el-menu-item index="1-1">公基课程</el-menu-item>
            <el-menu-item index="1-2">选修课程</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>
            <span slot="title">学院信息管理</span>
          </template>
          <el-menu-item-group>
            <!--            <template slot="title">分组一</template>-->
            <el-menu-item index="2-1">计算机学院</el-menu-item>
            <el-menu-item index="2-2">商学院</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-setting"></i>
            <span slot="title">用户信息管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1">普通用户</el-menu-item>
            <el-menu-item index="2-2">管理员用户</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 25px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>测试用户</span>
          <i class="el-icon-arrow-down" style="margin: 5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人信息</el-dropdown-item>
            <!--            <el-dropdown-item @click="exit1()">退出</el-dropdown-item>-->
            <el-button @click="exit1()">退出</el-button>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>

      <el-main>

        <div style="margin-bottom: 30px">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{path:'/'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>课程管理</el-breadcrumb-item>
          </el-breadcrumb>
        </div>

        <div style="padding: 10px 0">
          <el-input class="ml-5" style="width: 200px" placeholder="请输入课程id" suffix-icon="el-icon-search"
                    v-model="id"></el-input>
          <el-input class="ml-5" style="width: 200px" placeholder="请输入课时数" suffix-icon="el-icon-message"
                    v-model="hours"></el-input>
          <el-input class="ml-5" style="width: 200px" placeholder="请输入学院编号" suffix-icon="el-icon-position"
                    v-model="sid"></el-input>
          <el-button class="ml-5" type="primary" @click="this.load">搜索</el-button>
          <el-button class="ml-5" type="warning" @click="this.reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
          <el-button type="primary" @click="this.handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定批量删除这些数据吗？"
              @confirm="this.delBatch"
          >
            <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button type="primary" class="ml-5">导入<i class="el-icon-bottom"></i></el-button>
          <el-button type="danger">导出<i class="el-icon-top"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55">
          </el-table-column>
          <el-table-column prop="id" label="ID" width="140">
          </el-table-column>
          <el-table-column label="课程封面" width="140" align="center">
            <template slot-scope="scope">
              <el-image style="width: 100%; height: 100%" :src="' http://localhost:80/myWebImages/' + scope.row.imageUrl"> </el-image>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="课程名称" width="120">
          </el-table-column>
          <el-table-column prop="hours" label="课时数">
          </el-table-column>
          <el-table-column prop="sid" label="开设学院编号">
          </el-table-column>
          <el-table-column prop="sname" label="开设学院名称">
          </el-table-column>

          <el-table-column label="操作" width="200" align="center">
            <template slot-scope="scope">
              <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
              <el-popconfirm
                  class="ml-5"
                  confirm-button-text='确定'
                  cancel-button-text='我再想想'
                  icon="el-icon-info"
                  icon-color="red"
                  title="确定删除这条用户信息吗？"
                  @confirm="del(scope.row.id)"
              >
                <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[2, 5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="this.total">
          </el-pagination>
        </div>

        <el-dialog title="课程信息" :visible.sync="dialogFormVisible" width="30%">
          <el-form label-width="120px">
            <el-form-item label="课程名称">
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="课时数">
              <el-input v-model="form.hours" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="开设学院编号">
              <el-input v-model="form.sid" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="开设学院名称">
              <el-input v-model="form.sname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="图片">
              <el-upload class="upload-demo"
                         action="http://localhost:9090/file/add"
                         name="file"
                         :on-preview="handlePreview"
                         :on-remove="handleRemove"
                         :before-remove="beforeRemove"
                         :before-upload="onBeforeUpload"
                         multiple
                         :limit="3"
                         :on-exceed="handleExceed"
                         :on-success="handle_success"
                         :data="fanhuicanshu(form.id)">
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
            </el-form-item>
            <!--            <el-form-item label="电话">-->
            <!--              <el-input v-model="form.tel" autocomplete="off"></el-input>-->
            <!--            </el-form-item>-->
            <!--            <el-form-item label="家庭住址">-->
            <!--              <el-input v-model="form.address" autocomplete="off"></el-input>-->
            <!--            </el-form-item>-->
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="this.addCourse">确 定</el-button>
          </div>
        </el-dialog>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'

export default {

  name: 'HomeView',
  data() {
    const item = {
      date: '2023-05-19',
      name: 'admin',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 20,
      id: "",
      hours: "",
      sid: "",
      schoolname: "计算机学院",
      form: {},
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: "headerBg",
      dialogFormVisible: false,
      multipleSelection: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    exit1() {
      this.$router.push("/login")
    },
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) { //翻转之后为 收缩状态
        this.sideWidth = 64;
        this.collapseBtnClass = 'el-icon-s-unfold';
        this.logoTextShow = false
      } else { //翻转之后为 展开状态
        this.sideWidth = 200;
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    init() {
      this.request.post("/course/check").then(res => {

      })
    },
    load() {
      this.init();
      this.request.get("/course/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          id: this.id,
          hours: this.hours,
          sid: this.sid
        }
      }).then(res => {
        console.log(res);

        this.tableData = res.records;
        this.total = res.total;
      })
      console.log(this.tableData);
    },
    reset() {
      this.id = "";
      this.hours = "";
      this.sid = "";
      this.load();
    },
    handleAdd() {
      this.dialogFormVisible = true;
      this.form = {};

    },
    addCourse() {
      this.request.post("/course", this.form).then(res => {
        if (res) {
          this.$message.success("新课程保存成功！");
          this.dialogFormVisible = false;
          this.load();
          this.load();
          this.load();
        } else {
          this.$message.success("新课程保存失败，请检查问题所在!");
        }
      })
    },
    handleEdit(row) {
      this.form = row;
      this.dialogFormVisible = true;
    },
    del(id) {
      this.request.delete("/course/" + id).then(res => {
        if (res) {
          this.$message.success("课程删除成功！");
          this.dialogFormVisible = false;
          this.load();
        } else {
          this.$message.error("课程删除失败，请检查问题所在!");
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val;
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id);
      console.log(ids);

      this.request.delete("/course/batch", {data: ids}).then(res => {
        if (res) {
          this.$message.success("批量删除课程删除成功！");
          this.dialogFormVisible = false;
          this.load();
        } else {
          this.$message.error("批量删除失败，请检查问题所在!");
        }
      })
    },
    onBeforeUpload(file)
    {
      const isIMAGE = file.type === 'image/jpeg'||'image/gif'||'image/png||';
      const isLt1M = file.size / 1024 / 1024 < 1;

      if (!isIMAGE) {
        this.$message.error('上传文件只能是图片格式!');
      }
      if (!isLt1M) {
        this.$message.error('上传文件大小不能超过 1MB!');
      }
      return isIMAGE && isLt1M;
    },
    handleRemove(file, fileList) {
      console.log(file,fileList);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },handle_success(res){
      console.log("上传完毕")
      console.log(res);
    },fanhuicanshu(id){
      let res = {id: id};
      return res;
    }
  }
}
</script>
