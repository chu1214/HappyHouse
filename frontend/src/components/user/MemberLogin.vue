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
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <div
              class="p-5"
              style="
                background-color: #ffffff;
                width: 350px;
                height: 300px;
                border: 1px solid gray;
                border-radius: 90px;
              "
            >
              <b-form class="text-left">
                <b-form-group label-for="userid">
                  <b-input-group size="m" class="mb-2">
                    <b-input-group-prepend is-text>
                      <b-icon icon="person-fill"></b-icon>
                    </b-input-group-prepend>
                    <b-form-input
                      id="userid"
                      v-model="user.userid"
                      required
                      placeholder="ID"
                      @keyup.enter="confirm"
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
                      required
                      placeholder="PASSWORD"
                      @keyup.enter="confirm"
                    ></b-form-input>
                  </b-input-group>
                </b-form-group>
                <div align="center">
                  <b-button
                    type="button"
                    variant="dark"
                    style="width: 70%"
                    class="m-2"
                    @click="confirm"
                    >로그인</b-button
                  >
                </div>
              </b-form>
              <div
                align="center"
                type="button"
                variant="primary"
                class="m-1"
                @click="moveRegist"
              >
                아직 계정이 없으신가요?
              </div>
              <div align="center">
                <span style="cursor: pointer" @click="moveFindId">아이디</span>
                혹은
                <span style="cursor: pointer" @click="moveFindPass"
                  >비밀번호</span
                >가 기억나지 않으시나요?
              </div>
            </div>
          </b-col>
        </b-row>
      </div>
    </b-container>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "map" });
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
  },
};
</script>

<style>
.centerPosition {
  display: flex;
  justify-content: center;
  height: 100vh;
}
.background {
  background-size: cover;
  background-image: url("@/assets/bgimage.jpg");
}
</style>
