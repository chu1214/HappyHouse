<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label-for="userid">
          <b-input-group size="lg" class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="person-fill"></b-icon>
            </b-input-group-prepend>
            <b-form-input
              id="userid"
              :disabled="isUserid"
              v-model="article.userid"
              type="text"
              required
              readonly
            ></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group id="subject-group" label-for="subject">
          <b-input-group size="lg" class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="cursor"></b-icon>
            </b-input-group-prepend>
            <b-form-input
              id="subject"
              v-model="article.subject"
              type="text"
              required
              placeholder="제목을 입력하세요"
            ></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group id="content-group" label-for="content">
          <b-input-group size="lg" class="mb-2">
            <b-input-group-prepend is-text>
              <b-icon icon="card-text"></b-icon>
            </b-input-group-prepend>
            <b-form-textarea
              id="content"
              v-model="article.content"
              placeholder="내용을 입력하세요."
              rows="8"
            ></b-form-textarea>
          </b-input-group>
        </b-form-group>
        <div align="right">
          <b-button
            pill
            type="submit"
            variant="complete"
            class="m-1"
            v-if="this.type === 'register'"
            ><b-icon icon="check-square" variant="dark" font-scale="2"></b-icon
          ></b-button>
          <b-button type="submit" variant="complete" class="m-1" v-else
            ><b-icon icon="check-square" variant="dark" font-scale="2"></b-icon
          ></b-button>
          <b-button type="reset" class="m-1" variant="complete"
            ><b-icon icon="x-square" variant="dark" font-scale="2"></b-icon
          ></b-button>
        </div>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { http } from "@/js/http.js";
export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        this.article = data;
      });
      this.isUserid = true;
    }
    const data = JSON.parse(sessionStorage.getItem("vuex"));
    const userid = data.memberStore.userInfo.userid;
    this.article.userid = userid;
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userid &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({
        name: "boardList",
      });
    },
    registArticle() {
      http
        .post(`/board`, {
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data == 1) {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyArticle() {
      http
        .put(`/board/${this.article.articleno}`, {
          articleno: this.article.articleno,
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data == "1") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({
            name: "boardDetail",
            params: {
              articleno: this.article.articleno,
            },
          });
        });
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style scoped></style>
