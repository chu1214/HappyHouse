<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <h1 align="center">
        <b-icon icon="question-circle" scale="3" class="mt-5"> </b-icon>
      </h1>
      <b-row class="mb-1 mt-3">
        <b-col class="text-right">
          <b-button variant="outline-dark" @click="moveWrite()"
            >글쓰기</b-button
          >
        </b-col>
      </b-row>
      <b-table
        hover
        responsive
        id="my-table"
        thead-class="bg-dark text-white"
        class="mt-3 text-center"
        :items="articles"
        :fields="fields"
        :per-page="perPage"
        :current-page="currentPage"
        @row-clicked="showDetail"
        style="cursor: pointer"
      >
      </b-table>
      <b-pagination
        align="center"
        v-model="currentPage"
        :total-rows="articles.length"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
      <b-form>
        <div>
          <b-row>
            <b-col sm="4" />
            <b-col sm="4" align="right">
              <b-input
                @keyup.enter="searchArticle"
                type="text"
                v-model="keyword"
                placeholder="제목으로 검색하세요."
              />
            </b-col>
            <b-col>
              <b-button @click="searchArticle" variant="complete" size="sm"
                ><b-icon icon="search" variant="dark" scale="2"></b-icon
              ></b-button>
            </b-col>
          </b-row>
        </div>
      </b-form>
    </b-container>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import moment from "moment";

export default {
  name: "BoardList",

  data() {
    return {
      keyword: "",
      articles: [],
      fields: [
        {
          key: "articleno",
          label: "글번호",
          thStyle: {
            width: "10%",
          },
        },
        {
          key: "subject",
          label: "제목",
          thStyle: {
            width: "50%",
          },
        },
        {
          key: "regtime",
          label: "작성일",
          thStyle: {
            width: "12%",
          },
        },
        {
          key: "userid",
          label: "작성자",
          thStyle: {
            width: "20%",
          },
        },
        {
          key: "hit",
          label: "조회수",
          thStyle: {
            width: "8%",
          },
        },
      ],
      perPage: 10,
      currentPage: 1,
    };
  },

  async created() {
    try {
      const response = await http.get("/board");
      this.articles = response.data;
      for (let article of this.articles) {
        article.subject = `${article.subject} [${article.commentCnt}]`;
        article.regtime = this.dateFormat(article.regtime);
      }
    } catch (error) {
      alert("게시글 조회 실패");
    }
  },

  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    async searchArticle() {
      const response = await http.get(`/board/search/${this.keyword}`);
      if (response.data) {
        this.articles = response.data;
        for (let article of this.articles) {
          article.subject = `${article.subject} [${article.commentCnt}]`;
          article.regtime = this.dateFormat(article.regtime);
        }
      } else {
        alert("검색 실패");
      }
    },
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
    showDetail(e) {
      this.$router.push({
        name: "boardDetail",
        params: { articleno: e.articleno, commentCnt: e.commentCnt },
      });
    },
  },
};
</script>

<style>
.pagination .page-link {
  color: #444;
}
.pagination .page-link:focus {
  box-shadow: 0 0 0 0.2rem rgb(51 56 60 / 25%);
}
.pagination .page-item.active .page-link {
  color: #fff !important;
  background-color: #444 !important;
  border-color: #444 !important;
}
</style>
