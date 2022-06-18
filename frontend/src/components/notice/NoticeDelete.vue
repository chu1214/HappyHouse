<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="mt-5" align="center">
          <b-spinner
            variant="secondary"
            style="width: 20rem; height: 20rem"
          ></b-spinner>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { http } from "@/js/http.js";

export default {
  name: "NoticeDelete",
  async created() {
    await http
      .delete(`/notice/${this.$route.params.articleno}`)
      .then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data == 1) {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "noticeList" });
      });
  },
};
</script>

<style></style>
