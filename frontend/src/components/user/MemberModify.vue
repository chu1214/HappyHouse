<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col align="center">
        <h1 class="underline mt-5">정보수정</h1>
      </b-col>
    </b-row>

    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <div
          class="p-5 mt-4"
          style="
            background-color: #ffffff;
            width: 350px;
            height: 550px;
            border: 1px solid gray;
            border-radius: 90px;
          "
        >
          <hr />
          <b-form class="text-left">
            <b-form-group label-for="userid">
              <b-input-group size="m" class="mb-2">
                <b-input-group-prepend is-text>
                  <b-icon icon="person-fill"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  id="userid"
                  v-model="user.userid"
                  placeholder="아이디"
                  readonly
                ></b-form-input>
              </b-input-group>
            </b-form-group>
            <b-form-group label-for="userpwd">
              <b-input-group size="m" class="mb-2">
                <b-input-group-prepend is-text>
                  <b-icon icon="key-fill"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  type="password"
                  id="userpwd"
                  v-model="user.userpwd"
                  placeholder="비밀번호"
                  @keyup="checkPwd"
                ></b-form-input>
              </b-input-group>
              <div class="err">{{ pwderr }}</div>
            </b-form-group>
            <b-form-group label-for="userpwd2">
              <b-input-group size="m" class="mb-2">
                <b-input-group-prepend is-text>
                  <b-icon icon="key-fill"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  type="password"
                  id="userpwd2"
                  v-model="userpwd2"
                  placeholder="비밀번호 재확인"
                  @keyup="checkPwd2"
                ></b-form-input>
              </b-input-group>
              <div class="err">{{ pwd2err }}</div>
            </b-form-group>
            <b-form-group label-for="name">
              <b-input-group size="m" class="mb-2">
                <b-input-group-prepend is-text>
                  <b-icon icon="emoji-laughing"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  type="text"
                  id="username"
                  v-model="user.username"
                  placeholder="이름"
                  @keyup="checkNm"
                ></b-form-input>
              </b-input-group>
              <div class="err">{{ nameerr }}</div>
            </b-form-group>
            <b-form-group label-for="email">
              <b-input-group size="m" class="mb-2">
                <b-input-group-prepend is-text>
                  <b-icon icon="envelope-fill"></b-icon>
                </b-input-group-prepend>
                <b-form-input
                  type="email"
                  id="email"
                  v-model="user.email"
                  placeholder="이메일"
                  @keyup.enter="completeModify"
                ></b-form-input>
              </b-input-group>
            </b-form-group>
          </b-form>
          <hr />
          <div align="center" class="mt-3">
            <b-button variant="dark" @click="completeModify" class="mr-1"
              >정보수정</b-button
            >
            <b-button variant="secondary" @click="goback" class="ml-1"
              >뒤로가기</b-button
            >
          </div>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { http } from "@/js/http.js";

const memberStore = "memberStore";

export default {
  name: "MemberModify",
  components: {},
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
      },
      userpwd2: null,
      pwderr: "",
      pwd2err: "",
      nameerr: "",
      chkPwd: false,
      chkPwd2: false,
      chkNm: true,
      success: false,
    };
  },
  created() {
    this.user.userid = this.userInfo.userid;
    this.user.username = this.userInfo.username;
    this.user.email = this.userInfo.email;
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

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

    async completeModify() {
      this.success = true;
      if (!this.chkNm) {
        this.success = false;
      }
      if (!this.chkPwd) {
        this.success = false;
      }
      if (!this.chkPwd2) {
        this.success = false;
      }
      if (this.success) {
        const response = await http.put(`/user/modify`, this.user);
        if (response.data == 1) {
          alert("정보 수정을 완료했습니다.");
          this.SET_USER_INFO(this.user);
          this.$router.push({ name: "memberDetail" });
        } else {
          alert("수정이 실패하였습니다.");
        }
      } else {
        alert("입력 양식을 확인해주세요.");
      }
    },
    goback() {
      this.$router.push({ name: "memberDetail" });
    },
  },
};
</script>

<style scoped>
.underline {
  text-align: center;
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(84, 86, 92, 0.678) 30%
  );
}
.err {
  color: rgb(218, 60, 60);
}
</style>
