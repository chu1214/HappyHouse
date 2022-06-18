<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="complete" @click="listArticle"
          ><b-icon icon="reply" scale="1.5"></b-icon>
        </b-button>
      </b-col>
      <b-col class="text-right" v-if="this.currentUserId == 'admin'">
        <b-button
          variant="complete"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          ><b-icon icon="pencil-square" scale="1.5"></b-icon
        ></b-button>
        <b-button variant="complete" size="sm" @click="deleteArticle"
          ><b-icon icon="trash" scale="1.5"></b-icon
        ></b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card header-tag="header">
          <template #header>
            <h3 class="mt-2">{{ article.subject }}</h3>
            <div>
              <span>
                <h6>
                  {{ article.userid }} &nbsp; | &nbsp; {{ article.regtime }}
                </h6>
                <h6 align="right">
                  <b-icon icon="eye"></b-icon>&nbsp;{{ article.hit }}
                  &nbsp;&nbsp;&nbsp;
                </h6>
              </span>
            </div>
          </template>
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { http } from "@/js/http.js";

export default {
  name: "ArticleContent",
  data() {
    return {
      currentUserId: "",
      article: {},
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  async created() {
    const data = JSON.parse(sessionStorage.getItem("vuex"));
    const userid = data.memberStore.userInfo.userid;
    this.currentUserId = userid;

    const articleno = this.$route.params.articleno;
    const response1 = await http.get(`/notice/${articleno}`);
    this.article = response1.data;
    this.article.hit += 1;
    // 조회수 변경 및 DB 수정
    await http.put(`/notice/${articleno}`, {
      articleno: this.article.articleno,
      userid: this.article.userid,
      subject: this.article.subject,
      content: this.article.content,
      hit: this.article.hit,
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "noticeDelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style scoped></style>
