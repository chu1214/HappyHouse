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
            <b-alert show variant="danger" v-if="!isFindError"
              >입력 정보를 확인하세요.</b-alert
            >
            <div
              class="p-5"
              style="
                background-color: #ffffff;
                width: 350px;
                height: 350px;
                border: 1px solid gray;
                border-radius: 90px;
              "
            >
              <h2 align="center">비밀번호찾기</h2>
              <hr />
              <b-form class="text-left">
                <b-form-group label-for="userid">
                  <b-input-group size="m" class="mb-2">
                    <b-input-group-prepend is-text>
                      <b-icon icon="person-fill"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      id="userid"
                      v-model.trim="user.userid"
                      placeholder="아이디"
                    ></b-form-input>
                  </b-input-group>
                </b-form-group>
                <b-form-group label-for="name">
                  <b-input-group size="m" class="mb-2">
                    <b-input-group-prepend is-text>
                      <b-icon icon="emoji-laughing"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      type="text"
                      id="username"
                      v-model.trim="user.username"
                      placeholder="이름"
                    ></b-form-input>
                  </b-input-group>
                </b-form-group>
                <b-form-group label-for="email">
                  <b-input-group size="m" class="mb-2">
                    <b-input-group-prepend is-text>
                      <b-icon icon="envelope-fill"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      type="email"
                      id="email"
                      v-model.trim="user.email"
                      placeholder="이메일"
                      @keyup.enter="findPwd"
                    ></b-form-input>
                  </b-input-group>
                </b-form-group>
                <div align="center">
                  <b-button
                    type="button"
                    variant="dark"
                    style="width: 40%"
                    class="m-2"
                    @click="findPwd"
                    >찾기</b-button
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
  name: "MemberFindPwd",
  data() {
    return {
      user: {
        userid: null,
        username: null,
        email: null,
      },
      isFindError: true,
      foundPwd: "",
    };
  },

  methods: {
    async findPwd() {
      this.isError();
      if (this.isFindError) {
        const response = await http.post("/user/findpwd", this.user);
        if (response.data == "SUCCESS") {
          alert("회원님의 가입하신 이메일로 임시비밀번호가 발송되었습니다.");
          this.goback();
        } else {
          alert("입력 정보를 확인해주세요.");
        }
      }
    },

    isError() {
      this.isFindError = true;
      if (this.user.userid == null || this.user.userid == "") {
        this.isFindError = false;
      }
      if (this.user.username == null || this.user.username == "") {
        this.isFindError = false;
      }
      if (this.user.email == null || this.user.email == "") {
        this.isFindError = false;
      }
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
</style>
