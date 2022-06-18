<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col align="center">
        <h1 class="underline mt-5">회원정보</h1>
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
            height: 450px;
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
                  :value="userInfo.userid"
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
                  value="********"
                  placeholder="비밀번호"
                  readonly
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
                  :value="userInfo.username"
                  placeholder="이름"
                  readonly
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
                  :value="userInfo.email"
                  placeholder="이메일"
                  readonly
                ></b-form-input>
              </b-input-group>
            </b-form-group>
          </b-form>
          <hr />
          <div align="center" class="mt-5">
            <b-button variant="dark" @click="moveModify" class="mr-1"
              >정보수정</b-button
            >
            <b-button variant="danger" @click="memberDelete" class="ml-1"
              >회원탈퇴</b-button
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
  name: "MemberDetail",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    moveModify() {
      this.$router.push({ name: "memberModify" });
    },
    async memberDelete() {
      let result = confirm("정말로 탈퇴 하시겠습니까?");
      if (!result) return;
      const response = await http.delete(
        `/user/delete/${this.userInfo.userid}`
      );
      if (response.data == 1) {
        alert("탈퇴되었습니다.");
        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        sessionStorage.removeItem("access-token");
        if (this.$route.path != "/") this.$router.push({ name: "memberLogin" });
        window.document.body.style.paddingLeft = 0;
      }
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
</style>
