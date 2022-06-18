<template>
  <div class="background">
    <b-container class="bv-example-row">
      <div class="centerPosition">
        <b-row>
          <b-col cols="12">
            <div align="center" class="mt-5">
              <img
                src="@/assets/happyhouse.png"
                style="width: 300px; height: 200px"
                class="mb-4 mt-5"
              />
            </div>
            <div
              class="p-5"
              style="
                background-color: #ffffff;
                width: 350px;
                height: 530px;
                border: 1px solid gray;
                border-radius: 90px;
              "
            >
              <h2 align="center">회원가입</h2>
              <hr />
              <b-form class="text-left">
                <b-form-group label-for="userid">
                  <b-input-group size="m" class="mb-1">
                    <b-input-group-prepend is-text>
                      <b-icon icon="person-fill"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      id="userid"
                      v-model="user.userid"
                      required
                      placeholder="아이디"
                      @keyup="checkId"
                    ></b-form-input>
                  </b-input-group>
                  <div class="err">{{ iderr }}</div>
                </b-form-group>
                <b-form-group label-for="userpwd">
                  <b-input-group size="m" class="mb-1">
                    <b-input-group-prepend is-text>
                      <b-icon icon="key-fill"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      type="password"
                      id="userpwd"
                      v-model="user.userpwd"
                      required
                      placeholder="비밀번호"
                      @keyup="checkPwd"
                    ></b-form-input>
                  </b-input-group>
                  <div class="err">{{ pwderr }}</div>
                </b-form-group>
                <b-form-group label-for="userpwd">
                  <b-input-group size="m" class="mb-1">
                    <b-input-group-prepend is-text>
                      <b-icon icon="key-fill"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      type="password"
                      id="userpwd"
                      v-model="userpwd2"
                      required
                      placeholder="비밀번호 재확인"
                      @keyup="checkPwd2"
                    ></b-form-input>
                  </b-input-group>
                  <div class="err">{{ pwd2err }}</div>
                </b-form-group>
                <b-form-group label-for="name">
                  <b-input-group size="m" class="mb-1">
                    <b-input-group-prepend is-text>
                      <b-icon icon="emoji-laughing"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      type="text"
                      id="username"
                      v-model="user.username"
                      required
                      placeholder="이름"
                      @keyup="checkNm"
                    ></b-form-input>
                  </b-input-group>
                  <div class="err">{{ nameerr }}</div>
                </b-form-group>
                <b-form-group label-for="email">
                  <b-input-group size="m" class="mb-1">
                    <b-input-group-prepend is-text>
                      <b-icon icon="envelope-fill"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      type="email"
                      id="email"
                      v-model="user.email"
                      placeholder="이메일"
                      @keyup.enter="regist"
                    ></b-form-input>
                  </b-input-group>
                </b-form-group>
                <div align="center">
                  <b-button
                    type="button"
                    variant="dark"
                    style="width: 40%"
                    class="m-2"
                    @click="regist"
                    >가입</b-button
                  >
                  <b-button
                    type="button"
                    variant="secondary"
                    style="width: 40%"
                    class="m-2"
                    @click="goback"
                    >처음으로</b-button
                  >
                </div>
              </b-form>
            </div>
          </b-col>
        </b-row>
      </div>
    </b-container>
  </div>
</template>

<script>
import { http } from "@/js/http.js";

export default {
  name: "MemberRegister",

  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
      },
      userpwd2: null,
      iderr: "",
      pwderr: "",
      pwd2err: "",
      nameerr: "",
      chkId: false,
      chkPwd: false,
      chkPwd2: false,
      chkNm: false,
      chkDuplicated: false,
      success: false,
    };
  },

  methods: {
    checkId() {
      let regex = /^[a-z0-9]{5,16}$/;
      if (this.user.userid.match(regex)) {
        this.iderr = "";
        this.chkId = true;
        this.duplicatedId();
      } else {
        this.iderr = "ID는 소문자 또는 숫자 5~16글자 입니다.";
        this.chkId = false;
      }
    },

    async duplicatedId() {
      const response = await http.get(`/user/search/${this.user.userid}`);
      if (response.data == 0) {
        this.iderr = "";
        this.chkDuplicated = true;
      } else {
        this.iderr = "중복된 아이디입니다.";
        this.chkDuplicated = false;
      }
    },

    checkPwd() {
      let regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
      if (this.user.userpwd.match(regex)) {
        this.pwderr = "";
        this.chkPwd = true;
      } else {
        this.pwderr =
          "비밀번호는 알파벳, 숫자, 특수문자를 포함한 8~16글자 입니다.";
        this.chkPwd = false;
      }
    },

    checkPwd2() {
      if (this.user.userpwd == this.userpwd2) {
        this.pwd2err = "";
        this.chkPwd2 = true;
      } else {
        this.pwd2err = "비밀번호가 일치하지 않습니다.";
        this.chkPwd2 = false;
      }
    },

    checkNm() {
      let regex = /^[가-힣]{2,5}$/;
      if (this.user.username.match(regex)) {
        this.nameerr = "";
        this.chkNm = true;
      } else {
        this.nameerr = "이름은 한글 2~5글자 입니다.";
        this.chkNm = false;
      }
    },

    async regist() {
      this.success = true;
      if (!this.chkId) {
        this.success = false;
      }
      if (!this.chkNm) {
        this.success = false;
      }
      if (!this.chkPwd) {
        this.success = false;
      }
      if (!this.chkPwd2) {
        this.success = false;
      }
      if (!this.chkDuplicated) {
        this.success = false;
      }
      if (this.success) {
        const response = await http.post("/user/regist", this.user);
        if (response.data == 1) {
          alert("해피하우스의 가족이 되신 것을 환영합니다.");
          this.$router.push({ name: "memberLogin" });
        } else {
          alert("가입에 실패하셨습니다.");
        }
      } else {
        alert("입력 양식을 확인해주세요.");
      }
    },
    moveRegist() {
      this.$router.push({ name: "memberRegister" });
    },
    moveFindId() {
      this.$router.push({ name: "memberFindId" });
    },
    moveFindPass() {
      this.$router.push({ name: "memberFindPass" });
    },
    goback() {
      this.$router.push({ name: "memberLogin" });
    },
  },
};
</script>

<style scoped>
.centerPosition {
  display: flex;
  justify-content: center;
  height: 100vh;
}
.err {
  color: rgb(218, 60, 60);
}
.form-group {
  margin-bottom: 0.2rem;
}
</style>
