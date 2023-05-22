<script>

export default {
  data() {
    return {
      name: "",
      username: "",
      password: "",
      confirmPassword: "",
      status_22: "22_open.png",
      status_33: "33_open.png",
      status: "注册",
      statusClass: "safe"
    }
  },
  methods: {
    close() {
      this.status_22 = "22_close.png";
      this.status_33 = "33_close.png";
    },
    open() {
      this.status_22 = "22_open.png";
      this.status_33 = "33_open.png";
    },
    isSafe() {
      if (this.password.length > 16) {
        this.status = "密码过长";
        this.statusClass = "unsafe";
      } else if (this.password !== this.confirmPassword) {
        this.status = "密码不一致";
        this.statusClass = "unsafe";
      } else {
        this.status = "注册";
        this.statusClass = "safe";
      }
    },
    checkString(str) {
      var reg = /^[a-zA-Z0-9_]{1,}$/;
      return reg.test(str);
    },
    checkSafe() {
      return !(this.statusClass !== "safe" || !this.checkString(this.password));
    },
    submit() {
      if (this.checkSafe()) {
        this.request.get("/users/add", {
          params: {
            name: this.username,
            password: this.password,
            firstName: this.name
          }
        }).then(res => {
          if (res) {
            alert("注册成功");
            this.$router.push("/login");
          } else {
            alert("注册失败");
            this.statusClass = "unsafe";
          }
        });
      }
    },
  },
}
</script>

<template>
  <div id="register-box">
    <div id="logo">
      <img src="@/assets/school.png" id="school"/>
    </div>
    <div id="register">
      <img :src=status_22 width="185" class="left-22">
      <img :src=status_33 width="185" class="right-33">

      <div class="top">课程管理系统注册</div>
      <div class="split"></div>
      <div class="center">
        <div class="box">
          <div class="search">
            <label for="username">姓名：</label>
            <input type="text" id="name" v-model="name" placeholder="请输入姓名"/>
          </div>
        </div>
        <div class="box">
          <div class="search">
            <label for="username">账号：</label>
            <input type="text" id="username" v-model="username" placeholder="请输入账号"/>
          </div>
        </div>
        <div class="box">
          <div class="search">
            <label for="password">密码：</label>
            <input type="password" id="password" v-model="password" placeholder="请输入密码" @focusin="close"
                   @focusout="open"
                   @change="isSafe()"
            />
          </div>
        </div>

        <div class="box">
          <div class="search">
            <label for="password">密码：</label>
            <input type="password" id="confirm" v-model="confirmPassword" placeholder="再次输入密码" @focusin="close"
                   @focusout="open"
                   @change="isSafe()"/>
            />
          </div>
        </div>
        <div class="clk-button safe" :class="statusClass">
          <button @click="submit()">
            <p>{{ status }}</p>
          </button>
        </div>
      </div>
    </div>

  </div>

</template>

<style scoped>

#register-box {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 35rem;
  width: 55rem;
}

#logo {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  height: 20rem;
  width: 20rem;
  border-radius: 50%;
  background-color: rgb(255, 255, 255);
  overflow: hidden;
}

#register {
  position: absolute;
  top: 50%;
  left: 40%;
  height: 35rem;
  width: 30rem;
  transform: translateY(-50%);
  border-radius: 1rem;
  background-color: rgb(255, 255, 255);
  overflow: hidden;
}

#school {
  display: block;
  position: absolute;
  width: 100%;
}

.top {
  position: relative;
  width: 100%;
  height: 3rem;
  margin-top: 1rem;
  font-size: 2rem;
  font-weight: 600;
  line-height: 3rem;
  text-align: center;
}

.split {
  position: relative;
  width: 100%;
  height: 0.5rem;
  margin-bottom: 1.5rem;
  background-color: rgb(182, 182, 182);
}

.center {
  position: relative;
  width: 100%;
  height: 20rem;
}

.box {
  position: relative;
  width: 100%;
  height: 4rem;
}

.search {
  position: absolute;
  height: 3rem;
  width: 90%;
  left: 50%;
  transform: translateX(-50%);
  border: 2px solid black;
  border-radius: 8px 8px 8px 8px;
  border-color: rgb(143, 143, 143);
  padding-left: 1rem;
  overflow: hidden;
  line-height: 3rem;
  font-size: 1.2rem;
}

.search input {
  font-size: 1.2rem;
  width: 80%;
  border: none;
  padding-left: 0.2rem;
}

.search input:focus {
  border: none;
  outline: none;
}

#user {
  height: 2rem;
}

#password {
  height: 2rem;
}

.clk-button {
  position: relative;
  height: 2rem;
  width: 100%;
}

.clk-button button {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  width: 30%;
  font-size: 1.25rem;
  line-height: 2rem;
  text-align: center;
  color: #ffffff;
  border-radius: 8px 8px 8px 8px;
  margin-right: 0.5rem;
}

.safe button {
  background-color: rgb(64, 158, 255);
}

.safe button:hover {
  background-color: rgb(103, 194, 58);
}

.unsafe button {
  background-color: rgb(255, 0, 0);
}

.unsafe button:hover {
  background-color: rgb(255, 0, 0);
}

.clk-button p {
  width: 100%;
  height: 100%;
  text-align: center;
}

.footer {
  position: absolute;
  left: 50%;
  transform: translate(-50%, 0);
  bottom: 0%;
  height: 10%;
  width: 60%;

  display: flex;
  flex-direction: row;

  color: darkgray;
}

.footer span {
  display: flex;
  margin-right: 1rem;
  height: 2rem;
  width: 100%;
  line-height: 2rem;
}

.footer a {
  position: relative;
  display: inline-block;
  height: 100%;
}

.footer img {
  position: relative;
  margin-right: 0.5rem;
  width: 2rem;
}

.left-22 {
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 1;
}

.right-33 {
  position: absolute;
  bottom: 0;
  right: 0;
  z-index: 1;
}
</style>
