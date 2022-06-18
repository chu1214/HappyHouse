<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <h1 align="center">
        <b-icon icon="globe2" scale="3" class="mt-5"> </b-icon>
      </h1>
      <b-row class="mb-1 mt-3">
        <b-col class="text-right">
          <div class="mt-5"></div>
        </b-col>
      </b-row>
      <b-table
        id="my-table"
        thead-class="bg-dark text-white"
        class="mt-3 text-center"
        :items="newsList"
        :fields="fields"
        :per-page="perPage"
        :current-page="currentPage"
        style="cursor: pointer"
        caption-top
      >
        <template #table-caption>
          * 제목을 누르면 해당 기사로 이동합니다.</template
        >
        <template #cell(글번호)="data">
          {{ data.index + 1 }}
        </template>
        <template #cell(제목)="data">
          <a
            :href="`${data.item.url}`"
            target="_blank"
            style="text-decoration-line: none; color: inherit; font-size: 15px"
            >{{ data.item.title.substr(0, 35) }}</a
          >
        </template>
        <template #cell(내용)="data">
          {{ data.item.contents }}
        </template>
      </b-table>
      <b-pagination
        align="center"
        v-model="currentPage"
        :total-rows="newsList.length"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
      <b-form>
        <div>
          <b-row>
            <b-col sm="4" />
            <b-col sm="4" align="right">
              <b-input
                @keyup.enter="searchNews"
                type="text"
                v-model="search"
                placeholder="원하는 뉴스를 검색하세요."
              />
            </b-col>
            <b-col>
              <b-button @click="searchNews" variant="complete" size="sm"
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

export default {
  name: "NewsList",

  data() {
    return {
      search: "",
      newsList: [],
      fields: ["글번호", "제목", "내용"],
      perPage: 8,
      currentPage: 1,
    };
  },

  async created() {
    try {
      const response = await http.get("/news");
      this.newsList = response.data;
      let i = 1;
      for (let news of this.newsList) {
        news.no = i++;
      }
    } catch (error) {
      alert("게시글 조회 실패");
    }
  },

  methods: {
    async searchNews() {
      if (this.search == "") {
        alert("검색어를 확인해주세요.");
      } else {
        try {
          const response = await http.get(`/news/search/${this.search}`);
          this.newsList = response.data;
          let i = 1;
          for (let news of this.newsList) {
            news.no = i++;
          }
        } catch (error) {
          alert("게시글 조회 실패");
        }
      }
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
.table td,
.table th {
  vertical-align: center;
}
.table thead th {
  vertical-align: center !important;
}
</style>
